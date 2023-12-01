package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<Passenger, Long>{
    //basic CRUD operations
    Passenger findByEmailAndLname(String email, String lname);
}
