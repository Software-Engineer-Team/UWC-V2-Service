package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.TaskTime;
import com.UWCV2Service.repository.TaskTimeRepository;
import com.UWCV2Service.service.TaskTimeService;
import lombok.Builder;
import org.springframework.stereotype.Service;

/**
 * TaskTimeServiceImp
 */
@Service
@Builder
public class TaskTimeServiceImp implements TaskTimeService {
  private final TaskTimeRepository taskTimeRepository;
  @Override
  public TaskTime saveTaskTime(TaskTime taskTime) {
    return taskTimeRepository.save(taskTime);
  }
}
