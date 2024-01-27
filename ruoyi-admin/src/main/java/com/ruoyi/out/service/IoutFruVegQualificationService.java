package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outFruVegQualification;

/**
 * 各类蔬菜水果合格率情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutFruVegQualificationService 
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
     * 批量删除各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationIds 需要删除的各类蔬菜水果合格率情况主键集合
     * @return 结果
     */
    public int deleteoutFruVegQualificationByFruVegQualificationIds(Long[] fruVegQualificationIds);

    /**
     * 删除各类蔬菜水果合格率情况信息
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 结果
     */
    public int deleteoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId);
}
