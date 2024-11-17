package com.finalproject.MentorService.repository;

import com.finalproject.MentorService.model.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    List<Follow> findByMenteeId(String menteeId);

    List<Follow> findByMentorId(String mentorId);
}
