package com.finalproject.MentorService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.finalproject.MentorService.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
