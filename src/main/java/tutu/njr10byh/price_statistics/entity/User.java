package tutu.njr10byh.price_statistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("用户ID")
    private int id;

    @ApiModelProperty("用户登录名")
    private String userName;

    @ApiModelProperty("用户权限")
    private String role;
}
