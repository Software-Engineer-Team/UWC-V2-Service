package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/** Route */
@Builder
@Data
@Document(collection = "route")
public class Route {
  @Id private String id;

  private String name;

  // @DocumentReference(collection = "point")
  // @CascadeSave
  // private Point startPoint; // From MCP
  @DocumentReference(collection = "mcp")
  @CascadeSave
  private MCP mcp; // Start point

  @DocumentReference(collection = "point")
  @CascadeSave
  private Point endPoint; // from 1 point inside an area
}
