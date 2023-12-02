package edu.ensf480.airline.model;

import edu.ensf480.airline.dto.BookingRequest;
import edu.ensf480.airline.model.payment.*;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Random;

/**
 * Booking class for the Airline Reservation System
 *
 * This class is used to represent a booking in the Airline Reservation System.
 *
 * @version 1.1
 * @since 2021-03-20
 */

@Entity
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    private String bookingNumber;

    @Column(name = "cancellation_insurance", nullable = false)
    private boolean cancellationInsurance = false;

    @Column(name = "payment_total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Passenger user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    private static final double cancellationInsuranceCost = 1.25;

    /**
     * @param flight        - Flight of the booking
     * @param user          - User of the booking
     * @param seat          - Seat of the booking
     */

    public Booking(Flight flight, Passenger user, Seat seat, BookingRequest bookingDetails) {
        this.payment = new Payment(bookingDetails.getCardNumber(), bookingDetails.getExpirationMonth(), bookingDetails.getExpirationYear(), bookingDetails.getCvv());
        this.bookingNumber = generateBookingNumber();
        this.flight = flight;
        this.user = user;
        this.seat = seat;
        calculateCost(bookingDetails.getProvince());
    }

    /**
     * Determines the booking cost based on seat price, cancellation insurance, provincial and canadian taxes
     */
    public void calculateCost(String userProvince){
        total = seat.getCost();
        if (cancellationInsurance) total *= cancellationInsuranceCost;

        double taxRate = CalculateTaxRate.getGST();
        for (CalculateTaxRate province:CalculateTaxRate.values()){
            if(Objects.equals(province.toString(), userProvince)){
                taxRate += province.getPST();
            }
        }
        total += total*taxRate;
    }

    /**
     * Pays for booking by using class Payment
     */

    public void chargeCard(){
        this.payment.processPayment(total);

        if (payment.getPaymentSuccess()) {
            SendEmail confirmation = new SendEmail();
            confirmation.SendBookingEmail(this);
        }
    }

    public void refundPayment(){
        this.payment.processRefund(total);
    }

    /**
     *
     * @param bookingDetails
     * @throws Exception
     */
    public void setPaymentStrategy(BookingRequest bookingDetails) throws Exception{
        PaymentStrategy paymentStrategy;

        if (bookingDetails.getPaymentStrategy().equals("debit")){
            paymentStrategy = new chargeDebitCard();
        } else if (bookingDetails.getPaymentStrategy().equals("credit")){
            paymentStrategy = new chargeCreditCard();
        } else{
            throw new Exception("Payment strategy not found");
        }

        this.payment.setPaymentStrategy(paymentStrategy);
    }

    private String generateBookingNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }



    /**
     * Getter for cancellation insurance
     *
     * @return the cancellation insurance
     */
    public boolean getCancellationInsurance() {
        return cancellationInsurance;
    }

    /**
     * Setter for cancellation insurance
     *
     * @param cancellationInsurance - the cancellation insurance
     */
    public void setCancellationInsurance(boolean cancellationInsurance){
        this.cancellationInsurance = cancellationInsurance;
    }

    /**
     * Getter for booking number
     *
     * @return the booking number
     */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Getter for flight
     *
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Setter for flight
     *
     * @param flight - the flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Getter for user
     *
     * @return the user
     */
    public Passenger getUser() {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user - the user
     */
    public void setUser(Passenger user) {
        this.user = user;
    }

    /**
     * Getter for seat
     *
     * @return the seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Setter for seat
     *
     * @param seat - the seat
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Getter for payment
     *
     * @return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Getter for total cost
     *
     * @return the total cost
     */
    public double getTotalCost() {
        return total;
    }

    public void setPayment(Payment savedPayment) {
        this.payment = savedPayment;
    }
}
