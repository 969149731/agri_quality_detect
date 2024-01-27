package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outStandComplianceMapper;
import com.ruoyi.out.domain.outStandCompliance;
import com.ruoyi.out.service.IoutStandComplianceService;

/**
 * 参照国际组织或国家标准合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outStandComplianceServiceImpl implements IoutStandComplianceService 
{
    @Autowired
    private outStandComplianceMapper outStandComplianceMapper;

    /**
     * 查询参照国际组织或国家标准合格率情况
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 参照国际组织或国家标准合格率情况
     */
    @Override
    public outStandCompliance selectoutStandComplianceByInterStandId(Long interStandId)
    {
        return outStandComplianceMapper.selectoutStandComplianceByInterStandId(interStandId);
    }

    /**
     * 查询参照国际组织或国家标准合格率情况列表
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 参照国际组织或国家标准合格率情况
     */
    @Override
    public List<outStandCompliance> selectoutStandComplianceList(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.selectoutStandComplianceList(outStandCompliance);
    }

    /**
     * 新增参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    @Override
    public int insertoutStandCompliance(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.insertoutStandCompliance(outStandCompliance);
    }

    /**
     * 修改参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    @Override
    public int updateoutStandCompliance(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.updateoutStandCompliance(outStandCompliance);
    }

    /**
     * 批量删除参照国际组织或国家标准合格率情况
     * 
     * @param interStandIds 需要删除的参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutStandComplianceByInterStandIds(Long[] interStandIds)
    {
        return outStandComplianceMapper.deleteoutStandComplianceByInterStandIds(interStandIds);
    }

    /**
     * 删除参照国际组织或国家标准合格率情况信息
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutStandComplianceByInterStandId(Long interStandId)
    {
        return outStandComplianceMapper.deleteoutStandComplianceByInterStandId(interStandId);
    }
}
