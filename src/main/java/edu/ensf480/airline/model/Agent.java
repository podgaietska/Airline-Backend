package edu.ensf480.airline.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "agents")
public class Agent extends User {
    @Id
    protected long EmployeeId;

    /**
     * @param fname - First name of the user
     * @param lname - Last name of the user
     * @param email - Email address of the user
     * @param phone - Phone number of the user
     */
    public Agent(String fname, String lname, String email, String phone) {
        super(fname, lname, email, phone);
        this.EmployeeId = generateEmployeeId();
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
}
