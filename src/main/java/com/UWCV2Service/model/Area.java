package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Area
 */
@Builder
@Data
@Document(collection = "area")
public class Area {
  @Id private String id;

  @DocumentReference(collection = "point")
  @CascadeSave
  private Point centerPoint;

  private Integer numOfEmps;

  @DocumentReference(collection = "mcp") @CascadeSave private MCP mcp;
}
