package edu.ensf480.airline.model;

import edu.ensf480.airline.model.payment.CalculateTaxRate;
import edu.ensf480.airline.model.payment.PaymentStrategy;
import edu.ensf480.airline.model.payment.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long BookingNumber;

    @Column(name = "cancellation_insurance", nullable = false)
    private boolean cancellationInsurance = false;

    @Column(name = "payment_total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Booking(Flight flight, User user, Seat seat) {
        this.flight = flight;
        this.user = user;
        this.seat = seat;
        calculateCost();
    }

    /**
     * Determines the booking cost based on seat price, cancellation insurance, provincial and canadian taxes
     */
    public void calculateCost(){
        total = seat.getCost();
        if (cancellationInsurance) total *= cancellationInsuranceCost;

        double taxRate = CalculateTaxRate.getGST();
//        for (CalculateTaxRate province:CalculateTaxRate.values()){
//            if(province.toString()==user.getAddress.getProvince()){
//                taxRate += province.getPST();
//            }
//        }

        total *= taxRate;

    }

    /**
     * Pays for booking by using class Payment
     * @param cardNumber credit/debit card number
     * @param year credit/debit card year expiry
     * @param month credit/debit card month expiry
     * @param cvc credit/debit card cvc expiry
     * @param paymentType Strategy pattern selecting credit or debit card
     */

    public void createPayment(int cardNumber, int year, int month, int cvc, CollectPayment paymentType){
        calculateCost();
        payment = new Payment();
        payment.chargePayment(this,cardNumber,year,month,cvc,paymentType);

        if (payment.getPaymentSuccess()) {
            SendEmail confirmation = new SendEmail();
            confirmation.SendBookingEmail(this);
        }
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
    public long getBookingNumber() {
        return BookingNumber;
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
     * Getter for user
     *
     * @return the user
     */
    public User getUser() {
        return user;
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
}
