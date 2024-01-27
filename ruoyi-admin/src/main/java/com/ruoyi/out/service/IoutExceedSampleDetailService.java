package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outExceedSampleDetail;

/**
 * 超标蔬菜水果样品明细Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutExceedSampleDetailService 
{
    /**
     * 查询超标蔬菜水果样品明细
     * 
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 超标蔬菜水果样品明细
     */
    public outExceedSampleDetail selectoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId);

    /**
     * 查询超标蔬菜水果样品明细列表
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 超标蔬菜水果样品明细集合
     */
    public List<outExceedSampleDetail> selectoutExceedSampleDetailList(outExceedSampleDetail outExceedSampleDetail);

    /**
     * 新增超标蔬菜水果样品明细
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    public int insertoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail);

    /**
     * 修改超标蔬菜水果样品明细
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    public int updateoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail);

    /**
     * 批量删除超标蔬菜水果样品明细
     * 
     * @param exceedSampleDetailIds 需要删除的超标蔬菜水果样品明细主键集合
     * @return 结果
     */
    public int deleteoutExceedSampleDetailByExceedSampleDetailIds(Long[] exceedSampleDetailIds);

    /**
     * 删除超标蔬菜水果样品明细信息
     * 
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 结果
     */
    public int deleteoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId);
}
