package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outStandCompliance;
import com.ruoyi.out.domain.outStandardReturnType;

/**
 * 参照国际组织或国家标准合格率情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutStandComplianceService 
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
     * 批量删除参照国际组织或国家标准合格率情况
     * 
     * @param interStandIds 需要删除的参照国际组织或国家标准合格率情况主键集合
     * @return 结果
     */
    public int deleteoutStandComplianceByInterStandIds(Long[] interStandIds);

    /**
     * 删除参照国际组织或国家标准合格率情况信息
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    public int deleteoutStandComplianceByInterStandId(Long interStandId);


    public List<outStandardReturnType> selectoutStandComplianceList2(outStandardReturnType outStandardReturnType);
}
