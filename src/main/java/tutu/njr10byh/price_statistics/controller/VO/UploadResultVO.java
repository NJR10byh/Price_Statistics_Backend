package tutu.njr10byh.price_statistics.controller.VO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UploadResultVO {
    @NotBlank(message = "工号为空")
    private String jobNumber;

    @NotNull(message = "分数为空")
    private int result;
}
