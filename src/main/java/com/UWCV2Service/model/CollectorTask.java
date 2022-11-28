package com.UWCV2Service.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * CollectorTask
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Document(collection = "collector-task")
public class CollectorTask extends EmpTask {
  @DocumentReference(collection = "user") private User collector;
  @DocumentReference(collection = "route") private List<Route> routes;

  @Builder
  public CollectorTask(String id, String duty, String description,
                       Calendar calendar) {
    super(id, duty, description, calendar);
  }
}
