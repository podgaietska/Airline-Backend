package edu.ensf480.airline.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "agents")
public class Agent extends User {
    @Id
    protected long employeeId;

    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     * @param password - Password of the user
     */
    public Agent(String fname, String lname, String email, String phone, String password) {
        super(fname, lname, email, phone);
        this.password = password;
    }

    public Agent() {
        super();
    }

    public Long generateEmployeeId() {
        long currentTimeMillis = System.currentTimeMillis();
        int randomPart = new Random().nextInt(9999); // 4 digit random number
        String idString = currentTimeMillis + String.format("%04d", randomPart); // Combining with currentTimeMillis
        return Long.parseLong(idString);
    }

    /**
     * Getter for employeeId
     *
     * @return the employeeId: long
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * Setter for employeeId
     *
     * @param EmployeeId - Employee ID of the user
     */
    public void setEmployeeId(long EmployeeId) {
        this.employeeId = EmployeeId;
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
}
