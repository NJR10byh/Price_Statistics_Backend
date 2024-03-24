package tutu.njr10byh.price_statistics.service;

import tutu.njr10byh.price_statistics.controller.VO.UploadResultVO;
import tutu.njr10byh.price_statistics.entity.Result;
import tutu.njr10byh.price_statistics.error.BusinessException;

public interface ResultService {
    Result getResultByJobNumber(String jobNumber)
            throws BusinessException;

    Result uploadResult(UploadResultVO uploadResultVO)
            throws BusinessException;
}
