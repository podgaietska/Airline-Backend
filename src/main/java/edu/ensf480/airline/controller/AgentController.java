package edu.ensf480.airline.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.ensf480.airline.model.Flight;
import edu.ensf480.airline.model.ScheduledEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

    @PostMapping("/newsletter/time")
    public ResponseEntity<?> setNewsletterTime(@RequestBody JsonNode request){
        try{
            int spacing = request.get("daySpacing").asInt(30);
            int dayofMonth = request.get("dayOfMonth").asInt(1);
            int hour = request.get("hour").asInt(12);
            int minute = request.get("minute").asInt(0);

            ScheduledEmail newsletter = ScheduledEmail.getNewsletterEmail();
            newsletter.setTimer(spacing,dayofMonth,hour,minute);
            return ResponseEntity.ok(newsletter);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @PostMapping("/newsletter/message")
    public ResponseEntity<?> setNewsletterMessage(@RequestBody JsonNode request){
        try{
            String subject = request.get("subject").asText();
            String message = request.get("message").asText();

            ScheduledEmail newsletter = ScheduledEmail.getNewsletterEmail();
            newsletter.setMessage(subject,message);
            return ResponseEntity.ok(newsletter);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @PostMapping("/newsletter/emailList")
    public ResponseEntity<?> setEmailList(@RequestBody JsonNode request){
        try{
            String email = request.get("email").asText();
            String[] emails = new String[1];
            emails[0] = email;


            ScheduledEmail newsletter = ScheduledEmail.getNewsletterEmail();
            newsletter.setEmailList(emails);
            return ResponseEntity.ok(newsletter);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

}
