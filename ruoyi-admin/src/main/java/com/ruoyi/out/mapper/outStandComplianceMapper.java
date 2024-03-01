package com.ruoyi.out.mapper;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outStandCompliance;
import com.ruoyi.out.domain.outStandardReturnType;

/**
 * 参照国际组织或国家标准合格率情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outStandComplianceMapper 
{
    /**
     * 查询参照国际组织或国家标准合格率情况
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 参照国际组织或国家标准合格率情况
     */
    public outStandCompliance selectoutStandComplianceByInterStandId(Long interStandId);

    /**
     * 查询参照国际组织或国家标准合格率情况列表
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 参照国际组织或国家标准合格率情况集合
     */
    public List<outStandCompliance> selectoutStandComplianceList(outStandCompliance outStandCompliance);

    /**
     * 新增参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    public int insertoutStandCompliance(outStandCompliance outStandCompliance);

    /**
     * 修改参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    public int updateoutStandCompliance(outStandCompliance outStandCompliance);

    /**
     * 删除参照国际组织或国家标准合格率情况
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    public int deleteoutStandComplianceByInterStandId(Long interStandId);

    /**
     * 批量删除参照国际组织或国家标准合格率情况
     * 
     * @param interStandIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutStandComplianceByInterStandIds(Long[] interStandIds);


    public List<String> getAllPesticideList();

    public List<outFruVegSelectType> getFruVegDetResultList(String sampleCode);
    List<agriCitySampleTestDetails> getFruVegDetSample();
    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

}
