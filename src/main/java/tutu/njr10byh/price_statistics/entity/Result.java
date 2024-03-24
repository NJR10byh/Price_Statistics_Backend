package tutu.njr10byh.price_statistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("result")
public class Result {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("测评ID")
    private int id;

    @ApiModelProperty("用户工号")
    private String jobNumber;

    @ApiModelProperty("剩余答题次数")
    private int count;

    @ApiModelProperty("第一次答题分数")
    private int result1;

    @ApiModelProperty("第二次答题分数")
    private int result2;

    @ApiModelProperty("第三次答题分数")
    private int result3;
}
