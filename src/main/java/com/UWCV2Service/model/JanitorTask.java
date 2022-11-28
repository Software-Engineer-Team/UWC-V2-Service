package com.UWCV2Service.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * JanitorTask
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Document(collection = "janitor-task")
public class JanitorTask extends EmpTask {
  @DocumentReference(collection = "user") private User janitor;
  @DocumentReference(collection = "area") private List<Area> areas;

  @Builder
  public JanitorTask(String id, String duty, String description,
                     Calendar calendar) {
    super(id, duty, description, calendar);
  }
}
