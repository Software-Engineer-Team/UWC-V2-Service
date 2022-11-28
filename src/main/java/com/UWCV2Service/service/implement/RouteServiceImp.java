package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Point;
import com.UWCV2Service.model.Route;
import com.UWCV2Service.repository.PointRepository;
import com.UWCV2Service.repository.RouteRepository;
import com.UWCV2Service.service.RouteService;
import java.util.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RouteServiceImp
 */
@Service
@Builder
@Slf4j
public class RouteServiceImp implements RouteService {
  private final RouteRepository routeRepository;
  private final PointRepository pointRepository;

  @Override
  public Route saveRoute(Route route) {
    log.info("saveRoute: {}", route);
    pointRepository.save(route.getStartPoint());
    pointRepository.save(route.getEndPoint());
    return routeRepository.save(route);
  }

  @Override
  public List<Route> getRoutes() {
    return routeRepository.findAll();
  }
}
