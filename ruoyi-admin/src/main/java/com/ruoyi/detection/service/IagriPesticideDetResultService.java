package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.agriPesticideDetResult;

/**
 * 农药检测结果Service接口
 * 
 * @author chenjie
 * @date 2024-01-24
 */
public interface IagriPesticideDetResultService 
{
    /**
     * 查询农药检测结果
     * 
     * @param pesticideDetResultId 农药检测结果主键
     * @return 农药检测结果
     */
    public agriPesticideDetResult selectagriPesticideDetResultByPesticideDetResultId(Long pesticideDetResultId);

    /**
     * 查询农药检测结果列表
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 农药检测结果集合
     */
    public List<agriPesticideDetResult> selectagriPesticideDetResultList(agriPesticideDetResult agriPesticideDetResult);

    /**
     * 新增农药检测结果
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 结果
     */
    public int insertagriPesticideDetResult(agriPesticideDetResult agriPesticideDetResult);

    /**
     * 修改农药检测结果
     * 
     * @param agriPesticideDetResult 农药检测结果
     * @return 结果
     */
    public int updateagriPesticideDetResult(agriPesticideDetResult agriPesticideDetResult);

    /**
     * 批量删除农药检测结果
     * 
     * @param pesticideDetResultIds 需要删除的农药检测结果主键集合
     * @return 结果
     */
    public int deleteagriPesticideDetResultByPesticideDetResultIds(Long[] pesticideDetResultIds);

    /**
     * 删除农药检测结果信息
     * 
     * @param pesticideDetResultId 农药检测结果主键
     * @return 结果
     */
    public int deleteagriPesticideDetResultByPesticideDetResultId(Long pesticideDetResultId);
}
