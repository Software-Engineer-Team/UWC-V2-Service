package com.UWCV2Service.controller;

import com.UWCV2Service.model.Route;
import com.UWCV2Service.service.RouteService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RouteController
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Builder
public class RouteController {

  private RouteService routeService;

  // @PostMapping(value = "/route/save/{mcpId}")
  // public ResponseEntity<?>
  // addRoute(@RequestBody Route route,
  //          @PathVariable(value = "mcpId") String mcpId) {

  @PostMapping(value = "/route/save")
  public ResponseEntity<?> addRoute(@RequestBody Route route,
                                    @RequestParam("mcpId") String mcpId) {

    log.info("Route info: {}", route.getName());
    log.info("Route info: {}", route.getEndPoint());
    Route newRoute = routeService.saveRoute(route, mcpId);
    return ResponseEntity.ok().body(newRoute);
  }

  @GetMapping(value = "/routes")
  public ResponseEntity<?> fetchRoutes() {
    return ResponseEntity.ok().body(routeService.getRoutes());
  }
}
