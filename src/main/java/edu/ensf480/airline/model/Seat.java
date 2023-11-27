package edu.ensf480.airline.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Seat class for the Airline Reservation System
 *
 * This class is used to represent a seat on a flight in the Airline Reservation System.
 *
 * @version 1.0
 * @since 2021-03-20
 */

@Entity
@NoArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "seat_class", nullable = false)
    private String seatClass;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    /**
     * @param seatNumber - Unique identifier for the seat
     * @param seatClass - Class of the seat
     * @param flight - Flight the seat is on
     * @param isOccupied - Whether the seat is occupied
     */

    public Seat(String seatNumber, String seatClass, Flight flight, boolean isOccupied) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.flight = flight;
        this.isOccupied = isOccupied;
    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public Flight getFlight() {
        return flight;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

}
