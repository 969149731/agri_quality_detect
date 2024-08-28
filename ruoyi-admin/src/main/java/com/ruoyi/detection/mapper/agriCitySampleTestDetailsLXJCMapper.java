package com.ruoyi.detection.mapper;

import java.util.List;
import com.ruoyi.detection.domain.agriCitySampleTestDetailsLXJC;
import com.ruoyi.detection.domain.agriPesticideDetResult;

/**
 * 例行检测详细Mapper接口
 * 
 * @author chenjie
 * @date 2024-08-27
 */
public interface agriCitySampleTestDetailsLXJCMapper 
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
     * 删除例行检测详细
     * 
     * @param citySampleTestDetailsId 例行检测详细主键
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    /**
     * 批量删除例行检测详细
     * 
     * @param citySampleTestDetailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds);

    /**
     * 批量删除农药检测结果
     * 
     * @param citySampleTestDetailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteagriPesticideDetResultByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds);
    
    /**
     * 批量新增农药检测结果
     * 
     * @param agriPesticideDetResultList 农药检测结果列表
     * @return 结果
     */
    public int batchagriPesticideDetResult(List<agriPesticideDetResult> agriPesticideDetResultList);
    

    /**
     * 通过例行检测详细主键删除农药检测结果信息
     * 
     * @param citySampleTestDetailsId 例行检测详细ID
     * @return 结果
     */
    public int deleteagriPesticideDetResultByCitySampleTestDetailsId(Long citySampleTestDetailsId);
}
