package com.UWCV2Service.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TaskTime
 */
@Data
@Builder
@Document(collection = "calendar")
public class TaskTime {
  private String day;
  private String time;
}
