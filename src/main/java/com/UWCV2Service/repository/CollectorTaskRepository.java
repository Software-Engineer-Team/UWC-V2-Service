package com.UWCV2Service.repository;

import com.UWCV2Service.model.CollectorTask;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CollectorTaskRepository
 */
public interface CollectorTaskRepository
    extends MongoRepository<CollectorTask, String> {}
