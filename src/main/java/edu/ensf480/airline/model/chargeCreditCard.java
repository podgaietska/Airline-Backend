package edu.ensf480.airline.model;

/**
 * chargeCreditCard class for the Airline Reservation System
 *
 * This class is used to take credit card info and process it through a third party service
 *
 * @version 1.0
 * @since 2023-11-26
 */
public class chargeCreditCard implements CollectPayment{
    @Override
    public boolean processPayment(int cardNumber, int year, int month, int cvc, double total) {
        /*
         * Use third party service to charge a credit card
         */
        boolean CreditCardChargeSuccess = true;


        return CreditCardChargeSuccess;
    }
}