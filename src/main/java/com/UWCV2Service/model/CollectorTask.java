package com.UWCV2Service.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * CollectorTask
 */
@Data
@Builder
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Document(collection = "collector-task")
public class CollectorTask extends EmpTask {
  @DocumentReference(collection = "user") private User collector;
  @DocumentReference(collection = "route") private List<Route> routes;
}
