package com.UWCV2Service.repository;

import com.UWCV2Service.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MessageRepository
 */
public interface MessageRepository extends MongoRepository<Message, String> {}
