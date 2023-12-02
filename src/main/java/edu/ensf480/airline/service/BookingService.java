package edu.ensf480.airline.service;

import edu.ensf480.airline.dto.BookingRequest;
import edu.ensf480.airline.model.*;
import edu.ensf480.airline.model.payment.Payment;
import edu.ensf480.airline.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private PassengerRepository userRepository;
    private FlightRepository flightRepository;
    private PaymentRepository paymentRepository;
    private final JdbcTemplate jdbcTemplate;
    private final MemberRepository memberRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, PassengerRepository userRepository, FlightRepository flightRepository, PaymentRepository paymentRepository, JdbcTemplate jdbcTemplate, MemberRepository memberRepository){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.paymentRepository = paymentRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Booking createBookingMember(BookingRequest passengerDetails, Long flightId, Long seatId, boolean insurance) throws Exception {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
        // Find the user, flight, and seat from their respective repositories
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new Exception("Flight not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found"));
        Passenger user = userRepository.findByEmailAndLname(passengerDetails.getEmail(), passengerDetails.getLname());

        // Check if the user exists
        if (user == null){
            throw new Exception("User not found");
        } else {
            //check if user already has a booking for this flight
            Optional<Booking> existingBooking = bookingRepository.findByUserAndFlight(user, flight);
            if (existingBooking.isPresent()){
                throw new Exception("User already has a booking for this flight");
            }
        }

        // Check if the seat is occupied
        seat.checkIfOccupied();

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
            SendEmail email = new SendEmail();
            email.SendBookingEmail(booking);
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
        Passenger user = userRepository.findByEmailAndLname(passengerDetails.getEmail(), passengerDetails.getLname());

        // Check if the user exists
        if (user == null){
            //create new user if does not yet exist
            user = new Passenger(passengerDetails.getFname(), passengerDetails.getLname(), passengerDetails.getEmail(), passengerDetails.getPhone(), passengerDetails.getDateOfBirth());
//            while (memberRepository.existsById(user.getId() )){
//                user.setId(user.generateRandomId());
//            }
            userRepository.save(user);
        } else {
            //check if user already has a booking for this flight
            Optional<Booking> existingBooking = bookingRepository.findByUserAndFlight(user, flight);
            if (existingBooking.isPresent()){
                throw new Exception("User already has a booking for this flight");
            }
        }

        // Check if the seat is occupied
        seat.checkIfOccupied();

        // Create booking
        Booking booking = new Booking(flight, user, seat, passengerDetails);
        booking.getSeat().setIsOccupied(true);
        booking.setCancellationInsurance(insurance);
        booking.setPaymentStrategy(passengerDetails);
        booking.chargeCard();
        if (booking.getPayment().getPaymentSuccess()){
            //Save booking and payment if payment was successful
            Payment savedPayment = paymentRepository.save(booking.getPayment());
            booking.setPayment(savedPayment);
            seatRepository.save(seat);
            SendEmail email = new SendEmail();
            email.SendBookingEmail(booking);
            return bookingRepository.save(booking);
        }
        else{
            throw new Exception("Payment failed");
        }
    }

    public Booking getBookingByBookingNumber(String bookingNumber) throws Exception {
        return bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(() -> new Exception("Booking not found with number: " + bookingNumber));
    }

    public double getBookingTotal(Long seatId, boolean insurance, String province) throws Exception {
        Booking booking = new Booking();
        booking.setSeat(seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found")));
        booking.setCancellationInsurance(insurance);
        booking.calculateCost(province);
        return booking.getTotalCost();
    }

    public List<Booking> getBookingsByUser(Long userId) throws Exception {
        Optional<Passenger> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }

        List<Booking> bookings = bookingRepository.findByUser(user.get());

        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }

        return bookings;
    }

    @Transactional
    public void cancelBooking(String bookingNumber) throws Exception {
        Booking booking = bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(() -> new Exception("Booking not found with number: " + bookingNumber));
        Seat seat = booking.getSeat();
        if (booking.getCancellationInsurance()){
            booking.refundPayment();
        }
        SendEmail email = new SendEmail();
        email.SendCancellationEmail(booking, booking.getCancellationInsurance());
        seat.setIsOccupied(false);
        seatRepository.save(seat);
        bookingRepository.delete(booking);
    }
}
