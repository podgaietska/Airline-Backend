package edu.ensf480.airline.controller;

import edu.ensf480.airline.model.RegisteredUser;
import edu.ensf480.airline.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    @PostMapping("/login")
        public ResponseEntity<RegisteredUser> login(@RequestBody RegisteredUser registeredUser){
            RegisteredUser user = registeredUserService.login(registeredUser.getEmail(), registeredUser.getPassword());
            if(user != null){
                return ResponseEntity.ok(user);
            } else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

    @PostMapping("/register")
        public ResponseEntity<RegisteredUser> register(@RequestBody RegisteredUser registeredUser){
            try{
                RegisteredUser newUser = registeredUserService.register(registeredUser);
                return ResponseEntity.ok(newUser);
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }



}
