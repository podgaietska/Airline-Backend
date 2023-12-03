package edu.ensf480.airline.service;

import edu.ensf480.airline.model.Agent;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.Passenger;
import edu.ensf480.airline.repository.AgentRepository;
import edu.ensf480.airline.repository.BookingRepository;
import edu.ensf480.airline.repository.FlightRepository;
import edu.ensf480.airline.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final BookingRepository bookingRepository;

    private final FlightRepository flightRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository, BookingRepository bookingRepository, MemberRepository memberRepository, FlightRepository flightRepository){
        this.agentRepository = agentRepository;
        this.bookingRepository = bookingRepository;
        this.memberRepository = memberRepository;
        this.flightRepository = flightRepository;
    }

    public List<Passenger> getPassengersOnFlight(String flightNumber) throws Exception {
        Long flightId = flightRepository.findByFlightNumber(flightNumber).get().getId();
        System.out.println(flightId);
        List<Booking> bookings = bookingRepository.findByFlightId(flightId);
        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }

        return bookingRepository.findByFlightId(flightId).stream()
                .map(Booking::getUser) // Assuming you have a method getPassenger() in Booking
                .collect(Collectors.toList());
    }

    public Agent register(Agent newAgent) throws Exception{
        if (agentRepository.findByEmail(newAgent.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }

        Long employeeId;

        do {
            employeeId = newAgent.generateEmployeeId();
        } while (agentRepository.existsByEmployeeId(employeeId));

        newAgent.setEmployeeId(employeeId);

        return agentRepository.save(newAgent);
    }

    public Agent login(Long employeeId, String password) throws Exception{
        Optional<Agent> agent = agentRepository.findByEmployeeId(employeeId);
        if(agent.isPresent() && agent.get().getPassword().equals(password)){
            return agent.get();
        }
        throw new Exception("Invalid credentials. Please try again.");
    }

}
