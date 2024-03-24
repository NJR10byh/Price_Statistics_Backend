package tutu.njr10byh.price_statistics.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tutu.njr10byh.price_statistics.controller.VO.UploadResultVO;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.response.ReturnType;
import tutu.njr10byh.price_statistics.service.ResultService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    ResultService resultService;

    @PostMapping("/getResultByJobNumber")
    @ApiOperation("通过工号获取测评结果")
    public ReturnType getResultByJobNumber(@RequestParam("jobNumber") String jobNumber)
            throws BusinessException {
        return ReturnType.create(resultService.getResultByJobNumber(jobNumber));
    }

    @PostMapping("/uploadResult")
    @ApiOperation("上传测评结果")
    public ReturnType uploadResult(@Valid @RequestBody UploadResultVO uploadResultVO, BindingResult bindingResult)
            throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ReturnType.create(resultService.uploadResult(uploadResultVO));
    }
}
