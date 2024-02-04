package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.detection.domain.agriOutCitySampleTestDetails;

/**
 * 各市样品检测结果详细Service接口
 * 
 * @author chenjie
 * @date 2024-01-24
 */
public interface IagriCitySampleTestDetailsService 
{
    /**
     * 查询各市样品检测结果详细
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 各市样品检测结果详细
     */
    public agriCitySampleTestDetails selectagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    /**
     * 查询各市样品检测结果详细列表
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 各市样品检测结果详细集合
     */
    public List<agriCitySampleTestDetails> selectagriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails);

    /**
     * 新增各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    public int insertagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails);

    /**
     * 修改各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    public int updateagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails);

    /**
     * 批量删除各市样品检测结果详细
     * 
     * @param citySampleTestDetailsIds 需要删除的各市样品检测结果详细主键集合
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds);

    /**
     * 删除各市样品检测结果详细信息
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    String importAgriCitySampleTestDetails(List<agriCitySampleTestDetails> agriCitySampleTestDetailsList, boolean updateSupport, String operName);

    String importAgriOutCitySampleTestDetailsList(List<agriOutCitySampleTestDetails> agriOutCitySampleTestDetailsList, boolean updateSupport, String operName);

    String importAgriOut2CitySampleTestDetailsList(List<agriOut2CitySampleTestDetails> agriOut2CitySampleTestDetailsList, boolean updateSupport, String operName);
}
