package com.UWCV2Service.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Calendar
 */
@Data
@Builder
@Document(collection = "calendar")
public class Calendar {
  private Date startTime;
  private Date endTime;
}
