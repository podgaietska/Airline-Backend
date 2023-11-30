package edu.ensf480.airline.repository;

import edu.ensf480.airline.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
