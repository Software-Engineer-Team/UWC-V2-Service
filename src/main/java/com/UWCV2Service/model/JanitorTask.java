package com.UWCV2Service.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * JanitorTask
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Document(collection = "janitor-task")
public class JanitorTask extends EmpTask {
  @Id private String id;

  @DocumentReference(collection = "user") private User janitor;
  @DocumentReference(collection = "mcp") private MCP mcp;
  @DocumentReference(collection = "area") private Area areas;
}
