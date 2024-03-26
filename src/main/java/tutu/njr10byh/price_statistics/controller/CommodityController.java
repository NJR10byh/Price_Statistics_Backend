package tutu.njr10byh.price_statistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tutu.njr10byh.price_statistics.controller.VO.SearchVO;
import tutu.njr10byh.price_statistics.entity.Commodity;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.response.ReturnType;
import tutu.njr10byh.price_statistics.service.CommodityService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @PostMapping("/deleteCommodity")
    @ApiOperation("删除商品")
    public ReturnType deleteCommodity(@RequestParam("model") String model) throws BusinessException {
        return ReturnType.create(commodityService.deleteCommodity(model));
    }

    @PostMapping("/getCommodityList")
    @ApiOperation("获取商品列表")
    public ReturnType getCommodityList(@Valid @RequestBody SearchVO searchVO, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        Page<Commodity> result = commodityService.getCommodityList(searchVO);
        return ReturnType.create(result);
    }
}
