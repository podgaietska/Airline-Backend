package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{
    //basic CRUD operations
}
