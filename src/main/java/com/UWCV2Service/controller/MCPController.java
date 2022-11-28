package com.UWCV2Service.controller;

import com.UWCV2Service.model.MCP;
import com.UWCV2Service.service.MCPService;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MCPController
 */
@RequestMapping("/api")
@RestController
@Builder
public class MCPController {
  private final MCPService mcpService;

  @PostMapping(value = "/MCP/save")
  public ResponseEntity<?> saveMCP(@RequestBody MCP mcp) {
    return ResponseEntity.ok().body(mcpService.saveMCP(mcp));
  }

  @GetMapping(value = "/MCPs")
  private ResponseEntity<?> fetchMCPs() {
    return ResponseEntity.ok(mcpService.getMCPs());
  }
}
