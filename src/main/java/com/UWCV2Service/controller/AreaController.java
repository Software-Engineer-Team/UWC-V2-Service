package com.UWCV2Service.controller;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.service.AreaService;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping(value = "/area/add-mcp-to-area")
  public ResponseEntity<Area> addMcpToArea(@RequestBody McpToArea ob) {
    return ResponseEntity.ok(
        areaService.addMcpToArea(ob.getPointId(), ob.getMcpId()));
  }

  @GetMapping(value = "/areas")
  private ResponseEntity<?> getAllAreas() {
    return ResponseEntity.ok(areaService.getAreas());
  }

  @GetMapping(value = "/areas/{mcpId}")
  private ResponseEntity<?> getAreasByMcpId(@PathVariable String mcpId) {
    return ResponseEntity.ok(areaService.getAreasByMcpId(mcpId));
  }

  @Data
  @Builder
  static class McpToArea {
    private String pointId;
    private String mcpId;
  }
}
