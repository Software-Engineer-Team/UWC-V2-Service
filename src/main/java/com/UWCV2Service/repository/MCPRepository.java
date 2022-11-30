package com.UWCV2Service.repository;

import com.UWCV2Service.model.MCP;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MCPRepository
 */
public interface MCPRepository extends MongoRepository<MCP, String> {
  Optional<MCP> findByName(String name);
}
