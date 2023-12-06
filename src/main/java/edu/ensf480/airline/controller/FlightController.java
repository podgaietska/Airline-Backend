package edu.ensf480.airline.controller;

import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.repository.FlightRepository;
import edu.ensf480.airline.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/flight")
public class FlightController {

    private final FlightRepository flightRepository;
    private final FlightService flightService;

    public FlightController(FlightRepository flightRepository, FlightService flightService) {

        this.flightRepository = flightRepository;
        this.flightService = flightService;
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
            Map<String, String> error = new HashMap<>();
            error.put("error", "No flights for the following route or dates found. Please try another search.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}/seats")
    public ResponseEntity<?> getSeats(@PathVariable Long flightId){
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if(flight == null){
            Map<String, String> error = new HashMap<>();
            error.put("error", "Something went wrong. Please try again.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        return ResponseEntity.ok(flight.getSeats());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createFlight(@RequestBody Flight flight){
        try{
            Flight newFlight = flightService.createFlight(flight);
            return ResponseEntity.ok(newFlight);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
}
