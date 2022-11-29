package com.UWCV2Service.service;

import com.UWCV2Service.model.MCP;
import java.util.List;

/**
 * MCPService
 */
public interface MCPService {
  MCP saveMCP(MCP mcp);
  List<MCP> getMCPs();
}