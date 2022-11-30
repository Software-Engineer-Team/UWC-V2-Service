package com.UWCV2Service.repository;

import com.UWCV2Service.model.TaskTime;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TaskTimeRepository
 */
public interface TaskTimeRepository extends MongoRepository<TaskTime, String> {}
