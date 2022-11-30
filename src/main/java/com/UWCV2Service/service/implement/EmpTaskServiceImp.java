package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.model.CollectorTask;
import com.UWCV2Service.model.JanitorTask;
import com.UWCV2Service.model.MCP;
import com.UWCV2Service.model.Route;
import com.UWCV2Service.model.User;
import com.UWCV2Service.repository.AreaRepository;
import com.UWCV2Service.repository.CollectorTaskRepository;
import com.UWCV2Service.repository.JanitorTaskRepository;
import com.UWCV2Service.repository.MCPRepository;
import com.UWCV2Service.repository.RouteRepository;
import com.UWCV2Service.repository.UserRepository;
import com.UWCV2Service.service.EmpTaskService;
import com.UWCV2Service.service.RouteService;
import java.util.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * EmpTaskServiceImp
 */
@Service
@Builder
@Slf4j
public class EmpTaskServiceImp implements EmpTaskService {
  private final CollectorTaskRepository collectorTaskRepository;
  private final JanitorTaskRepository janitorTaskRepository;
  private final UserRepository userRepository;
  private final RouteRepository routeRepository;
  private final AreaRepository areaRepository;
  private final MCPRepository mcpRepository;

  @Override
  public CollectorTask saveCollectorTask(CollectorTask collectorTask,
                                         String userName, String routeName) {

    User user = userRepository.findByName(userName).orElse(null);
    Route route = routeRepository.findByName(routeName).orElse(null);
    collectorTask.setRoute(route);
    collectorTask.setCollector(user);
    return collectorTaskRepository.save(collectorTask);
  }

  @Override
  public List<CollectorTask> getCollectorTasks() {
    return collectorTaskRepository.findAll();
  }

  @Override
  public JanitorTask saveJanitorTask(JanitorTask janitorTask, String userName,
                                     String mcpName, String areaName) {
    User janitor = userRepository.findByName(userName).orElse(null);
    MCP mcp = mcpRepository.findByName(mcpName).orElse(null);
    Area area = areaRepository.findByDescription(areaName).orElse(null);
    janitorTask.setJanitor(janitor);
    janitorTask.setAreas(area);
    janitorTask.setMcp(mcp);
    return janitorTaskRepository.save(janitorTask);
  }

  @Override
  public List<JanitorTask> getJanitorTasks() {
    return janitorTaskRepository.findAll();
  }
}
