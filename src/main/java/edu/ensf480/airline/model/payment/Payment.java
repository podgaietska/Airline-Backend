package edu.ensf480.airline.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ensf480.airline.model.Booking;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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

    @Column(name = "payment_type", nullable = false)
    private String strategyName;

    @Transient //will not be saved in the database
    @JsonIgnore
    private int cvc;

    @Transient //will not be saved in the database
    @JsonIgnore
    private PaymentStrategy paymentStrategy;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    /**
     * Charges a payment based on the booking and customer info
     * @param cardNumber credit/debit card number
     * @param expirationYear credit/debit card year expiry
     * @param expirationMonth credit/debit card month expiry
     * @param cvc credit/debit card cvc expiry
     */
    public Payment(String cardNumber, int expirationMonth, int expirationYear, int cvc){
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvc = cvc;
        this.paid = false;
    }

    /**
     * Charges a payment based on the booking and payment info
     * @param totalCost - the cost of the booking
     */

    public void processPayment(double totalCost){
        this.paid = this.paymentStrategy.pay(this.cardNumber, this.expirationYear, this.expirationMonth, this.cvc, totalCost);
        strategyName = paymentStrategy.strategyToString();
    }

    public void processRefund(double totalCost){
        if (Objects.equals(strategyName, "Debit")){
            paymentStrategy = new chargeDebitCard();
        }
        else{
            paymentStrategy = new chargeCreditCard();
        }

        this.paid = this.paymentStrategy.refundPayment(this.cardNumber, this.expirationYear, this.expirationMonth, this.cvc, totalCost);
    }


    /**
     * Getter for id
     * @return id
     */

    public long getId() {
        return id;
    }

    /**
     * Getter to indicate payment success
     * @return true if successful
     */
    public boolean getPaymentSuccess(){return this.paid;}

    /**
     * Getter for card number
     * @return card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Getter for expiration month
     * @return expiration month
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * Getter for expiration year
     * @return expiration year
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * Getter for cvc
     * @return cvc
     */
    public int getCvc() {
        return cvc;
    }

    /**
     * Getter for payment strategy
     * @return payment strategy
     */
    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    /**
     * Setter for payment strategy
     * @param paymentStrategy - the payment strategy to set
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * To string method
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationMonth=" + expirationMonth +
                ", expirationYear=" + expirationYear +
                ", cvc=" + cvc +
                ", paymentStrategy=" + paymentStrategy +
                ", paid=" + paid +
                '}';
    }

}
