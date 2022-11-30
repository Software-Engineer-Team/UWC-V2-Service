package com.UWCV2Service.controller;

import com.UWCV2Service.model.CollectorTask;
import com.UWCV2Service.model.JanitorTask;
import com.UWCV2Service.service.EmpTaskService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmpTaskController
 */
@RequestMapping("/api")
@RestController
@Builder
@Slf4j
public class EmpTaskController {
  private final EmpTaskService empTaskService;

  @PostMapping(value = "/collector-task/save")
  public ResponseEntity<?>
  saveCollectorTask(@RequestBody CollectorTask collectorTask,
                    @RequestParam("userName") String userName,
                    @RequestParam("routeName") String routeName,
                    @RequestParam("mcpName") String mcpName) {
    log.info("collectorTask: {}", collectorTask);
    log.info("userName: {}", userName);
    log.info("routeName: {}", routeName);
    return ResponseEntity.ok().body(empTaskService.saveCollectorTask(
        collectorTask, userName, routeName, mcpName));
  }

  @GetMapping(value = "/collector-tasks")
  public ResponseEntity<?> getCollectorTasks() {
    return ResponseEntity.ok().body(empTaskService.getCollectorTasks());
  }

  @GetMapping(value = "/collector-tasks-date")
  public ResponseEntity<?>
  getCollectorTasksByDate(@RequestParam("date") String date) {
    return ResponseEntity.ok().body(empTaskService.getCollectorTasks(date));
  }

  @PostMapping(value = "/janitor-task/save")
  public ResponseEntity<?>
  saveCollectorTask(@RequestBody JanitorTask janitorTask,
                    @RequestParam("userName") String userName,
                    @RequestParam("areaName") String areaName,
                    @RequestParam("mcpName") String mcpName) {
    log.info("janitorTask: {}", janitorTask);
    log.info("userName: {}", userName);
    log.info("mcpName: {}", mcpName);
    log.info("areaName: {}", areaName);
    return ResponseEntity.ok().body(empTaskService.saveJanitorTask(
        janitorTask, userName, mcpName, areaName));
  }

  @GetMapping(value = "/janitor-tasks")
  public ResponseEntity<?> getJanitorTasks() {
    return ResponseEntity.ok().body(empTaskService.getJanitorTasks());
  }

  @GetMapping(value = "/janitor-tasks-date")
  public ResponseEntity<?>
  getJanitorTasksByDate(@RequestParam("date") String date) {
    return ResponseEntity.ok().body(empTaskService.getJanitorTasks(date));
  }
}
