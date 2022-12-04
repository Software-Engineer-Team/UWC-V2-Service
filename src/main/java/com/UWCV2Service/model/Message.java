package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Message
 */
@Data
@Builder
@Document(collation = "message")
public class Message {
  @Id private String id;

  @CascadeSave @DocumentReference(collection = "user") private User sender;
  private String message;
  private String date;
  private Status status;
}

/**
 * Status
 */
enum Status { JOIN, MESSAGE, LEAVE }
