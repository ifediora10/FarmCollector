package FarmCollector.controller;


import FarmCollector.dtos.FarmDto;
import FarmCollector.model.entities.Farm;
import FarmCollector.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
//@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
        Farm savedFarm = farmService.saveFarmRecord(farmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarm);
    }

    @GetMapping
    public ResponseEntity<Page<Farm>> getAllFarms(
            @RequestParam String farmName,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Farm> farms = farmService.retrieveFarmRecords(farmName, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(farms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        Farm farm = farmService.retrieveFarmById(id);
        return ResponseEntity.status(HttpStatus.OK).body(farm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestParam Double actualAmount) {
        Farm updatedFarm = farmService.updateFarmRecord(id, actualAmount);
        return ResponseEntity.status(HttpStatus.OK).body(updatedFarm);
    }
}

