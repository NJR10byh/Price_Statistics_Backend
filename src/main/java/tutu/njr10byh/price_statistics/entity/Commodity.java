package tutu.njr10byh.price_statistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("commodity")
public class Commodity {
    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("品类")
    private String category;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("渠道价")
    private String channel_price;

    @ApiModelProperty("供应商")
    private String supplier;
}
