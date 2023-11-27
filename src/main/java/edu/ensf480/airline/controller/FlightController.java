package edu.ensf480.airline.controller;

import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.repository.FlightRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(@RequestParam String departureAirport,
                                                      @RequestParam String arrivalAirport,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirportAndFlightDate(departureAirport, arrivalAirport, date);

        if(flights.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, no flights found for the given parameters.");
        }

        return ResponseEntity.ok(flights);
    }

    @PostMapping("/add")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        Flight newFlight = flightRepository.save(flight);
        return ResponseEntity.status(201).body(newFlight);
    }
}
