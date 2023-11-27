package edu.ensf480.airline.model;

import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * User class for the Airline Reservation System
 *
 * This class is used to represent a user in the Airline Reservation System.
 *
 * @version 1.0
 * @since 2021-03-20
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "first_name", nullable = false)
    protected String fname;

    @Column(name = "last_name", nullable = false)
    protected String lname;

    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "phone") // can be blank
    protected String phone;

    @Column(name = "date_of_birth", nullable = false)
    protected LocalDate dateOfBirth;

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     */
    public User(String fname, String lname, String email, String phone, LocalDate dateOfBirth) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter for id
     * @return the id: UUID
     */
    public long getId() {
        return id;
    }


    /**
     * Getter for fname
     * @return fname: String
     */
    public String getFname() {
        return fname;
    }

    /**
     * Setter for fname
     * @param fname: String
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Getter for lname
     * @return lname: String
     */
    public String getLname() {
        return lname;
    }

    /**
     * Setter for lname
     * @param lname: String
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Getter for email
     * @return email: String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email: String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for phone
     * @return phone: String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for phone
     * @param phone: String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for dateOfBirth
     * @return dateOfBirth: LocalDate
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter for dateOfBirth
     * @param dateOfBirth: LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
