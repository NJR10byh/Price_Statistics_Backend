package tutu.njr10byh.price_statistics.controller.VO;

import lombok.Data;

@Data
public class CommodityVO {
    private String brand;
    private String model;
    private Double price;
    private String supplier;
    private String remark;
}
