package FarmCollector.services.implementation;

import FarmCollector.dtos.FarmDto;
import FarmCollector.dtos.FarmReportDto;
import FarmCollector.model.entities.Farm;
import FarmCollector.repositories.FarmRepository;
import FarmCollector.services.FarmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//@RequiredArgsConstructor
@Service
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Page<Farm> retrieveFarmRecords(String farmName, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return farmRepository.findByName(farmName, pageable);
    }

    @Override
    public Farm retrieveFarmById(Long farmId) {

        return farmRepository.findById(farmId)
                .orElseThrow(()-> new RuntimeException("No Record Found For Id " + farmId));
    }

    @Override
    public Farm saveFarmRecord(FarmDto farmDto) {

        if (farmRepository.existsByNameAndSeason(farmDto.getName(), farmDto.getSeason())) {
            throw new RuntimeException("Farm with name " + farmDto.getName() + " already exists");
        }

        Farm farm = new Farm();
        farm.setName(farmDto.getName());
        farm.setYear(farmDto.getYear());
        farm.setSeason(farmDto.getSeason());
        farm.setCropType(farmDto.getCropType());
        farm.setPlantingArea(farmDto.getPlantingArea());
        farm.setAmountExpected(farmDto.getAmountExpected());

        return farmRepository.save(farm);
    }

    @Override
    public Farm updateFarmRecord(Long farmId, Double actualAmount) {

        if(actualAmount < 0){
            throw new RuntimeException("Invalid Actual Amount");
        }
        Farm farm = retrieveFarmById(farmId);

        farm.setActualAmount(actualAmount);

        return farmRepository.save(farm);
    }

    @Override
    public Page<FarmReportDto> getFarmReport(String farmName, Pageable pageable) {
        Page<Farm> farms = farmRepository.findByName(farmName, pageable);

        List<FarmReportDto> reportDtos = farms.getContent().stream()
                .map(farm -> new FarmReportDto(farm.getAmountExpected(), farm.getActualAmount()))
                .collect(Collectors.toList());

        return new PageImpl<>(reportDtos, pageable, farms.getTotalElements());
    }
}