package edu.ensf480.airline.model.payment;

/**
 * chargeCreditCard class for the Airline Reservation System
 *
 * This class is used to take credit card info and process it through a third party service
 *
 * @version 1.0
 * @since 2023-11-26
 */
public class CreditPayment implements PaymentStrategy {

    public CreditPayment() {
    }

    @Override
    public boolean processPayment(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount) {
        /*
         * Use third party service to charge a credit card
         */
        return true;
    }

    @Override
    public boolean refundPayment(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount) {
        /*
         * Use third party service to charge a credit card
         */
        return true;
    }
}