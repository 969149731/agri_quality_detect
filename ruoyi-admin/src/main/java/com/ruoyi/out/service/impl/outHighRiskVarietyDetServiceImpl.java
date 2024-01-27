package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outHighRiskVarietyDetMapper;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import com.ruoyi.out.service.IoutHighRiskVarietyDetService;

/**
 * 高风险品种样品检出情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outHighRiskVarietyDetServiceImpl implements IoutHighRiskVarietyDetService 
{
    @Autowired
    private outHighRiskVarietyDetMapper outHighRiskVarietyDetMapper;

    /**
     * 查询高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 高风险品种样品检出情况
     */
    @Override
    public outHighRiskVarietyDet selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId)
    {
        return outHighRiskVarietyDetMapper.selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId);
    }

    /**
     * 查询高风险品种样品检出情况列表
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 高风险品种样品检出情况
     */
    @Override
    public List<outHighRiskVarietyDet> selectoutHighRiskVarietyDetList(outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return outHighRiskVarietyDetMapper.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
    }

    /**
     * 新增高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    @Override
    public int insertoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return outHighRiskVarietyDetMapper.insertoutHighRiskVarietyDet(outHighRiskVarietyDet);
    }

    /**
     * 修改高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    @Override
    public int updateoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return outHighRiskVarietyDetMapper.updateoutHighRiskVarietyDet(outHighRiskVarietyDet);
    }

    /**
     * 批量删除高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDetIds 需要删除的高风险品种样品检出情况主键
     * @return 结果
     */
    @Override
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(Long[] outHighRiskVarietyDetIds)
    {
        return outHighRiskVarietyDetMapper.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(outHighRiskVarietyDetIds);
    }

    /**
     * 删除高风险品种样品检出情况信息
     * 
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 结果
     */
    @Override
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId)
    {
        return outHighRiskVarietyDetMapper.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId);
    }
}
