package com.ruoyi.out.mapper;

import java.util.List;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import org.springframework.stereotype.Repository;

/**
 * 高风险品种样品检出情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outHighRiskVarietyDetMapper 
{
    /**
     * 查询高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 高风险品种样品检出情况
     */
    public outHighRiskVarietyDet selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId);

    /**
     * 查询高风险品种样品检出情况列表
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 高风险品种样品检出情况集合
     */
    public List<outHighRiskVarietyDet> selectoutHighRiskVarietyDetList(outHighRiskVarietyDet outHighRiskVarietyDet);

    /**
     * 新增高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    public int insertoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet);

    /**
     * 修改高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    public int updateoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet);

    /**
     * 删除高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 结果
     */
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId);

    /**
     * 批量删除高风险品种样品检出情况
     * 
     * @param outHighRiskVarietyDetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(Long[] outHighRiskVarietyDetIds);


    public String selectHighRiskSampleByVegFruName(String vegFruName);
}
