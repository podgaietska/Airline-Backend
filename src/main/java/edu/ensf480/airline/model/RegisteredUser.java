package edu.ensf480.airline.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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
public class RegisteredUser extends User{
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "membership_num", nullable = false)
    private String membershipNum;

    /**
     * @param password - Password of the user
     * @param membershipNum - Membership number of the user
     */

    public RegisteredUser(String fname, String lname, String email, String phone, LocalDate dateOfBirth, String password, String membershipNum) {
        super(fname, lname, email, phone, dateOfBirth);
        this.password = password;
        this.membershipNum = membershipNum;
    }

    public RegisteredUser() {
        super();
    }

    /**
     * Getter for password
     * @return the password: String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for membershipNum
     * @return the membershipNum: String
     */
    public String getMembershipNum() {
        return membershipNum;
    }

}
