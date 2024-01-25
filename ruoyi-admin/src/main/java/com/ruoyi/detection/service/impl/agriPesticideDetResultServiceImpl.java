package com.ruoyi.detection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.service.IagriPesticideDetResultService;

/**
 * 农药检测结果Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@Service
public class agriPesticideDetResultServiceImpl implements IagriPesticideDetResultService 
{
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;

    /**
     * 查询农药检测结果
     * 
     * @param pesticideDetResultId 农药检测结果主键
     * @return 农药检测结果
     */
    @Override
    public agriPesticideDetResult selectagriPesticideDetResultByPesticideDetResultId(Long pesticideDetResultId)
    {
        return agriPesticideDetResultMapper.selectagriPesticideDetResultByPesticideDetResultId(pesticideDetResultId);
    }

    /**
     * 查询农药检测结果列表
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 农药检测结果
     */
    @Override
    public List<agriPesticideDetResult> selectagriPesticideDetResultList(agriPesticideDetResult agriPesticideDetResult)
    {
        return agriPesticideDetResultMapper.selectagriPesticideDetResultList(agriPesticideDetResult);
    }

    /**
     * 新增农药检测结果
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 结果
     */
    @Override
    public int insertagriPesticideDetResult(agriPesticideDetResult agriPesticideDetResult)
    {
        return agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
    }

    /**
     * 修改农药检测结果
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 结果
     */
    @Override
    public int updateagriPesticideDetResult(agriPesticideDetResult agriPesticideDetResult)
    {
        return agriPesticideDetResultMapper.updateagriPesticideDetResult(agriPesticideDetResult);
    }

    /**
     * 批量删除农药检测结果
     * 
     * @param pesticideDetResultIds 需要删除的农药检测结果主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideDetResultByPesticideDetResultIds(Long[] pesticideDetResultIds)
    {
        return agriPesticideDetResultMapper.deleteagriPesticideDetResultByPesticideDetResultIds(pesticideDetResultIds);
    }

    /**
     * 删除农药检测结果信息
     * 
     * @param pesticideDetResultId 农药检测结果主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideDetResultByPesticideDetResultId(Long pesticideDetResultId)
    {
        return agriPesticideDetResultMapper.deleteagriPesticideDetResultByPesticideDetResultId(pesticideDetResultId);
    }
}
