package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.MCP;
import com.UWCV2Service.model.Route;
import com.UWCV2Service.repository.MCPRepository;
import com.UWCV2Service.repository.PointRepository;
import com.UWCV2Service.repository.RouteRepository;
import com.UWCV2Service.service.RouteService;
import java.util.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.stereotype.Service;

/**
 * RouteServiceImp
 */
@Service
@Builder
@Slf4j
public class RouteServiceImp implements RouteService {
  private final RouteRepository routeRepository;
  private final MCPRepository mcpRepository;

  @Override
  public Route saveRoute(Route route, String mcpId) {
    log.info("saveRoute: {}", route);
    MCP mcp = mcpRepository.findById(mcpId).orElse(null);
    route.setMcp(mcp);
    return routeRepository.save(route);
  }

  @Override
  public List<Route> getRoutes() {
    return routeRepository.findAll();
  }
}
