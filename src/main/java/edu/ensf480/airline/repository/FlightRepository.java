package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    //basic CRUD operations
}
