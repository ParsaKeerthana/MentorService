package com.finalproject.MentorService.repository;

import com.finalproject.MentorService.model.Mentee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends MongoRepository<Mentee, String> {
}
