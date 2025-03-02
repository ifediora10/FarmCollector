package FarmCollector.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class FarmReportDto {
    private Double expectedAmount;
    private Double actualAmount;

    public FarmReportDto(Double expectedAmount, Double actualAmount) {
        this.expectedAmount = expectedAmount;
        this.actualAmount = actualAmount;
    }
    public Double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
}
