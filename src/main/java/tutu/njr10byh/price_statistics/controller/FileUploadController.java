package tutu.njr10byh.price_statistics.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tutu.njr10byh.price_statistics.controller.VO.CommodityVO;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.response.ReturnType;
import tutu.njr10byh.price_statistics.service.CommodityService;
import tutu.njr10byh.price_statistics.tools.StringTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    CommodityService commodityService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ReturnType handleFileUpload(@RequestParam("file") MultipartFile file) throws BusinessException {
        List<CommodityVO> dataList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                CommodityVO commodityVO = getmodelVO(line);
                dataList.add(commodityVO);
            }
            br.close();
        } catch (IOException e) {
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR, "文件读取失败");
        }
        commodityService.addCommodity(dataList);
        return ReturnType.create("上传成功");
    }

    private static CommodityVO getmodelVO(String line) {
        String[] fields = line.split(",", -1);
        CommodityVO commodityVO = new CommodityVO();
        commodityVO.setBrand(StringTools.isNullOrEmpty(fields[0]) ? null : fields[0].trim());
        commodityVO.setCategory(StringTools.isNullOrEmpty(fields[1]) ? null : fields[1].trim());
        commodityVO.setModel(StringTools.isNullOrEmpty(fields[2]) ? null : fields[2].trim());
        commodityVO.setChannel_price(StringTools.isNullOrEmpty(fields[3]) ? null : fields[3].trim());
        commodityVO.setSupplier(StringTools.isNullOrEmpty(fields[4]) ? null : fields[4].trim());
        return commodityVO;
    }
}
