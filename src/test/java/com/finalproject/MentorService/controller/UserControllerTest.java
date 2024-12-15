package com.finalproject.MentorService.controller;
import com.finalproject.MentorService.model.*;
import com.finalproject.MentorService.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_success() {
        User user = new User();
        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void registerUser_conflict() {
        when(userService.registerUser(any(User.class))).thenThrow(new RuntimeException());

        ResponseEntity<User> response = userController.registerUser(new User());

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void registerAsMentor_success() {
        Mentor mentor = new Mentor();
        when(userService.registerMentor(any(Mentor.class))).thenReturn(mentor);

        ResponseEntity<Mentor> response = userController.registerAsMentor(mentor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentor, response.getBody());
    }

    @Test
    void registerAsMentor_conflict() {
        when(userService.registerMentor(any(Mentor.class))).thenThrow(new RuntimeException());

        ResponseEntity<Mentor> response = userController.registerAsMentor(new Mentor());

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void registerAsMentee_success() {
        Mentee mentee = new Mentee();
        when(userService.registerMentor(any(Mentee.class))).thenReturn(mentee);

        ResponseEntity<Mentee> response = userController.registerAsMentee(mentee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentee, response.getBody());
    }

    @Test
    void registerAsMentee_conflict() {
        when(userService.registerMentor(any(Mentee.class))).thenThrow(new RuntimeException());

        ResponseEntity<Mentee> response = userController.registerAsMentee(new Mentee());

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void followUser_success() {
        FollowRequest followRequest = new FollowRequest();
        when(userService.followRequest(any(FollowRequest.class))).thenReturn(followRequest);

        ResponseEntity<FollowRequest> response = userController.followUser(followRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(followRequest, response.getBody());
    }

    @Test
    void followUser_internalServerError() {
        when(userService.followRequest(any(FollowRequest.class))).thenThrow(new RuntimeException());

        ResponseEntity<FollowRequest> response = userController.followUser(new FollowRequest());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void cancelFollowRequest_success() {
        doNothing().when(userService).cancelFollowRequest(anyString());

        ResponseEntity<Void> response = userController.cancelFollowRequest("requestId");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void cancelFollowRequest_internalServerError() {
        doThrow(new RuntimeException()).when(userService).cancelFollowRequest(anyString());

        ResponseEntity<Void> response = userController.cancelFollowRequest("requestId");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllUsers_success() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void getAllUsers_internalServerError() {
        when(userService.getAllUsers()).thenThrow(new RuntimeException());

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllMentors_success() {
        List<Mentor> mentors = Arrays.asList(new Mentor(), new Mentor());
        when(userService.getAllMentors()).thenReturn(mentors);

        ResponseEntity<List<Mentor>> response = userController.getAllMentors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentors, response.getBody());
    }

    @Test
    void getAllMentors_internalServerError() {
        when(userService.getAllMentors()).thenThrow(new RuntimeException());

        ResponseEntity<List<Mentor>> response = userController.getAllMentors();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllMentees_success() {
        List<Mentee> mentees = Arrays.asList(new Mentee(), new Mentee());
        when(userService.getAllMentees()).thenReturn(mentees);

        ResponseEntity<List<Mentee>> response = userController.getAllMentees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentees, response.getBody());
    }

    @Test
    void getAllMentees_internalServerError() {
        when(userService.getAllMentees()).thenThrow(new RuntimeException());

        ResponseEntity<List<Mentee>> response = userController.getAllMentees();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getFollowRequests_success() {
        List<FollowRequest> followRequests = Arrays.asList(new FollowRequest(), new FollowRequest());
        when(userService.getAllFollowRequests()).thenReturn(followRequests);

        ResponseEntity<List<FollowRequest>> response = userController.getFollowRequests(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(followRequests, response.getBody());
    }

    @Test
    void getFollowRequests_internalServerError() {
        when(userService.getAllFollowRequests()).thenThrow(new RuntimeException());

        ResponseEntity<List<FollowRequest>> response = userController.getFollowRequests(null, null, null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllFollows_success() {
        List<Follow> follows = Arrays.asList(new Follow(), new Follow());
        when(userService.getAllFollows()).thenReturn(follows);

        ResponseEntity<List<Follow>> response = userController.getAllFollows(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(follows, response.getBody());
    }

    @Test
    void getAllFollows_internalServerError() {
        when(userService.getAllFollows()).thenThrow(new RuntimeException());

        ResponseEntity<List<Follow>> response = userController.getAllFollows(null, null, null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMentorById_success() {
        Optional<Mentor> mentor = Optional.of(new Mentor());
        when(userService.getMentorById(anyString())).thenReturn(mentor);

        ResponseEntity<Optional<Mentor>> response = userController.getMentorById("mentorId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentor, response.getBody());
    }


    @Test
    void getMentorById_internalServerError() {
        when(userService.getMentorById(anyString())).thenThrow(new RuntimeException());

        ResponseEntity<Optional<Mentor>> response = userController.getMentorById("mentorId");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMenteeById_success() {
        Optional<Mentee> mentee = Optional.of(new Mentee());
        when(userService.getMenteeById(anyString())).thenReturn(mentee);

        ResponseEntity<Optional<Mentee>> response = userController.getMenteeById("menteeId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentee, response.getBody());
    }

    @Test
    void getMenteeById_internalServerError() {
        when(userService.getMenteeById(anyString())).thenThrow(new RuntimeException());

        ResponseEntity<Optional<Mentee>> response = userController.getMenteeById("menteeId");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateFollowRequest_success() {
        FollowRequest followRequest = new FollowRequest();
        when(userService.updateFollowRequestStatus(anyString(), anyString())).thenReturn(followRequest);

        ResponseEntity<FollowRequest> response = userController.updateFollowRequest("requestId", Map.of("status", "approved"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(followRequest, response.getBody());
    }

    @Test
    void updateFollowRequest_notFound() {
        when(userService.updateFollowRequestStatus(anyString(), anyString())).thenReturn(null);

        ResponseEntity<FollowRequest> response = userController.updateFollowRequest("requestId", Map.of("status", "approved"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateFollowRequest_internalServerError() {
        when(userService.updateFollowRequestStatus(anyString(), anyString())).thenThrow(new RuntimeException());

        ResponseEntity<FollowRequest> response = userController.updateFollowRequest("requestId", Map.of("status", "approved"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void createMessage_success() {
        Message message = new Message();
        when(userService.createMessage(any(Message.class))).thenReturn(message);

        ResponseEntity<Message> response = userController.createMessage(message);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void createMessage_badRequest() {
        when(userService.createMessage(any(Message.class))).thenReturn(null);

        ResponseEntity<Message> response = userController.createMessage(new Message());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMessage_internalServerError() {
        when(userService.createMessage(any(Message.class))).thenThrow(new RuntimeException());

        ResponseEntity<Message> response = userController.createMessage(new Message());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMessagesBetweenUsers_success() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(userService.findMessagesBetweenUsers(anyString(), anyString())).thenReturn(messages);

        ResponseEntity<List<Message>> response = userController.getMessagesBetweenUsers("senderId", "receiverId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messages, response.getBody());
    }

    @Test
    void getMessagesBetweenUsers_internalServerError() {
        when(userService.findMessagesBetweenUsers(anyString(), anyString())).thenThrow(new RuntimeException());

        ResponseEntity<List<Message>> response = userController.getMessagesBetweenUsers("senderId", "receiverId");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateMentorProfile_success() {
        Mentor mentor = new Mentor();
        when(userService.updateMentorProfile(anyString(), any(Mentor.class))).thenReturn(mentor);

        ResponseEntity<Mentor> response = userController.updateMentorProfile("mentorId", mentor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentor, response.getBody());
    }

    @Test
    void updateMentorProfile_internalServerError() {
        when(userService.updateMentorProfile(anyString(), any(Mentor.class))).thenThrow(new RuntimeException());

        ResponseEntity<Mentor> response = userController.updateMentorProfile("mentorId", new Mentor());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateMenteeProfile_success() {
        Mentee mentee = new Mentee();
        when(userService.updateMenteeProfile(anyString(), any(Mentee.class))).thenReturn(mentee);

        ResponseEntity<Mentee> response = userController.updateMenteeProfile("menteeId", mentee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentee, response.getBody());
    }

    @Test
    void updateMenteeProfile_internalServerError() {
        when(userService.updateMenteeProfile(anyString(), any(Mentee.class))).thenThrow(new RuntimeException());

        ResponseEntity<Mentee> response = userController.updateMenteeProfile("menteeId", new Mentee());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
