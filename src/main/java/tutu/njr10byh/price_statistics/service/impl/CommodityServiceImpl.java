package tutu.njr10byh.price_statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tutu.njr10byh.price_statistics.controller.VO.CommodityVO;
import tutu.njr10byh.price_statistics.controller.VO.SearchVO;
import tutu.njr10byh.price_statistics.entity.Commodity;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.mapper.CommodityMapper;
import tutu.njr10byh.price_statistics.service.CommodityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Page<Commodity> getCommodityList(SearchVO searchVO) throws BusinessException {
        QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>().isNotNull("model");
        // 品牌不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getBrand()), "brand", searchVO.getSearchCondition().getBrand());
        // 型号不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getModel()), "model", searchVO.getSearchCondition().getModel());
        // 供应商不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getSupplier()), "supplier", searchVO.getSearchCondition().getSupplier());
        Page<Commodity> page = new Page<>(searchVO.getCurrPage(), searchVO.getSize());
        Page<Commodity> result = commodityMapper.selectPage(page, wrapper);
        filterRecords(result.getRecords());
        result.getRecords().forEach(commodity -> {
            commodity.setRecent_update_price(commodity.getPrice());
            commodity.setRecent_update_time(commodity.getUpdateTime());
            commodity.setRecent_update_supplier(commodity.getSupplier());
        });
        return result;
    }

    /**
     * 筛选出每个品牌和型号对应的最新更新时间的商品
     *
     * @param records 商品列表
     */
    public void filterRecords(List<Commodity> records) {
        // 创建Map存储品牌和型号对应的最新更新时间
        Map<String, Map<String, Commodity>> brandModelCommodityMap = new HashMap<>();
        for (Commodity commodity : records) {
            String brand = commodity.getBrand();
            String model = commodity.getModel();

            if (!brandModelCommodityMap.containsKey(brand)) {
                brandModelCommodityMap.put(brand, new HashMap<>());
            }
            Map<String, Commodity> modelCommodityMap = brandModelCommodityMap.get(brand);

            if (!modelCommodityMap.containsKey(model) || commodity.getUpdateTime().after(modelCommodityMap.get(model).getUpdateTime())) {
                modelCommodityMap.put(model, commodity);
            }
        }
        // 清空原始records列表
        records.clear();
        // 将筛选后的数据添加回原始records列表
        for (Map<String, Commodity> modelCommodityMap : brandModelCommodityMap.values()) {
            records.addAll(modelCommodityMap.values());
        }
    }

    @Override
    public void addCommodity(List<CommodityVO> datalist) throws BusinessException {
        try {
            for (CommodityVO commodityVO : datalist) {
                Commodity commodity = new Commodity();
                commodity.setBrand(commodityVO.getBrand());
                commodity.setModel(commodityVO.getModel());
                commodity.setPrice(commodityVO.getPrice());
                commodity.setSupplier(commodityVO.getSupplier());
                commodity.setRemark(commodityVO.getRemark());
                commodityMapper.insert(commodity);
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "该型号已存在");
        }
    }

    @Override
    public Integer deleteCommodity(String model) throws BusinessException {
        return 1;
    }


}
