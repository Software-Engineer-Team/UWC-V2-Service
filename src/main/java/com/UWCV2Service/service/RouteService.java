package com.UWCV2Service.service;

import com.UWCV2Service.model.Point;
import com.UWCV2Service.model.Route;
import java.util.List;

/**
 * RouteService
 */
public interface RouteService {
  Route saveRoute(Route route, String mcpId);

  List<Route> getRoutes();
}
