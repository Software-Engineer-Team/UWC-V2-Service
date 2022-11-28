package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Area;
import com.UWCV2Service.repository.AreaRepository;
import com.UWCV2Service.service.AreaService;
import java.util.List;
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

  @Override
  public Area saveArea(Area area) {
    log.info("saveArea: {}", area);
    return areaRepository.save(area);
  }

  @Override
  public List<Area> getAreas() {
    return areaRepository.findAll();
  }
}
