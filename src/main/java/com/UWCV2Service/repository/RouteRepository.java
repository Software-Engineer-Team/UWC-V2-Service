package com.UWCV2Service.repository;

import com.UWCV2Service.model.Point;
import com.UWCV2Service.model.Route;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * RouteRepository
 */
@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
  Optional<Route> findByName(String name);
}
