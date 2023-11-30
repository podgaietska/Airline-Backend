package edu.ensf480.airline.dto;

import java.time.LocalDate;

public class BookingRequest {
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String cardNumber;

    private String province;
    private int expirationMonth;
    private int expirationYear;
    private int cvv;
    private String paymentStrategy;

    public BookingRequest() {
    }

    public BookingRequest(String fname, String lname, String email, String phone, LocalDate dateOfBirth, String cardNumber, int expirationMonth, int expirationYear, int cvv, String paymentStrategy) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvv = cvv;
        this.paymentStrategy = paymentStrategy;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public int getExpirationMonth() {
        return this.expirationMonth;
    }

    public int getExpirationYear() {
        return this.expirationYear;
    }

    public int getCvv() {
        return this.cvv;
    }

    public String getPaymentStrategy() {
        return this.paymentStrategy;
    }

    public String getProvince() {
        return this.province;
    }

}
