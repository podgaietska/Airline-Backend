package edu.ensf480.airline.service;

import edu.ensf480.airline.dto.NonMemberDetails;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.model.Seat;
import edu.ensf480.airline.model.User;
import edu.ensf480.airline.model.payment.CreditPayment;
import edu.ensf480.airline.model.payment.DebitPayment;
import edu.ensf480.airline.model.payment.Payment;
import edu.ensf480.airline.model.payment.PaymentStrategy;
import edu.ensf480.airline.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private FlightRepository flightRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, UserRepository userRepository, FlightRepository flightRepository){
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public Booking createBookingMember(Long userId, Long flightId, Long seatId) throws Exception {
        // Find the user, flight, and seat from their respective repositories
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new Exception("Flight not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found"));
        return null;

    }

    public Booking createBookingGuest(NonMemberDetails passengerDetails, Long flightId, Long seatId) throws Exception {
        // Find the flight and seat from their respective repositories
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new Exception("Flight not found"));
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new Exception("Seat not found"));
        User newUser = new User(passengerDetails.getFname(), passengerDetails.getLname(), passengerDetails.getEmail(), passengerDetails.getPhone(), passengerDetails.getDateOfBirth());

        PaymentStrategy paymentStrategy;

        if (passengerDetails.getPaymentStartegy().equals("debit")){
            paymentStrategy = new DebitPayment();
        } else if (passengerDetails.getPaymentStartegy().equals("credit")){
            paymentStrategy = new CreditPayment();
        } else{
            throw new Exception("Payment strategy not found");
        }

        Payment payment = new Payment(passengerDetails.getCardNumber(), passengerDetails.getExpirationMonth(), passengerDetails.getExpirationYear(), passengerDetails.getCvv(), paymentStrategy);
        return null;
    }




}
