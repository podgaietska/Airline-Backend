package edu.ensf480.airline.dto;

import edu.ensf480.airline.model.User;
import edu.ensf480.airline.model.payment.Payment;

public class BookingRequestGuest {
    User user;
    Payment payment;
    private Long flightId;
    private Long seatId;

    public BookingRequestGuest() {
    }

    public BookingRequestGuest(User user, Payment payment, Long flightId, Long seatId) {
        this.user = user;
        this.payment = payment;
        this.flightId = flightId;
        this.seatId = seatId;
    }
}
