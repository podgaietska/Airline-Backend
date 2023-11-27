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

//	@Autowired
//	private edu.ensf480.airline.repository.FlightRepository flightRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Flight flight = new Flight(
//				"AC382",
//				"Calgary",
//				"Toronto",
//				LocalTime.of(11, 0),
//				LocalTime.of(16, 45),
//				"3 hr 45 min",
//				LocalDate.of(2023, 12, 28)
//				);
//
//		Set<Seat> seats = new HashSet<>();
//
//		int businessClassEnd = 6; // Rows 1-6
//		int comfortClassStart = 7, comfortClassEnd = 16; // Rows 7-16
//
//		for(int row = 1; row <= 30; row++ ){
//			char[] seatLetters = row <= businessClassEnd ? new char[] {'A', 'B'} : new char[] {'A', 'B', 'C', 'D'};
//			for (char seatLetter : seatLetters) {
//				String seatNumber = row + String.valueOf(seatLetter);
//				String seatClass;
//				if (row <= businessClassEnd) {
//					seatClass = "Business";
//				} else if (row >= comfortClassStart && row <= comfortClassEnd) {
//					seatClass = "Comfort";
//				} else {
//					seatClass = "Ordinary";
//				}
//				seats.add(new Seat(seatNumber, seatClass, flight, false));
//			}
//
//		}
//
//		flight.setSeats(seats);
//		flightRepository.save(flight);
//
//		Flight flight2 = new Flight(
//				"AC005",
//				"Calgary",
//				"Vancouver",
//				LocalTime.of(19, 10),
//				LocalTime.of(19, 55),
//				"1 hr 45 min",
//				LocalDate.of(2024, 1, 3)
//		);
//
//		Set<Seat> seats2 = new HashSet<>();
//
//		int businessClassEnd2 = 6; // Rows 1-6
//		int comfortClassStart2 = 7, comfortClassEnd2 = 16; // Rows 7-16
//
//		for(int row = 1; row <= 30; row++ ){
//			char[] seatLetters = row <= businessClassEnd ? new char[] {'A', 'B'} : new char[] {'A', 'B', 'C', 'D'};
//			for (char seatLetter : seatLetters) {
//				String seatNumber = row + String.valueOf(seatLetter);
//				String seatClass;
//				if (row <= businessClassEnd) {
//					seatClass = "Business";
//				} else if (row >= comfortClassStart && row <= comfortClassEnd) {
//					seatClass = "Comfort";
//				} else {
//					seatClass = "Ordinary";
//				}
//				seats2.add(new Seat(seatNumber, seatClass, flight, false));
//			}
//
//		}
//
//		flight2.setSeats(seats2);
//		flightRepository.save(flight2);
//	}
}
