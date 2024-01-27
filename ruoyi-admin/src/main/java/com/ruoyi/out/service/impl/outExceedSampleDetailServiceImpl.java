package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outExceedSampleDetailMapper;
import com.ruoyi.out.domain.outExceedSampleDetail;
import com.ruoyi.out.service.IoutExceedSampleDetailService;

/**
 * 超标蔬菜水果样品明细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outExceedSampleDetailServiceImpl implements IoutExceedSampleDetailService 
{
    @Autowired
    private outExceedSampleDetailMapper outExceedSampleDetailMapper;

    /**
     * 查询超标蔬菜水果样品明细
     * 
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 超标蔬菜水果样品明细
     */
    @Override
    public outExceedSampleDetail selectoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId)
    {
        return outExceedSampleDetailMapper.selectoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId);
    }

    /**
     * 查询超标蔬菜水果样品明细列表
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 超标蔬菜水果样品明细
     */
    @Override
    public List<outExceedSampleDetail> selectoutExceedSampleDetailList(outExceedSampleDetail outExceedSampleDetail)
    {
        return outExceedSampleDetailMapper.selectoutExceedSampleDetailList(outExceedSampleDetail);
    }

    /**
     * 新增超标蔬菜水果样品明细
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    @Override
    public int insertoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail)
    {
        return outExceedSampleDetailMapper.insertoutExceedSampleDetail(outExceedSampleDetail);
    }

    /**
     * 修改超标蔬菜水果样品明细
     * 
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    @Override
    public int updateoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail)
    {
        return outExceedSampleDetailMapper.updateoutExceedSampleDetail(outExceedSampleDetail);
    }

    /**
     * 批量删除超标蔬菜水果样品明细
     * 
     * @param exceedSampleDetailIds 需要删除的超标蔬菜水果样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutExceedSampleDetailByExceedSampleDetailIds(Long[] exceedSampleDetailIds)
    {
        return outExceedSampleDetailMapper.deleteoutExceedSampleDetailByExceedSampleDetailIds(exceedSampleDetailIds);
    }

    /**
     * 删除超标蔬菜水果样品明细信息
     * 
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId)
    {
        return outExceedSampleDetailMapper.deleteoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId);
    }
}
