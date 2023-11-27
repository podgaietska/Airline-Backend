package edu.ensf480.airline.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * RegisteredUser class for the Airline Reservation System
 *
 * This class is used to represent a user that is a registered member of the Airline.
 *
 * @version 1.0
 * @since 2021-03-20
 */
@Entity
@Table(name = "registered_users")
public class RegisteredUser extends User {
    private static final int MEMBERSHIP_NUM_LENGTH = 9;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "membership_num", nullable = false)
    private String membershipNum;

    @Embedded
    private Address address;

    public RegisteredUser() {
        super();
    }

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     * @param dateOfBirth - Date of birth of the user
     * @param password - Password of the user
     */
    public RegisteredUser(String fname, String lname, String email, String phone, LocalDate dateOfBirth, String password) {
        super(fname, lname, email, phone, dateOfBirth);
        this.password = password;
    }

    /**
     * Getter for password
     *
     * @return the password: String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     *
     * @param password - Password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for membershipNum
     *
     * @return the membershipNum: String
     */
    public String getMembershipNum() {
        return membershipNum;
    }

    /**
     * Setter for membershipNum
     *
     * @param membershipNum - Membership number of the user
     */
    public void setMembershipNum(String membershipNum) {
        this.membershipNum = membershipNum;
    }

    /**
     * Getter for address
     *
     * @return the address: Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for address
     *
     * @param address - object that describes the address of the user
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Generates a random membership number for the user
     *
     * @return the membershipNum: String
     */

    public String generateMembershipNum() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(MEMBERSHIP_NUM_LENGTH);
        for (int i = 0; i < MEMBERSHIP_NUM_LENGTH; i++) {
            sb.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return sb.toString();
    }


}
