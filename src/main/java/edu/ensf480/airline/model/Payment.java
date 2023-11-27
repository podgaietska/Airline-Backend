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
    private long total;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    private final long comfortSeat = (long) 1.4;
    private final long businessClassSeat = (long) 2.5;


    public Payment(Booking booking){
        calculate_total(booking);
    }

    private void calculate_total(Booking booking){
        total = booking.getFlight().getBaseSeatPrice();
        String seatClass = booking.getSeat().getSeatClass();
        if (seatClass == "Comfort") total *= comfortSeat;
        if (seatClass == "Business-Class") total *= businessClassSeat;

        
    }
}
