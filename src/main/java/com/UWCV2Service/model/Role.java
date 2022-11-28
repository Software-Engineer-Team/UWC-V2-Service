package com.UWCV2Service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Role */
@Builder
@Data
@Document(collection = "role")
public class Role {
  @Id private String id;

  private String name;
}
