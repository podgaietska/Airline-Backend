package edu.ensf480.airline.model;

/**
 * CollectPayment interface for the Airline Reservation System
 *
 * This class is used to apply a strategy pattern to payment type
 *
 * @version 1.0
 * @since 2023-11-26
 */
public interface CollectPayment {
    boolean processPayment(int cardNumber,int year,int month,int cvc,double total);
}
