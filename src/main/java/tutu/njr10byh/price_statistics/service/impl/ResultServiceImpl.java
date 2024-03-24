package tutu.njr10byh.price_statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutu.njr10byh.price_statistics.controller.VO.UploadResultVO;
import tutu.njr10byh.price_statistics.entity.Result;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.mapper.ResultMapper;
import tutu.njr10byh.price_statistics.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultMapper resultMapper;

    @Override
    public Result getResultByJobNumber(String jobNumber)
            throws BusinessException {
        QueryWrapper<Result> resultWrapper = new QueryWrapper<Result>().eq("job_number", jobNumber);
        Result result = resultMapper.selectOne(resultWrapper);
        if (result == null) {
            throw new BusinessException(EmBusinessError.RECORD_NOT_EXIST, "未查询到数据");
        }
        return result;
    }

    @Override
    public Result uploadResult(UploadResultVO uploadResultVO)
            throws BusinessException {
        String jobNumber = uploadResultVO.getJobNumber();
        int result = uploadResultVO.getResult();
        QueryWrapper<Result> resultWrapper = new QueryWrapper<Result>().eq("job_number", jobNumber);
        Result originResult = resultMapper.selectOne(resultWrapper);
        if (originResult == null) {
            throw new BusinessException(EmBusinessError.RECORD_NOT_EXIST, "未查询到数据，更新失败");
        }
        if (originResult.getCount() == 0) {
            throw new BusinessException(EmBusinessError.CANNOT_BE_MODIFIED, "已经测评三次，无法再次测评");
        }
        int count = originResult.getCount();
        int result1 = originResult.getResult1();
        int result2 = originResult.getResult2();
        int result3 = originResult.getResult3();
        switch (count) {
            case 3:
                result1 = result;
                break;
            case 2:
                result2 = result;
                break;
            case 1:
                result3 = result;
                break;
        }
        Result newResult = new Result();
        newResult.setId(originResult.getId());
        newResult.setJobNumber(originResult.getJobNumber());
        newResult.setResult1(result1);
        newResult.setResult2(result2);
        newResult.setResult3(result3);
        newResult.setCount(--count);
        try {
            resultMapper.updateById(newResult);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR, "更新失败");
        }
        return resultMapper.selectOne(resultWrapper);
    }
}
