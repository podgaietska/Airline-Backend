package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
