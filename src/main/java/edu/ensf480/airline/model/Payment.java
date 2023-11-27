package edu.ensf480.airline.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_method", nullable = false)
    CollectPayment paymentmethod;

    @Column(name = "payment_total", nullable = false)
    private double total;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    private static final double comfortSeat = 1.4;
    private static final double businessClassSeat = 2.5;
    private static final double cancellationInsurance = 1.25;

    public Payment(){}

    private void calculate_total(Booking booking){
        total = booking.getFlight().getBaseSeatPrice();
        String seatClass = booking.getSeat().getSeatClass();
        if (seatClass == "Comfort") total *= comfortSeat;
        if (seatClass == "Business-Class") total *= businessClassSeat;

        if (booking.getCancellationInsurance()) total *= cancellationInsurance;
    }

    public boolean collectPayment(int cardNumber, int year, int month, int cvc){
        paid = paymentmethod.processPayment(cardNumber, year, month, cvc, total);
        return paid;
    }
}
