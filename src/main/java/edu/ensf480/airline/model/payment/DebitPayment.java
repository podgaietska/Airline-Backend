package edu.ensf480.airline.model.payment;

/**
 * chargeDebitCard class for the Airline Reservation System
 *
 * This class is used to take debit card info and process it through a third party service
 *
 * @version 1.0
 * @since 2023-11-26
 */
public class DebitPayment implements PaymentStrategy {

    public DebitPayment() {
    }

    @Override
    public boolean processPayment(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount) {
        /*
         * Use third party service to charge a debit card
         */
        return true;
    }

    @Override
    public boolean refundPayment(String cardNumber, int expirationYear, int expirationMonth, int cvc, double amount) {
        /*
         * Use third party service to charge a debit card
         */
        return true;
    }
}
