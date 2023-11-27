package edu.ensf480.airline.controller;

import edu.ensf480.airline.model.RegisteredUser;
import edu.ensf480.airline.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody RegisteredUser registeredUser){
            RegisteredUser user = registeredUserService.login(registeredUser.getEmail(), registeredUser.getPassword());
            if(user != null){
                return ResponseEntity.ok(user);
            } else{
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid credentials, please try again");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
        }

    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody RegisteredUser registeredUser){
            try{
                RegisteredUser newUser = registeredUserService.register(registeredUser);
                return ResponseEntity.ok(newUser);
            } catch (Exception e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());

                // Return the map instead of the exception object
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }
        }



}
