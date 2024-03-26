package tutu.njr10byh.price_statistics.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tutu.njr10byh.price_statistics.controller.VO.CommodityVO;
import tutu.njr10byh.price_statistics.controller.VO.SearchVO;
import tutu.njr10byh.price_statistics.entity.Commodity;
import tutu.njr10byh.price_statistics.error.BusinessException;

import java.util.List;

public interface CommodityService {
    Page<Commodity> getCommodityList(SearchVO searchVO) throws BusinessException;

    void addCommodity(List<CommodityVO> datalist) throws BusinessException;

    Integer deleteCommodity(String model) throws BusinessException;
}
