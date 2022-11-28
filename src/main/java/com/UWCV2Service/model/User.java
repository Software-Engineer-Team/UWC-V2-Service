package com.UWCV2Service.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * User
 */
@Data
@Builder
@Document(collection = "user")
public class User {
  @Id private String id;

  private String name;

  // @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;

  private String password;

  @DocumentReference(collection = "role") private List<Role> roles;
}
