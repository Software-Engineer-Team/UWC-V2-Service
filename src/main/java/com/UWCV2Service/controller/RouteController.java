package com.UWCV2Service.controller;

import com.UWCV2Service.model.Route;
import com.UWCV2Service.service.RouteService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RouteController
 */
@RestController
@RequestMapping("/api")
@Builder
public class RouteController {

  private RouteService routeService;

  @PostMapping(value = "/route/save")
  public ResponseEntity<?> addRoute(@RequestBody Route route) {
    Route newRoute = routeService.saveRoute(route);
    return ResponseEntity.ok().body(newRoute);
  }

  @GetMapping(value = "/routes")
  public ResponseEntity<?> fetchRoutes() {
    return ResponseEntity.ok().body(routeService.getRoutes());
  }
}
