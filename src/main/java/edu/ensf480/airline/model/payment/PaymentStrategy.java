package edu.ensf480.airline.model.payment;

/**
 * CollectPayment interface for the Airline Reservation System
 *
 * This class is used to apply a strategy pattern to payment type
 *
 * @version 1.0
 * @since 2023-11-26
 */
public interface PaymentStrategy {
    boolean pay(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount);
    boolean refundPayment(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount);
    String strategyToString();
}
