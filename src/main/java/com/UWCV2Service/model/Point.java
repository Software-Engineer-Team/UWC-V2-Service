package com.UWCV2Service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Point */
@Builder
@Data
@Document(collection = "point")
public class Point {
  @Id private String id;

  private Double longitude;
  private Double latitude;
}
