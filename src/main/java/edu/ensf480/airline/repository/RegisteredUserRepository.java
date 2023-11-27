package edu.ensf480.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensf480.airline.model.RegisteredUser;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{
    Optional<RegisteredUser> findByEmail(String email);
}
