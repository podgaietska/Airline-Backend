package edu.ensf480.airline.service;

import edu.ensf480.airline.dto.BookingRequest;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.model.Seat;
import edu.ensf480.airline.model.User;
import edu.ensf480.airline.model.payment.Payment;
import edu.ensf480.airline.model.payment.chargeCreditCard;
import edu.ensf480.airline.model.payment.chargeDebitCard;
import edu.ensf480.airline.model.payment.PaymentStrategy;
import edu.ensf480.airline.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private FlightRepository flightRepository;
    private PaymentRepository paymentRepository;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, UserRepository userRepository, FlightRepository flightRepository, PaymentRepository paymentRepository, JdbcTemplate jdbcTemplate){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.paymentRepository = paymentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Booking createBookingMember(BookingRequest passengerDetails, Long flightId, Long seatId, boolean insurance) throws Exception {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
        // Find the user, flight, and seat from their respective repositories
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new Exception("Flight not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found"));
        seat.checkIfOccupied();
        User user = userRepository.findByEmailAndLname(passengerDetails.getEmail(), passengerDetails.getLname());
        // Check if the user exists
        if (user == null){
            throw new Exception("User not found");
        }

        // Create booking
        Booking booking = new Booking(flight, user, seat, passengerDetails);
        booking.getSeat().setIsOccupied(true);
        booking.setCancellationInsurance(insurance);
        booking.setPaymentStrategy(passengerDetails);
        booking.chargeCard();
        if (booking.getPayment().getPaymentSuccess()){
            Payment savedPayment = paymentRepository.save(booking.getPayment());
            booking.setPayment(savedPayment);
            seatRepository.save(seat);
            return bookingRepository.save(booking);
        } else{
            throw new Exception("Payment failed");
        }
    }

    @Transactional
    public Booking createBookingGuest(BookingRequest passengerDetails, Long flightId, Long seatId, boolean insurance) throws Exception {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");

        // Find the flight and seat from their respective repositories
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new Exception("Flight not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found"));
        // Check if the seat is occupied
        seat.checkIfOccupied();
        User user = userRepository.findByEmailAndLname(passengerDetails.getEmail(), passengerDetails.getLname());

        if (user == null){
            //create new user if does not yet exist
            user = new User(passengerDetails.getFname(), passengerDetails.getLname(), passengerDetails.getEmail(), passengerDetails.getPhone(), passengerDetails.getDateOfBirth());
        } else {
            //check if user already has a booking for this flight
            Optional<Booking> existingBooking = bookingRepository.findByUserAndFlight(user, flight);
            if (existingBooking.isPresent()){
                throw new Exception("User already has a booking for this flight");
            }
        }

        // Create booking
        Booking booking = new Booking(flight, user, seat, passengerDetails);
        booking.getSeat().setIsOccupied(true);
        booking.setCancellationInsurance(insurance);
        booking.setPaymentStrategy(passengerDetails);
        booking.chargeCard();
        if (booking.getPayment().getPaymentSuccess()){
            //Save booking and payment if payment was successful
            userRepository.save(user);
            Payment savedPayment = paymentRepository.save(booking.getPayment());
            booking.setPayment(savedPayment);
            seatRepository.save(seat);
            return bookingRepository.save(booking);
        }
        else{
            throw new Exception("Payment failed");
        }
    }




}
