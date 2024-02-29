package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.out2BanPesticideDetection;
import com.ruoyi.out.domain.outBanPesticideDetection;

/**
 * 蔬菜水果禁用农药检出样品明细Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutBanPesticideDetectionService 
{
    /**
     * 查询蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 蔬菜水果禁用农药检出样品明细
     */
    public outBanPesticideDetection selectoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId);

    /**
     * 查询蔬菜水果禁用农药检出样品明细列表
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 蔬菜水果禁用农药检出样品明细集合
     */
    public List<outBanPesticideDetection> selectoutBanPesticideDetectionList(outBanPesticideDetection outBanPesticideDetection);

    /**
     * 新增蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    public int insertoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection);

    /**
     * 修改蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    public int updateoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection);

    /**
     * 批量删除蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionIds 需要删除的蔬菜水果禁用农药检出样品明细主键集合
     * @return 结果
     */
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionIds(Long[] banPesticideDetectionIds);

    /**
     * 删除蔬菜水果禁用农药检出样品明细信息
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId);

    List<out2BanPesticideDetection> selectOutBanPesticideDetectionList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
