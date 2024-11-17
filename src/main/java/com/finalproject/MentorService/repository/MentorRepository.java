package com.finalproject.MentorService.repository;

import com.finalproject.MentorService.model.Mentor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends MongoRepository<Mentor, String> {
}
