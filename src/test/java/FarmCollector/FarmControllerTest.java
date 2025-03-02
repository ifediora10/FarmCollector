package FarmCollector;

import FarmCollector.dtos.FarmDto;
import FarmCollector.model.entities.Farm;
import FarmCollector.repositories.FarmRepository;
import FarmCollector.services.FarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static FarmCollector.model.enums.Season.DRY_SEASON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class FarmControllerTest {
    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FarmService farmService;

    @BeforeEach
    void setUp() {
        farmRepository.deleteAll();
    }

    @Test
    void testCreateFarm() throws Exception {
        FarmDto farmDto = new FarmDto();
        farmDto.setName("John's Farm");
        farmDto.setSeason(DRY_SEASON);
        farmDto.setCropType("Tuber");
        farmDto.setYear(2025);
        farmDto.setPlantingArea(100d);
        farmDto.setAmountExpected(50d);

        mockMvc.perform(post("/farms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(farmDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetById() throws Exception {
        Farm save = createFarmRecord();

        mockMvc.perform(get("/farms/" + save.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByIdWhenRecordNotFound() throws Exception {
        mockMvc.perform(get("/farms/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllFarm() throws Exception {
        mockMvc.perform(get("/farms?farmName=John's_Farm")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFarm() throws Exception {
        Farm save = createFarmRecord();

        mockMvc.perform(put("/farms/" + save.getId() + "?actualAmount=25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetReport() throws Exception {
        Farm save = createFarmRecord();

        mockMvc.perform(get("/farms/report?farmName=" + save.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Farm createFarmRecord() {
        Farm farm = new Farm();
        farm.setName("John's Farm");
        farm.setSeason(DRY_SEASON);
        farm.setCropType("Tuber");
        farm.setYear(2025);
        farm.setPlantingArea(100d);
        farm.setAmountExpected(50d);

        return farmRepository.save(farm);
    }
}
