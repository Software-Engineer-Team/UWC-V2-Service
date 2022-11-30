package com.UWCV2Service.repository;

import com.UWCV2Service.model.JanitorTask;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * JanitorTaskRepository
 */
public interface JanitorTaskRepository
    extends MongoRepository<JanitorTask, String> {}
