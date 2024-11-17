package com.finalproject.MentorService.repository;

import com.finalproject.MentorService.model.FollowRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRequestRepository extends MongoRepository<FollowRequest, String> {
    List<FollowRequest> findByMenteeId(String menteeId);

    List<FollowRequest> findByMentorId(String mentorId);

    List<FollowRequest> findByMentorIdAndStatus(String mentorId, String status);
}
