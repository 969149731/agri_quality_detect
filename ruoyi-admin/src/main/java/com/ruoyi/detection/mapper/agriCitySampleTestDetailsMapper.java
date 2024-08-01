package com.ruoyi.detection.mapper;

import java.util.List;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 各市样品检测结果详细Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@Repository
public interface agriCitySampleTestDetailsMapper 
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
     * 删除各市样品检测结果详细
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    /**
     * 批量删除各市样品检测结果详细
     * 
     * @param citySampleTestDetailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds);

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
     * 通过各市样品检测结果详细主键删除农药检测结果信息
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细ID
     * @return 结果
     */
    public int deleteagriPesticideDetResultByCitySampleTestDetailsId(Long citySampleTestDetailsId);

    //通过当前用户的id查询当前用户所在的部门
    public String selectUserDepByUserName(String userName);

    //查询当前用户导入的各市样品检测结果详细(可以用来看历史记录的)
    List<agriCitySampleTestDetails> selectMyImportAgriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails);


    //查询各市样品检测结果详细列表，但是这边不包括检测到的农药和农药值，为了保证分页的准确性，需要分开查
    List<agriCitySampleTestDetails> selectAgriCitySampleList(agriCitySampleTestDetails agriCitySampleTestDetails);

    List<agriPesticideDetResult> selectAgriCitySampleTestDetailsListById(@Param("citySampleTestDetailsId") Long citySampleTestDetailsId);
}
