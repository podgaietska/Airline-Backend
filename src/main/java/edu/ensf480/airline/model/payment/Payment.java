package edu.ensf480.airline.model.payment;

import edu.ensf480.airline.model.Booking;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "expiration_month", nullable = false)
    private int expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private int expirationYear;

    @Transient //will not be saved in the database
    private int cvc;

    @Transient //will not be saved in the database
    private PaymentStrategy paymentStrategy;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    /**
     * Charges a payment based on the booking and customer info
     * @param cardNumber credit/debit card number
     * @param expirationYear credit/debit card year expiry
     * @param expirationMonth credit/debit card month expiry
     * @param cvc credit/debit card cvc expiry
     * @param paymentMethod Strategy pattern selecting credit or debit card
     */
    public Payment(String cardNumber, int expirationMonth, int expirationYear, int cvc, PaymentStrategy paymentMethod){
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvc = cvc;
        this.paymentStrategy = paymentMethod;
        this.paid = false;
    }

    /**
     * Charges a payment based on the booking and payment info
     * @param booking The booking to pay for
     */

    public void processPayment(Booking booking){
        this.paid = this.paymentStrategy.processPayment(this.cardNumber, this.expirationYear, this.expirationMonth, this.cvc, booking.getTotalCost());
    }

    /**
     * Getter to indicate payment success
     * @return true if successful
     */
    public boolean getPaymentSuccess(){return this.paid;}
}
