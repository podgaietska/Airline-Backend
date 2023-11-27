package edu.ensf480.airline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Booking class for the Airline Reservation System
 *
 * This class is used to represent a booking in the Airline Reservation System.
 *
 * @version 1.0
 * @since 2021-03-20
 */

@Entity
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long BookingNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    /**
     * @param flight        - Flight of the booking
     * @param user          - User of the booking
     * @param seat          - Seat of the booking
     */

    public Booking(Flight flight, User user, Seat seat) {
        this.flight = flight;
        this.user = user;
        this.seat = seat;
    }

    public long getBookingNumber() {
        return BookingNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public User getUser() {
        return user;
    }

    public Seat getSeat() {
        return seat;
    }

}
