package com.UWCV2Service.repository;

import com.UWCV2Service.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * UserRepository
 */
// Mongodb need id (String type)
public interface UserRepository extends MongoRepository<User, String> {
  // @Query(value = "{ 'email' : ?0 }", fields = "{'password': 0}")
  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);
}
