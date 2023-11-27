package edu.ensf480.airline.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ensf480.airline.repository.RegisteredUserRepository;

import edu.ensf480.airline.model.RegisteredUser;

import java.util.Optional;

@Service
public class RegisteredUserService{
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository){
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUser login(String email, String password){
        Optional<RegisteredUser> registeredUser = registeredUserRepository.findByEmail(email);
        if(registeredUser.isPresent() && registeredUser.get().getPassword().equals(password)){
            return registeredUser.get();
        }
        return null;
    }

    public RegisteredUser register(RegisteredUser newUser) throws Exception{
        if (registeredUserRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }
        return registeredUserRepository.save(newUser);
    }
}
