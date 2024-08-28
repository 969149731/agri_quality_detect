package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.agriCitySampleTestDetailsLXJC;

/**
 * 例行检测详细Service接口
 * 
 * @author chenjie
 * @date 2024-08-27
 */
public interface IagriCitySampleTestDetailsLXJCService 
{
    /**
     * 查询例行检测详细
     * 
     * @param citySampleTestDetailsId 例行检测详细主键
     * @return 例行检测详细
     */
    public agriCitySampleTestDetailsLXJC selectagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    /**
     * 查询例行检测详细列表
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 例行检测详细集合
     */
    public List<agriCitySampleTestDetailsLXJC> selectagriCitySampleTestDetailsLXJCList(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC);

    /**
     * 新增例行检测详细
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 结果
     */
    public int insertagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC);

    /**
     * 修改例行检测详细
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 结果
     */
    public int updateagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC);

    /**
     * 批量删除例行检测详细
     * 
     * @param citySampleTestDetailsIds 需要删除的例行检测详细主键集合
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds);

    /**
     * 删除例行检测详细信息
     * 
     * @param citySampleTestDetailsId 例行检测详细主键
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(Long citySampleTestDetailsId);
}
