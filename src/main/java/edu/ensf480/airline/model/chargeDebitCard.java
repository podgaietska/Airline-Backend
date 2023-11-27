package edu.ensf480.airline.model;

/**
 * chargeDebitCard class for the Airline Reservation System
 *
 * This class is used to take debit card info and process it through a third party service
 *
 * @version 1.0
 * @since 2023-11-26
 */
public class chargeDebitCard implements CollectPayment{
    @Override
    public boolean processPayment(int cardNumber, int year, int month, int cvc, double total) {
        /*
         * Use third party service to charge a debit card
         */
        boolean DebitCardChargeSuccess = true;


        return DebitCardChargeSuccess;
    }
}
