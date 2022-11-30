package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import java.util.List;
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

  @DocumentReference(collection = "mcp") @CascadeSave private MCP mcp;

  @DocumentReference(collection = "point")
  @CascadeSave
  private List<Point> points;
}
