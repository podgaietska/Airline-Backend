package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    //basic CRUD operations
    Optional<Booking> findByUserAndFlight(User user, Flight flight);
    Optional<Booking> findByBookingNumber(String bookingNumber);

    List<Booking> findByUser(User user);
}
