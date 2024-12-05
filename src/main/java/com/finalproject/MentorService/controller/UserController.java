package com.finalproject.MentorService.controller;
import com.finalproject.MentorService.model.*;
import com.finalproject.MentorService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {

    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public UserController(UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.registerUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }

    @PostMapping("/mentors")
    public ResponseEntity<Mentor> registerAsMentor(@RequestBody Mentor mentor) {
        try {
            Mentor createdUser = userService.registerMentor(mentor);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }
    @PostMapping("/mentees")
    public ResponseEntity<Mentee> registerAsMentee(@RequestBody Mentee mentee) {
        try {
            Mentee createdUser = userService.registerMentor(mentee);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }

    @PostMapping("/followRequests")
    public ResponseEntity<FollowRequest> followUser(@RequestBody FollowRequest followRequest) {
        try {
            FollowRequest followRequests = userService.followRequest(followRequest);
            return ResponseEntity.ok(followRequests);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/follows")
    private ResponseEntity<Follow> follow(@RequestBody Follow follow) {
        try {
            Follow follows = userService.follow(follow);
            return ResponseEntity.ok(follows);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/followRequests/{requestId}")
    public ResponseEntity<Void> cancelFollowRequest(@PathVariable String requestId) {
        try {
            userService.cancelFollowRequest(requestId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/users")
    public  ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/mentors")
    public ResponseEntity<List<Mentor>> getAllMentors() {
        try {
            List<Mentor> mentors = userService.getAllMentors();
            return ResponseEntity.ok(mentors);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/mentees")
    public ResponseEntity<List<Mentee>> getAllMentees() {
        try {
            List<Mentee> mentees = userService.getAllMentees();
            return ResponseEntity.ok(mentees);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/followRequests")
    public ResponseEntity<List<FollowRequest>> getFollowRequests(
            @RequestParam(required = false) String menteeId,
            @RequestParam(required = false) String mentorId,
            @RequestParam(required = false) String status) {
        try {
            List<FollowRequest> followRequests;
            if(mentorId != null && status != null) {
                followRequests = userService.getFollowRequestForMentorIdAndStatus(mentorId, status);
            }
            else if (menteeId != null) {
                followRequests = userService.getFollowRequestsForMenteeId(menteeId);
            } else if (mentorId != null) {
                followRequests = userService.getFollowRequestsForMentorId(mentorId);
            } else {
                followRequests = userService.getAllFollowRequests();
            }
            return ResponseEntity.ok(followRequests);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/follows")
    public ResponseEntity<List<Follow>> getAllFollows(
            @RequestParam(required = false) String menteeId,
            @RequestParam(required = false) String mentorId,
            @RequestParam(required = false) String status)
    {
        try {
            List<Follow> follows;
            if (menteeId != null) {
                follows = userService.getFollowsForMenteeId(menteeId);
            } else if (mentorId != null) {
                follows = userService.getFollowsForMentorId(mentorId);
            } else {
                follows = userService.getAllFollows();
            }
            return ResponseEntity.ok(follows);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/mentors/{mentorId}")
    public ResponseEntity<Optional<Mentor>> getMentorById(@PathVariable String mentorId) {
        try {
            Optional<Mentor> mentor = userService.getMentorById(mentorId);
            if (mentor != null) {
                return ResponseEntity.ok(mentor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/mentees/{menteeId}")
    public ResponseEntity<Optional<Mentee>> getMenteeById(@PathVariable String menteeId) {
        try {
            Optional<Mentee> mentee = userService.getMenteeById(menteeId);
            if (mentee != null) {
                return ResponseEntity.ok(mentee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/followRequests/{requestId}")
    public ResponseEntity<FollowRequest> updateFollowRequest(@PathVariable String requestId, @RequestBody Map<String, String> updates) {
        try {
            FollowRequest updatedRequest = userService.updateFollowRequestStatus(requestId, updates.get("status"));
            if (updatedRequest != null) {
                return ResponseEntity.ok(updatedRequest);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message createdMessage = userService.createMessage(message);
            if (createdMessage != null) {
                messagingTemplate.convertAndSend("/topic/return", createdMessage);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@RequestParam(required = false) String senderId, @RequestParam(required = false) String receiverId) {
        try {
            List<Message> messages;
            if (senderId != null && receiverId != null) {
                messages = userService.findMessagesBetweenUsers(senderId, receiverId);
            } else {
                messages = userService.findAllMessages();
            }
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Request received successfully");
    }

    @PatchMapping("/mentors/{mentorId}")
    public ResponseEntity<Mentor> updateMentorProfile(@PathVariable String mentorId, @RequestBody Mentor updatedMentor) {
        try {
            Mentor mentor = userService.updateMentorProfile(mentorId, updatedMentor);
            return ResponseEntity.ok(mentor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/mentees/{menteeId}")
    public ResponseEntity<Mentee> updateMenteeProfile(@PathVariable String menteeId, @RequestBody Mentee updatedMentee) {
        System.out.println("Mentee ID: " + menteeId);
        try {
            Mentee mentee = userService.updateMenteeProfile(menteeId, updatedMentee);
            return ResponseEntity.ok(mentee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

