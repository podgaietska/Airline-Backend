package edu.ensf480.airline.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;

/**
 * Flight class for the Airline Reservation System
 *
 * This class is used to represent a flight in the Airline Reservation System.
 *
 * @version 1.0
 * @since 2021-03-20
 */

@Entity
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @Column(name = "departure_time", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    @Column(name = "flight_duration", nullable = false)
    private String flightDuration;

    @Column(name = "flight_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightDate;

    @Column(name = "base_seat_price", nullable = false)
    private double baseSeatPrice;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Seat> seats;

    /**
     * @param  flightNumber - Unique identifier for the flight
     * @param departureAirport - Airport in city of departure
     * @param arrivalAirport - Airport in city of arrival
     * @param departureTime - Time of departure
     * @param arrivalTime - Time of arrival
     * @param flightDuration - Duration of flight
     * @param flightDate - Date of flight
     * @param baseSeatPrice - Price of an ordinary seat
     */
    public Flight(String flightNumber, String departureAirport, String arrivalAirport, LocalTime departureTime, LocalTime arrivalTime, String flightDuration, LocalDate flightDate,double baseSeatPrice){
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
        this.flightDate = flightDate;
        this.baseSeatPrice = baseSeatPrice;
    }

    /**
     * Getter for flightNumber
     * @return the flightNumber: String
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Setter for flightNumber
     * @param flightNumber - Unique identifier for the flight
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Getter for departureAirport
     * @return the departureAirport: String
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * Setter for departureAirport
     * @param departureAirport - Airport in city of departure
     */
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * Getter for arrivalAirport
     * @return the arrivalAirport: String
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * Setter for arrivalAirport
     * @param arrivalAirport - Airport in city of arrival
     */
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     * Getter for departureTime
     * @return the departureTime: String
     */
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Setter for departureTime
     * @param departureTime - Time of departure
     */
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Getter for arrivalTime
     * @return the arrivalTime: String
     */
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Setter for arrivalTime
     * @param arrivalTime - Time of arrival
     */
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Getter for flightDuration
     * @return the flightDuration: String
     */
    public String getFlightDuration() {
        return flightDuration;
    }

    /**
     * Setter for flightDuration
     * @param flightDuration - Duration of flight
     */
    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    /**
     * Getter for flightDate
     * @return the flightDate: String
     */
    public LocalDate getFlightDate() {
        return flightDate;
    }

    /**
     * Setter for flightDate
     * @param flightDate - Date of flight
     */
    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    /**
     * Getter for seats
     * @return the seats: Set<Seat>
     */
    public Set<Seat> getSeats() {
        return seats;
    }

    /**
     * Setter for seats
     * @param seats - Set of seats on the flight
     */
    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    /**
     * Setter for base seat price
     * @param baseSeatPrice - Price of an ordinary seat
     */
    public void setBaseSeatPrice(double baseSeatPrice){this.baseSeatPrice = baseSeatPrice;}

    /**
     * Getter for base seat price
     * @return the base seat price - double
     */
    public double getBaseSeatPrice(){return baseSeatPrice;}

}
