package edu.ensf480.airline.service;

import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.model.Passenger;
import edu.ensf480.airline.model.Seat;
import edu.ensf480.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight newFlight) throws Exception{
        List<Flight> existingFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(newFlight.getDepartureAirport(), newFlight.getArrivalAirport(), newFlight.getDepartureTime());
        if (!existingFlights.isEmpty()){
            throw new Exception("Flight already exists");
        }
        Set<Seat> seats = newFlight.createSeatsForFlight();
        newFlight.setSeats(seats);
        return flightRepository.save(newFlight);
    }

}
