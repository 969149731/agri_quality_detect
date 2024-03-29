package com.ruoyi.out.mapper;

import java.util.List;
import com.ruoyi.out.domain.outBanPesticideDetection;
import org.springframework.stereotype.Repository;

/**
 * 蔬菜水果禁用农药检出样品明细Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outBanPesticideDetectionMapper 
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
     * 删除蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId);

    /**
     * 批量删除蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionIds(Long[] banPesticideDetectionIds);
}
