package edu.ensf480.airline.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ensf480.airline.repository.MemberRepository;

import edu.ensf480.airline.model.Member;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository registeredUserRepository;

    @Autowired
    public MemberService(MemberRepository registeredUserRepository){
        this.registeredUserRepository = registeredUserRepository;
    }

    public Member login(String email, String password) throws Exception{
        Optional<Member> registeredUser = registeredUserRepository.findByEmail(email);
        if(registeredUser.isPresent() && registeredUser.get().getPassword().equals(password)){
            return registeredUser.get();
        }
        throw new Exception("Invalid credentials. Please try again.");
    }

    public Member register(Member newUser) throws Exception{
        if (registeredUserRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }

        String membershipNum;

        do {
            membershipNum = newUser.generateMembershipNum();
        } while (registeredUserRepository.existsByMembershipNum(membershipNum));

        newUser.setMembershipNum(membershipNum);

        return registeredUserRepository.save(newUser);
    }
}
