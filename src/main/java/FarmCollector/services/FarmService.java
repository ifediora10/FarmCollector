package FarmCollector.services;

import FarmCollector.dtos.FarmDto;
import FarmCollector.dtos.FarmReportDto;
import FarmCollector.model.entities.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FarmService {

    Page<Farm> retrieveFarmRecords(String farmName, int pageNo, int pageSize);

    Farm retrieveFarmById(Long farmId);

    Farm saveFarmRecord(FarmDto farmDto);

    Farm updateFarmRecord(Long farmId, Double actualAmount);

    Page<FarmReportDto> getFarmReport(String farmName, Pageable pageable);
}
