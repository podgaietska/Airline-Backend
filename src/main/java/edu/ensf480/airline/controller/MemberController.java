package edu.ensf480.airline.controller;

import edu.ensf480.airline.model.Member;
import edu.ensf480.airline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class MemberController {

    private final MemberService registeredUserService;

    @Autowired
    public MemberController(MemberService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Member registeredUser){
        try {
            Member user = registeredUserService.login(registeredUser.getEmail(), registeredUser.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e){
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid credentials, please try again");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }
        }

    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody Member registeredUser){
            try{
                Member newUser = registeredUserService.register(registeredUser);
                return ResponseEntity.ok(newUser);
            } catch (Exception e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());

                // Return the map instead of the exception object
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }
        }
}
