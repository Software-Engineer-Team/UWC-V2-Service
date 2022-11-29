package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.model.MCP;
import com.UWCV2Service.model.Point;
import com.UWCV2Service.repository.AreaRepository;
import com.UWCV2Service.repository.MCPRepository;
import com.UWCV2Service.service.AreaService;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * AreaServiceIml
 */
@Service
@Slf4j
@Builder
public class AreaServiceIml implements AreaService {
  private final AreaRepository areaRepository;
  private final MCPRepository mcpRepository;

  @Override
  public Area saveArea(Area area) {
    return areaRepository.save(area);
  }

  @Override
  public List<Area> getAreas() {
    return areaRepository.findAll();
  }

  @Override
  public Area addMcpToArea(String pointId, String mcpId) {
    Area area = areaRepository.findAreaByPoint(pointId).orElse(null);
    MCP mcp = mcpRepository.findById(mcpId).orElse(null);
    area.setMcp(mcp);
    return areaRepository.save(area);
  }
}
