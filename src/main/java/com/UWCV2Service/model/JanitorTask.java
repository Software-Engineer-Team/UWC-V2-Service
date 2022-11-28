package com.UWCV2Service.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * JanitorTask
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Document(collection = "janitor-task")
public class JanitorTask extends EmpTask {

  @Builder
  public JanitorTask(String id, String duty, String description,
                     Calendar calendar) {
    super(id, duty, description, calendar);
  }
}
