package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    //basic CRUD operations
    List<Flight> findByDepartureAirportAndArrivalAirportAndFlightDate(String departureAirport, String arrivalAirport, LocalDate date);
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTime(String departureAirport, String arrivalAirport, LocalTime departureTime);

}
