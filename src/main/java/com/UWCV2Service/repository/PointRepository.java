package com.UWCV2Service.repository;

import com.UWCV2Service.model.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * PointRepository
 */
public interface PointRepository extends MongoRepository<Point, String> {}
