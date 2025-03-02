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
    public double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }
}
