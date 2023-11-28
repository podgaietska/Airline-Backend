package edu.ensf480.airline.controller;

import edu.ensf480.airline.dto.NonMemberDetails;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.User;
import edu.ensf480.airline.model.payment.Payment;
import edu.ensf480.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookFlight")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/checkout/member")
    public ResponseEntity<?> createBookingMember(@RequestParam Long userId, @RequestParam Long flightId, @RequestParam Long seatId, @RequestBody Payment payment){
        try{
            Booking booking = bookingService.createBookingMember(userId, flightId, seatId);
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/checkout/guest")
    public ResponseEntity<?> createBookingGuest(@RequestBody NonMemberDetails passengerDetails, @RequestParam Long flightId, @RequestParam Long seatId){
        try{
            Booking booking = bookingService.createBookingGuest(passengerDetails, flightId, seatId);
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
