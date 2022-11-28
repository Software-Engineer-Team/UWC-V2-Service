package com.UWCV2Service.model;

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

  @DocumentReference(collection = "point") private Point startPoint; // From MCP
  @DocumentReference(collection = "point")
  private Point endPoint; // from 1 point inside an area
}
