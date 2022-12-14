package com.UWCV2Service.service;

import com.UWCV2Service.model.Area;
import java.util.List;

/**
 * AreService
 */
public interface AreaService {
  Area saveArea(Area area);

  List<Area> getAreas();

  Area addMcpToArea(String pointId, String mcpId);

  List<Area> getAreasByMcpId(String mcpId);
}
