package edu.ensf480.airline.model;

import jakarta.persistence.*;
@MappedSuperclass
public abstract class User {

    @Column(name = "first_name", nullable = false)
    protected String fname;
    @Column(name = "last_name", nullable = false)
    protected String lname;

    @Column(name = "email", nullable = false)
    protected String email;
    @Column(name = "phone") // can be blank
    protected String phone;

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     */
    protected User(String fname, String lname, String email, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    protected User() {
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
}
