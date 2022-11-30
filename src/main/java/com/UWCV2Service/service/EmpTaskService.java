package com.UWCV2Service.service;

import com.UWCV2Service.model.CollectorTask;
import com.UWCV2Service.model.JanitorTask;
import java.util.List;

/**
 * EmpTaskService
 */
public interface EmpTaskService {
  CollectorTask saveCollectorTask(CollectorTask collectorTask, String userName,
                                  String routeName, String mcpName);
  List<CollectorTask> getCollectorTasks();

  List<CollectorTask> getCollectorTasks(String date);

  JanitorTask saveJanitorTask(JanitorTask janitorTask, String userName,
                              String mcpName, String areaName);
  List<JanitorTask> getJanitorTasks();

  List<JanitorTask> getJanitorTasks(String date);
}
