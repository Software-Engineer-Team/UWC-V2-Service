package com.UWCV2Service.repository;

import com.UWCV2Service.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * UserRepository
 */
// Mongodb need id (String type)
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);
}
