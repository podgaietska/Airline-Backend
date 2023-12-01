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
@Table(name = "passengers")
public class Passenger extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "date_of_birth", nullable = false)
    protected LocalDate dateOfBirth;

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     * @param dateOfBirth - Date of birth of the user
     */
    public Passenger(String fname, String lname, String email, String phone, LocalDate dateOfBirth) {
        super(fname, lname, email, phone);
        this.dateOfBirth = dateOfBirth;
    }

    public Passenger() {
        super();
    }

    /**
     * Getter for id
     * @return the id: UUID
     */
    public long getId() {
        return id;
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
