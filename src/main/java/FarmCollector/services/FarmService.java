package FarmCollector.services;

import FarmCollector.dtos.FarmDto;
import FarmCollector.model.entities.Farm;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FarmService {

    Page<Farm> retrieveFarmRecords(String farmName, int pageNo, int pageSize);

    Farm retrieveFarmById(Long farmId);

    Farm saveFarmRecord(FarmDto farmDto);

    Farm updateFarmRecord(Long farmId, Double actualAmount);

}
