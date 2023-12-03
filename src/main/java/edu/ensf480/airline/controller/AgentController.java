package edu.ensf480.airline.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.ensf480.airline.model.Agent;
import edu.ensf480.airline.model.Member;
import edu.ensf480.airline.model.Passenger;
import edu.ensf480.airline.model.ScheduledEmail;
import edu.ensf480.airline.repository.MemberRepository;
import edu.ensf480.airline.service.AgentService;
import edu.ensf480.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/agent")
public class AgentController {
    private final AgentService agentService;

    private final MemberRepository memberRepository;

    @Autowired
    public AgentController(AgentService agentService, MemberRepository memberRepository){
        this.agentService = agentService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/{flightNumber}/passengers")
    public ResponseEntity<?> getPassengersOnFlight(@PathVariable String flightNumber){
        try {
            List<Passenger> passengers = agentService.getPassengersOnFlight(flightNumber);
            return ResponseEntity.ok(passengers);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAgent(@RequestBody Agent agent){
        try {
            Agent newAgent = agentService.register(agent);
            return ResponseEntity.ok(newAgent);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Agent agent){
        try {
            Agent newAgent = agentService.login(agent.getEmployeeId(), agent.getPassword());
            return ResponseEntity.ok(newAgent);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/promotion/send")
    public ResponseEntity<?> sendPromotionEmail(@RequestBody JsonNode request){
        try {
            String subject = request.get("subject").asText();
            String message = request.get("message").asText();

            ScheduledEmail promotionEmail = ScheduledEmail.getNewsletterEmail();
            promotionEmail.setMessage(subject,message);

            List<Member> members = memberRepository.findAll();
            for(Member member : members){
                System.out.println(member.getEmail());
                String email = member.getEmail();
                promotionEmail.addEmail(email);
            }

            promotionEmail.sendEmail();
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("success", "Emails sent successfully");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(successResponse);
        } catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

//    @PostMapping("/newsletter/time")
//    public ResponseEntity<?> setNewsletterTime(@RequestBody JsonNode request){
//        try{
//            int spacing = request.get("daySpacing").asInt(30);
//            int dayofMonth = request.get("dayOfMonth").asInt(1);
//            int hour = request.get("hour").asInt(12);
//            int minute = request.get("minute").asInt(0);
//
//            ScheduledEmail newsletter = ScheduledEmail.getNewsletterEmail();
//            newsletter.setTimer(spacing,dayofMonth,hour,minute);
//            return ResponseEntity.ok(newsletter);
//        } catch (Exception e){
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//        }
//    }

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

//    /**
//     * Designed for testing
//     */
//    @PostMapping("/newsletter/emailList")
//    public ResponseEntity<?> setEmailList(@RequestBody JsonNode request){
//        try{
//            String email = request.get("email").asText();
//            String[] emails = new String[1];
//            emails[0] = email;
//
//            ScheduledEmail newsletter = ScheduledEmail.getNewsletterEmail();
//            newsletter.setEmailList(emails);
//            return ResponseEntity.ok(newsletter);
//        } catch (Exception e){
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", e.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//        }
//    }

}
