package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegQualification;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import org.apache.ibatis.annotations.Param;

/**
 * 各类蔬菜水果合格率情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outFruVegQualificationMapper 
{
    /**
     * 查询各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 各类蔬菜水果合格率情况
     */
    public outFruVegQualification selectoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId);

    /**
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况集合
     */
    public List<outFruVegQualification> selectoutFruVegQualificationList(outFruVegQualification outFruVegQualification);

    /**
     * 新增各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    public int insertoutFruVegQualification(outFruVegQualification outFruVegQualification);

    /**
     * 修改各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    public int updateoutFruVegQualification(outFruVegQualification outFruVegQualification);

    /**
     * 删除各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 结果
     */
    public int deleteoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId);

    /**
     * 批量删除各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutFruVegQualificationByFruVegQualificationIds(Long[] fruVegQualificationIds);

    public List<outFruVegSelectType> getFruVegDetResultList(@Param("params") Map<String, Object> params);

    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);
}
