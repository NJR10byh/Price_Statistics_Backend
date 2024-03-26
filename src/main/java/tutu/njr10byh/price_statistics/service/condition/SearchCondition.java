package tutu.njr10byh.price_statistics.service.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchCondition {
    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("品类")
    private String category;
}
