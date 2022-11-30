package com.UWCV2Service.service;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.model.MCP;
import java.util.List;

/**
 * MCPService
 */
public interface MCPService {
  MCP saveMCP(MCP mcp);
  MCP getMCPByName(String name);
  List<MCP> getMCPs();
}
