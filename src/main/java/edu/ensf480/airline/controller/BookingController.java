package edu.ensf480.airline.controller;

import edu.ensf480.airline.dto.BookingRequest;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/bookFlight")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/checkout/member")
    public ResponseEntity<?> createBookingMember(@RequestBody BookingRequest passengerDetails, @RequestParam Long flightId, @RequestParam Long seatId, @RequestParam boolean insurance){
        try{
            Booking booking = bookingService.createBookingMember(passengerDetails, flightId, seatId, insurance);
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @PostMapping("/checkout/guest")
    public ResponseEntity<?> createBookingGuest(@RequestBody BookingRequest passengerDetails, @RequestParam Long flightId, @RequestParam Long seatId, @RequestParam boolean insurance){
        try{
            Booking booking = bookingService.createBookingGuest(passengerDetails, flightId, seatId, insurance);
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

}
