package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outUndeterminedStandDetMapper;
import com.ruoyi.out.domain.outUndeterminedStandDet;
import com.ruoyi.out.service.IoutUndeterminedStandDetService;

/**
 * 无判定标准检出值详细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outUndeterminedStandDetServiceImpl implements IoutUndeterminedStandDetService 
{
    @Autowired
    private outUndeterminedStandDetMapper outUndeterminedStandDetMapper;

    /**
     * 查询无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 无判定标准检出值详细
     */
    @Override
    public outUndeterminedStandDet selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId)
    {
        return outUndeterminedStandDetMapper.selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId);
    }

    /**
     * 查询无判定标准检出值详细列表
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 无判定标准检出值详细
     */
    @Override
    public List<outUndeterminedStandDet> selectoutUndeterminedStandDetList(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
    }

    /**
     * 新增无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    @Override
    public int insertoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.insertoutUndeterminedStandDet(outUndeterminedStandDet);
    }

    /**
     * 修改无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    @Override
    public int updateoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.updateoutUndeterminedStandDet(outUndeterminedStandDet);
    }

    /**
     * 批量删除无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionIds 需要删除的无判定标准检出值详细主键
     * @return 结果
     */
    @Override
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(Long[] undeterminedStandardDetectionIds)
    {
        return outUndeterminedStandDetMapper.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(undeterminedStandardDetectionIds);
    }

    /**
     * 删除无判定标准检出值详细信息
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 结果
     */
    @Override
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId)
    {
        return outUndeterminedStandDetMapper.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId);
    }
}
