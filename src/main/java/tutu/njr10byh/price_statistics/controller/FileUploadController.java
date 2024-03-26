package tutu.njr10byh.price_statistics.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tutu.njr10byh.price_statistics.controller.VO.GoodsModelVO;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.response.ReturnType;
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
    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ReturnType handleFileUpload(@RequestParam("file") MultipartFile file) throws BusinessException {
        List<GoodsModelVO> dataList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                GoodsModelVO goodsModelVO = getGoodsModelVO(line);
                dataList.add(goodsModelVO);
            }
            br.close();
        } catch (IOException e) {
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR, "文件读取失败");
        }
        return ReturnType.create(dataList);
    }

    private static GoodsModelVO getGoodsModelVO(String line) {
        String[] fields = line.split(",", -1);
        GoodsModelVO goodsModelVO = new GoodsModelVO();
        goodsModelVO.setBrand(StringTools.isNullOrEmpty(fields[0]) ? null : fields[0].trim());
        goodsModelVO.setCategory(StringTools.isNullOrEmpty(fields[1]) ? null : fields[1].trim());
        goodsModelVO.setModel(StringTools.isNullOrEmpty(fields[2]) ? null : fields[2].trim());
        goodsModelVO.setChannel_price(StringTools.isNullOrEmpty(fields[3]) ? null : fields[3].trim());
        goodsModelVO.setSupplier(StringTools.isNullOrEmpty(fields[4]) ? null : fields[4].trim());
        return goodsModelVO;
    }
}
