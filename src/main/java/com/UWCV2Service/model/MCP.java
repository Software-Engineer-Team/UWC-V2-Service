package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * MCP
 */
@Data
@Builder
@Document(collection = "mcp")
public class MCP {
  @Id private String id;

  private String name;

  @DocumentReference(collection = "point") @CascadeSave private Point point;
}
