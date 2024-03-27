package tutu.njr10byh.price_statistics.service.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchCondition {
    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("供应商")
    private String supplier;
}
