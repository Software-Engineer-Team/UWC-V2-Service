package com.UWCV2Service.repository;

import com.UWCV2Service.model.Role;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * RoleRepository
 */
// Mongodb need id (String type)
public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(String roleName);
}
