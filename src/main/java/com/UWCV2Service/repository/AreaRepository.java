package com.UWCV2Service.repository;

import com.UWCV2Service.model.Area;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * AreaRepository
 */
public interface AreaRepository extends MongoRepository<Area, String> {
  @Query("{'centerPoint': {'$oid': ?0}}") Optional<Area> findAreaByPoint(String pointId);
}
