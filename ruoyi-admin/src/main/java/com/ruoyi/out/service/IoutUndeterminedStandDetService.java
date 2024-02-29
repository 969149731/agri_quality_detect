package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.out2UndeterminedStandDet;
import com.ruoyi.out.domain.outUndeterminedStandDet;

/**
 * 无判定标准检出值详细Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutUndeterminedStandDetService 
{
    /**
     * 查询无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 无判定标准检出值详细
     */
    public outUndeterminedStandDet selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId);

    /**
     * 查询无判定标准检出值详细列表
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 无判定标准检出值详细集合
     */
    public List<outUndeterminedStandDet> selectoutUndeterminedStandDetList(outUndeterminedStandDet outUndeterminedStandDet);

    /**
     * 新增无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    public int insertoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet);

    /**
     * 修改无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    public int updateoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet);

    /**
     * 批量删除无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionIds 需要删除的无判定标准检出值详细主键集合
     * @return 结果
     */
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(Long[] undeterminedStandardDetectionIds);

    /**
     * 删除无判定标准检出值详细信息
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 结果
     */
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId);

    List<out2UndeterminedStandDet> selectOutUndeterminedStandDetList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
