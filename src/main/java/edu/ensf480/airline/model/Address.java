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

@Embeddable
@NoArgsConstructor
public class Address {
    private String street;

    private String city;

    private String country;

    private String postalCode;

    private String apartment;

    /**
     * @param street     - Street address of the mailing address
     * @param city       - City of the mailing address
     * @param country    - Country of the mailing address
     * @param postalCode - Postal code of the mailing address
     * @param apartment  - Apartment number of the mailing address
     */

    public Address(String street, String city, String country, String postalCode, String apartment) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.apartment = apartment;
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
