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

    @ApiModelProperty("渠道价格")
    @TableField(exist = false)
    private Double channel_price;

    @ApiModelProperty("零售定价")
    @TableField(exist = false)
    private Double retail_price;

    @ApiModelProperty("建议零售价")
    @TableField(exist = false)
    private Double recommended_retail_price;

    /**
     * 最近更新
     */
    @ApiModelProperty("最近更新 价格")
    @TableField(exist = false)
    private Double recent_update_price;

    @ApiModelProperty("最近更新 时间")
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recent_update_time;

    @ApiModelProperty("最近更新 供应商")
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String recent_update_supplier;

    /**
     * 当日更新
     */
    @ApiModelProperty("当日更新 最低价")
    @TableField(exist = false)
    private Double daily_update_lowest_price;

    @ApiModelProperty("当日更新 供应商")
    @TableField(exist = false)
    private String daily_update_lowest_supplier;

    @ApiModelProperty("当日更新 次低价")
    @TableField(exist = false)
    private Double daily_update_second_lowest_price;

    @ApiModelProperty("当日更新 供应商")
    @TableField(exist = false)
    private String daily_update_second_lowest_supplier;

    /**
     * 昨日更新
     */
    @ApiModelProperty("昨日更新 最低价")
    @TableField(exist = false)
    private Double yesterday_update_lowest_price;

    @ApiModelProperty("昨日更新 供应商")
    @TableField(exist = false)
    private String yesterday_update_lowest_supplier;

    @ApiModelProperty("昨日更新 次低价")
    @TableField(exist = false)
    private Double yesterday_update_second_lowest_price;

    @ApiModelProperty("昨日更新 供应商")
    @TableField(exist = false)
    private String yesterday_update_second_lowest_supplier;

    /**
     * 前日更新
     */
    @ApiModelProperty("前日更新 最低价")
    @TableField(exist = false)
    private Double third_day_update_lowest_price;

    @ApiModelProperty("前日更新 供应商")
    @TableField(exist = false)
    private String third_day_update_lowest_supplier;

    @ApiModelProperty("前日更新 次低价")
    @TableField(exist = false)
    private Double third_day_update_second_lowest_price;

    @ApiModelProperty("前日更新 供应商")
    @TableField(exist = false)
    private String third_day_update_second_lowest_supplier;
}
