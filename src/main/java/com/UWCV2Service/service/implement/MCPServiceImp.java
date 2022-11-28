package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.MCP;
import com.UWCV2Service.repository.MCPRepository;
import com.UWCV2Service.repository.PointRepository;
import com.UWCV2Service.service.MCPService;
import java.util.List;
import lombok.Builder;
import org.springframework.stereotype.Service;

/**
 * MCPServiceImp
 */
@Service
@Builder
public class MCPServiceImp implements MCPService {
  private final MCPRepository mcpRepository;
  private final PointRepository pointRepository;
  @Override
  public MCP saveMCP(MCP mcp) {
    pointRepository.save(mcp.getPoint());
    return mcpRepository.save(mcp);
  }

  @Override
  public List<MCP> getMCPs() {
    return mcpRepository.findAll();
  }
}
