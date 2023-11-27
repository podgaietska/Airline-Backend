package edu.ensf480.airline.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Address class for the Airline Reservation System
 *
 * This class is used to represent an address of a user in the Airline Reservation System.
 *
 * @version 1.0
 * @since 2021-03-20
 */

@Entity
@NoArgsConstructor
@Table(name = "mailing_addresses")
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street_address", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "apartment") // can be blank
    private String apartment;

    /**
     * @param id         - Unique identifier for the mailing address
     * @param street     - Street address of the mailing address
     * @param city       - City of the mailing address
     * @param country    - Country of the mailing address
     * @param postalCode - Postal code of the mailing address
     * @param apartment  - Apartment number of the mailing address
     */

    public BillingAddress(long id, String street, String city, String country, String postalCode, String apartment) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.apartment = apartment;
    }

    /**
     * Getter for id
     * @return the id: UUID
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for street
     * @return the street: String
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for street
     * @param street - Street address of the mailing address
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter for city
     * @return the city: String
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for city
     * @param city - City of the mailing address
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for country
     * @return the country: String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for country
     * @param country - Country of the mailing address
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter for postalCode
     * @return the postalCode: String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for postalCode
     * @param postalCode - Postal code of the mailing address
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for apartment
     * @return the apartment: String
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Setter for apartment
     * @param apartment - Apartment number of the mailing address
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
