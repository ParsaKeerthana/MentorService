package com.finalproject.MentorService.service;

import com.finalproject.MentorService.model.*;
import com.finalproject.MentorService.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private MenteeRepository menteeRepository;

    @Mock
    private FollowRequestRepository followRequestRepository;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_success() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(user);

        assertEquals(user, result);
    }

    @Test
    void registerMentor_success() {
        Mentor mentor = new Mentor();
        when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

        Mentor result = userService.registerMentor(mentor);

        assertEquals(mentor, result);
    }

    @Test
    void registerMentee_success() {
        Mentee mentee = new Mentee();
        when(menteeRepository.save(any(Mentee.class))).thenReturn(mentee);

        Mentee result = userService.registerMentor(mentee);

        assertEquals(mentee, result);
    }

    @Test
    void getAllMentors_success() {
        List<Mentor> mentors = Arrays.asList(new Mentor(), new Mentor());
        when(mentorRepository.findAll()).thenReturn(mentors);

        List<Mentor> result = userService.getAllMentors();

        assertEquals(mentors, result);
    }

    @Test
    void getAllMentees_success() {
        List<Mentee> mentees = Arrays.asList(new Mentee(), new Mentee());
        when(menteeRepository.findAll()).thenReturn(mentees);

        List<Mentee> result = userService.getAllMentees();

        assertEquals(mentees, result);
    }

    @Test
    void followRequest_success() {
        FollowRequest followRequest = new FollowRequest();
        when(followRequestRepository.save(any(FollowRequest.class))).thenReturn(followRequest);

        FollowRequest result = userService.followRequest(followRequest);

        assertEquals(followRequest, result);
    }

    @Test
    void getAllUsers_success() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
    }

    @Test
    void follow_success() {
        Follow follow = new Follow();
        when(followRepository.save(any(Follow.class))).thenReturn(follow);

        Follow result = userService.follow(follow);

        assertEquals(follow, result);
    }

    @Test
    void getAllFollowRequests_success() {
        List<FollowRequest> followRequests = Arrays.asList(new FollowRequest(), new FollowRequest());
        when(followRequestRepository.findAll()).thenReturn(followRequests);

        List<FollowRequest> result = userService.getAllFollowRequests();

        assertEquals(followRequests, result);
    }

    @Test
    void getAllFollows_success() {
        List<Follow> follows = Arrays.asList(new Follow(), new Follow());
        when(followRepository.findAll()).thenReturn(follows);

        List<Follow> result = userService.getAllFollows();

        assertEquals(follows, result);
    }

    @Test
    void getFollowRequestsForMenteeId_success() {
        List<FollowRequest> followRequests = Arrays.asList(new FollowRequest(), new FollowRequest());
        when(followRequestRepository.findByMenteeId(anyString())).thenReturn(followRequests);

        List<FollowRequest> result = userService.getFollowRequestsForMenteeId("menteeId");

        assertEquals(followRequests, result);
    }

    @Test
    void getFollowRequestsForMentorId_success() {
        List<FollowRequest> followRequests = Arrays.asList(new FollowRequest(), new FollowRequest());
        when(followRequestRepository.findByMentorId(anyString())).thenReturn(followRequests);

        List<FollowRequest> result = userService.getFollowRequestsForMentorId("mentorId");

        assertEquals(followRequests, result);
    }

    @Test
    void getFollowsForMenteeId_success() {
        List<Follow> follows = Arrays.asList(new Follow(), new Follow());
        when(followRepository.findByMenteeId(anyString())).thenReturn(follows);

        List<Follow> result = userService.getFollowsForMenteeId("menteeId");

        assertEquals(follows, result);
    }

    @Test
    void getFollowsForMentorId_success() {
        List<Follow> follows = Arrays.asList(new Follow(), new Follow());
        when(followRepository.findByMentorId(anyString())).thenReturn(follows);

        List<Follow> result = userService.getFollowsForMentorId("mentorId");

        assertEquals(follows, result);
    }

    @Test
    void getMentorById_success() {
        Optional<Mentor> mentor = Optional.of(new Mentor());
        when(mentorRepository.findById(anyString())).thenReturn(mentor);

        Optional<Mentor> result = userService.getMentorById("mentorId");

        assertEquals(mentor, result);
    }

    @Test
    void updateFollowRequestStatus_success() {
        FollowRequest followRequest = new FollowRequest();
        when(followRequestRepository.findById(anyString())).thenReturn(Optional.of(followRequest));
        when(followRequestRepository.save(any(FollowRequest.class))).thenReturn(followRequest);

        FollowRequest result = userService.updateFollowRequestStatus("requestId", "approved");

        assertEquals(followRequest, result);
    }

    @Test
    void createMessage_success() {
        Message message = new Message();
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message result = userService.createMessage(message);

        assertEquals(message, result);
    }

    @Test
    void findMessagesBetweenUsers_success() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(messageRepository.findMessagesBetweenUsers(anyString(), anyString())).thenReturn(messages);

        List<Message> result = userService.findMessagesBetweenUsers("senderId", "receiverId");

        assertEquals(messages, result);
    }

    @Test
    void getMenteeById_success() {
        Optional<Mentee> mentee = Optional.of(new Mentee());
        when(menteeRepository.findById(anyString())).thenReturn(mentee);

        Optional<Mentee> result = userService.getMenteeById("menteeId");

        assertEquals(mentee, result);
    }

    @Test
    void getFollowRequestForMentorIdAndStatus_success() {
        List<FollowRequest> followRequests = Arrays.asList(new FollowRequest(), new FollowRequest());
        when(followRequestRepository.findByMentorIdAndStatus(anyString(), anyString())).thenReturn(followRequests);

        List<FollowRequest> result = userService.getFollowRequestForMentorIdAndStatus("mentorId", "approved");

        assertEquals(followRequests, result);
    }

    @Test
    void cancelFollowRequest_success() {
        doNothing().when(followRequestRepository).deleteById(anyString());

        userService.cancelFollowRequest("requestId");

        verify(followRequestRepository, times(1)).deleteById("requestId");
    }

    @Test
    void updateMentorProfile_success() {
        Mentor mentor = new Mentor();
        when(mentorRepository.findById(anyString())).thenReturn(Optional.of(mentor));
        when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

        Mentor result = userService.updateMentorProfile("mentorId", mentor);

        assertEquals(mentor, result);
    }

    @Test
    void updateMenteeProfile_success() {
        Mentee mentee = new Mentee();
        when(menteeRepository.findById(anyString())).thenReturn(Optional.of(mentee));
        when(menteeRepository.save(any(Mentee.class))).thenReturn(mentee);

        Mentee result = userService.updateMenteeProfile("menteeId", mentee);

        assertEquals(mentee, result);
    }
}