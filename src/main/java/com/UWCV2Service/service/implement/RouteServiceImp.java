package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Route;
import com.UWCV2Service.repository.PointRepository;
import com.UWCV2Service.repository.RouteRepository;
import com.UWCV2Service.service.RouteService;
import java.util.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RouteServiceImp
 */
@Service
@Builder
@Slf4j
public class RouteServiceImp implements RouteService {
  private final RouteRepository routeRepository;

  @Override
  public Route saveRoute(Route route) {
    log.info("saveRoute: {}", route);
    return routeRepository.save(route);
  }

  @Override
  public List<Route> getRoutes() {
    return routeRepository.findAll();
  }
}
