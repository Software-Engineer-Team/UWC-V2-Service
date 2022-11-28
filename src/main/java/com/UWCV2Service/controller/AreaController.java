package com.UWCV2Service.controller;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.service.AreaService;
import java.net.URI;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * AreaController
 */
@RestController
@Builder
@RequestMapping("/api")
public class AreaController {
  private final AreaService areaService;

  @PostMapping(value = "/area/save")
  public ResponseEntity<Area> saveArea(@RequestBody Area area) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                             .path("/api/area/save")
                             .toUriString());
    return ResponseEntity.created(uri).body(areaService.saveArea(area));
  }

  @GetMapping(value = "/areas")
  private ResponseEntity<?> getAllAreas() {
    return ResponseEntity.ok(areaService.getAreas());
  }
}
