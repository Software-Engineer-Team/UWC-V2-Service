package com.UWCV2Service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Task
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee-task")
public class EmpTask {
  @Id private String id;
  private String duty;
  private String description;
  private Calendar calendar;
}
