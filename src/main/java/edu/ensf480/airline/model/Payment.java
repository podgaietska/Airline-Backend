package edu.ensf480.airline.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

import java.util.Objects;

/**
 * Payment class for the Airline Reservation System
 *
 * This class is used to take the booking and user info to charge the client through strategy pattern
 *
 * @version 1.0
 * @since 2023-11-26
 */


@Entity
@NoArgsConstructor
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    /**
     * Charges a payment based on the booking and customer info
     * @param booking The booking to pay for
     * @param cardNumber credit/debit card number
     * @param year credit/debit card year expiry
     * @param month credit/debit card month expiry
     * @param cvc credit/debit card cvc expiry
     * @param paymentmethod Strategy pattern selecting credit or debit card
     */
    public void chargePayment(Booking booking, int cardNumber, int year, int month, int cvc, CollectPayment paymentmethod){
        paid = paymentmethod.processPayment(cardNumber, year, month, cvc, booking.getTotalCost());
    }

    /**
     * Getter to indicate payment success
     * @return true if successful
     */
    private boolean getPaymentSuccess(){return this.paid;}
}
