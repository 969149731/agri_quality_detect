package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outBanPesticideDetectionMapper;
import com.ruoyi.out.domain.outBanPesticideDetection;
import com.ruoyi.out.service.IoutBanPesticideDetectionService;

/**
 * 蔬菜水果禁用农药检出样品明细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outBanPesticideDetectionServiceImpl implements IoutBanPesticideDetectionService 
{
    @Autowired
    private outBanPesticideDetectionMapper outBanPesticideDetectionMapper;

    /**
     * 查询蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 蔬菜水果禁用农药检出样品明细
     */
    @Override
    public outBanPesticideDetection selectoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId)
    {
        return outBanPesticideDetectionMapper.selectoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId);
    }

    /**
     * 查询蔬菜水果禁用农药检出样品明细列表
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 蔬菜水果禁用农药检出样品明细
     */
    @Override
    public List<outBanPesticideDetection> selectoutBanPesticideDetectionList(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.selectoutBanPesticideDetectionList(outBanPesticideDetection);
    }

    /**
     * 新增蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    @Override
    public int insertoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.insertoutBanPesticideDetection(outBanPesticideDetection);
    }

    /**
     * 修改蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    @Override
    public int updateoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.updateoutBanPesticideDetection(outBanPesticideDetection);
    }

    /**
     * 批量删除蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionIds 需要删除的蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionIds(Long[] banPesticideDetectionIds)
    {
        return outBanPesticideDetectionMapper.deleteoutBanPesticideDetectionByBanPesticideDetectionIds(banPesticideDetectionIds);
    }

    /**
     * 删除蔬菜水果禁用农药检出样品明细信息
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId)
    {
        return outBanPesticideDetectionMapper.deleteoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId);
    }
}
