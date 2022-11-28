package com.UWCV2Service.repository;

import com.UWCV2Service.model.MCP;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MCPRepository
 */
public interface MCPRepository extends MongoRepository<MCP, String> {}
