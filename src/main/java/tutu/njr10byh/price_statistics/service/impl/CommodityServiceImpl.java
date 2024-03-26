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

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Page<Commodity> getCommodityList(SearchVO searchVO) throws BusinessException {
        QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>().isNotNull("model");
        // 品牌不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getBrand()),
                "brand",
                searchVO.getSearchCondition().getBrand());
        // 品类不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getCategory()),
                "category",
                searchVO.getSearchCondition().getCategory());
        // 型号不为空时模糊查询
        wrapper.like(StringUtils.isNotBlank(searchVO.getSearchCondition().getModel()),
                "model",
                searchVO.getSearchCondition().getModel());
        Page<Commodity> page = new Page<>(searchVO.getCurrPage(), searchVO.getSize());
        return commodityMapper.selectPage(page, wrapper);
    }

    @Override
    public void addCommodity(List<CommodityVO> datalist) throws BusinessException {
        try {
            for (CommodityVO commodityVO : datalist) {
                Commodity commodity = new Commodity();
                commodity.setBrand(commodityVO.getBrand());
                commodity.setCategory(commodityVO.getCategory());
                commodity.setModel(commodityVO.getModel());
                commodity.setSupplier(commodityVO.getSupplier());
                commodity.setChannel_price(commodityVO.getChannel_price());
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
