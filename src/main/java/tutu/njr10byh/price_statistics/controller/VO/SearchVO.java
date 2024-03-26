package tutu.njr10byh.price_statistics.controller.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tutu.njr10byh.price_statistics.service.condition.SearchCondition;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class SearchVO {
    @ApiModelProperty("当前页")
    private Integer currPage;
    @ApiModelProperty("每页数据大小")
    private Integer size;

    @NotNull(message = "搜索条件为空")
    @Valid
    private SearchCondition searchCondition;
}
