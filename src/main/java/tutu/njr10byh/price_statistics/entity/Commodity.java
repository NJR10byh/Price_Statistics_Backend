package tutu.njr10byh.price_statistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("commodity")
public class Commodity {
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty("ID")
    private int id;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("区域/赠品/备注")
    private String remark;

    @ApiModelProperty("品类")
    private String category;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("系列")
    private String series;

    @ApiModelProperty("上传时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
