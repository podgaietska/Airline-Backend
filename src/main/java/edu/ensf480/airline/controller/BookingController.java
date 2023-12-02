package edu.ensf480.airline.controller;

import edu.ensf480.airline.dto.BookingRequest;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.SendEmail;
import edu.ensf480.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/booking")
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

    @GetMapping("/total")
    public ResponseEntity<?> getPrice(@RequestParam Long seatId, @RequestParam boolean insurance, @RequestParam String province){
        try{
            double price = bookingService.getBookingTotal(seatId, insurance, province);
            return ResponseEntity.ok(price);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @GetMapping("/{bookingNumber}")
    public ResponseEntity<?> getBooking(@PathVariable String bookingNumber){
        try{
            Booking booking = bookingService.getBookingByBookingNumber(bookingNumber);
            return ResponseEntity.ok(booking);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable Long userId){
        try{
            List<Booking> bookings = bookingService.getBookingsByUser(userId);
            return ResponseEntity.ok(bookings);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/cancel/{bookingNumber}")
    public ResponseEntity<?> cancelBooking(@PathVariable String bookingNumber){
        try{
            bookingService.cancelBooking(bookingNumber);
            Map<String, String> status = new HashMap<>();
            status.put("status", "Booking cancelled successfully. Email sent.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

}
