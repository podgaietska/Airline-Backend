package edu.ensf480.airline.service;
import edu.ensf480.airline.model.Booking;
import edu.ensf480.airline.repository.BookingRepository;
import edu.ensf480.airline.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ensf480.airline.repository.MemberRepository;
import edu.ensf480.airline.model.Passenger;

import edu.ensf480.airline.model.Member;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, PassengerRepository passengerRepository, BookingRepository bookingRepository){
        this.memberRepository = memberRepository;
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
    }

    public Member login(String email, String password) throws Exception{
        Optional<Member> registeredUser = memberRepository.findByEmail(email);
        if(registeredUser.isPresent() && registeredUser.get().getPassword().equals(password)){
            return registeredUser.get();
        }
        throw new Exception("Invalid credentials. Please try again.");
    }

    @Transactional
    public Member register(Member newMember) throws Exception{
        if (memberRepository.findByEmail(newMember.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }

        Passenger passenger = passengerRepository.findByEmail(newMember.getEmail());

        if (passenger != null) {
            //passenger present with the same email
            List<Booking> bookings = bookingRepository.findByUser(passenger);
            if (bookings.size() > 0) {
                // if passenger has bookings

                //generate membership number
                String membershipNum;
                do {
                    membershipNum = newMember.generateMembershipNum();
                } while (memberRepository.existsByMembershipNum(membershipNum));
                newMember.setMembershipNum(membershipNum);

                //save new member to database
                Member savedMember = memberRepository.save(newMember);

                //update bookings and assign to new member
                for (Booking booking : bookings) {
                    booking.setUser(savedMember);
                    bookingRepository.save(booking);
                }
                //delete old passenger (duplicate) from database
                passengerRepository.delete(passenger);
                //return new member
                return savedMember;

            } else{
                //if passenger has no bookings
                //delete old passenger (duplicate) from database
                passengerRepository.delete(passenger);
            }

        }
        //generate membership number
        String membershipNum;
        do {
            membershipNum = newMember.generateMembershipNum();
        } while (memberRepository.existsByMembershipNum(membershipNum));
        newMember.setMembershipNum(membershipNum);
        //save new member to database
        return memberRepository.save(newMember);
    }
}
