package FarmCollector.dtos;

import FarmCollector.model.enums.Season;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;


//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmDto implements Serializable {

    private Long id;

    @NotBlank(message = "Farm Name Is Required")
    private String name;

    @NotNull(message = "season Is Required")
    private Season season;

    @NotNull(message = "Year Is Required")
    private Integer year;

    @NotNull(message = "plantingArea Is Required")
    private Double plantingArea;

    @NotBlank (message = "cropType Is Required")
    private String cropType;

    @NotNull(message = "amountExpected Is Required")
    private Double amountExpected;

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }

    public Integer getYear() {
        return year;
    }

    public Double getPlantingArea() {
        return plantingArea;
    }

    public String getCropType() {
        return cropType;
    }

    public Double getAmountExpected() {
        return amountExpected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPlantingArea(Double plantingArea) {
        this.plantingArea = plantingArea;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public void setAmountExpected(Double amountExpected) {
        this.amountExpected = amountExpected;
    }
}