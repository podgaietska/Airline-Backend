package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByEmail(String email);

    Optional<Agent> findByEmployeeId(Long employeeId);

    boolean existsByEmployeeId(Long employeeId);
}
