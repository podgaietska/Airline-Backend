package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    //basic CRUD operations
}
