package edu.ensf480.airline;

import edu.ensf480.airline.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import edu.ensf480.airline.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AirlineBackendApplication{

	public static void main(String[] args) {
		SpringApplication.run(AirlineBackendApplication.class, args);
	}

}
