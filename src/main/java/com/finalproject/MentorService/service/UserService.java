package com.finalproject.MentorService.service;

import com.finalproject.MentorService.model.*;
import com.finalproject.MentorService.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final FollowRequestRepository followRequestRepository;
    private final FollowRepository followRepository;
    private final MessageRepository messageRepository;

    public UserService(UserRepository userRepository, MentorRepository mentorRepository, MenteeRepository menteeRepository, FollowRequestRepository followRequestRepository, FollowRepository followRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
        this.menteeRepository = menteeRepository;
        this.followRequestRepository = followRequestRepository;
        this.followRepository = followRepository;
        this.messageRepository = messageRepository;
    }
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public Mentor registerMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Mentee registerMentor(Mentee mentee) {
        return menteeRepository.save(mentee);
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public List<Mentee> getAllMentees() {
        return menteeRepository.findAll();
    }

    public FollowRequest followRequest(FollowRequest followRequest) {
        return followRequestRepository.save(followRequest);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Follow follow(Follow follow) {
        return followRepository.save(follow);
    }

    public List<FollowRequest> getAllFollowRequests() {
        return followRequestRepository.findAll();
    }

    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    public List<FollowRequest> getFollowRequestsForMenteeId(String menteeId) {
        return followRequestRepository.findByMenteeId(menteeId);
    }

    public List<FollowRequest> getFollowRequestsForMentorId(String mentorId) {
        return followRequestRepository.findByMentorId(mentorId);
    }

    public List<Follow> getFollowsForMenteeId(String menteeId) {
        return followRepository.findByMenteeId(menteeId);
    }

    public List<Follow> getFollowsForMentorId(String mentorId) {
        return followRepository.findByMentorId(mentorId);
    }

    public Optional<Mentor> getMentorById(String mentorId) {
        return mentorRepository.findById(mentorId);
    }

    public FollowRequest updateFollowRequestStatus(String requestId, String status) {
        FollowRequest followRequest = followRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Follow request not found with id: " + requestId));
        followRequest.setStatus(status);
        return followRequestRepository.save(followRequest);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findMessagesBetweenUsers(String senderId, String receiverId) {
        return messageRepository.findMessagesBetweenUsers(senderId, receiverId);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Mentee> getMenteeById(String menteeId) {
        return menteeRepository.findById(menteeId);
    }

    public List<FollowRequest> getFollowRequestForMentorIdAndStatus(String mentorId, String status) {
        return followRequestRepository.findByMentorIdAndStatus(mentorId, status);
    }

    public void cancelFollowRequest(String requestId) {
        followRequestRepository.deleteById(requestId);
    }
}
