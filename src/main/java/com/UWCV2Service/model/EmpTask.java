package com.UWCV2Service.model;

import com.UWCV2Service.anotation.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * Task
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpTask {
  private String description;
  private TaskTime taskTime;
}
