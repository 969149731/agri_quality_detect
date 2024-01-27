package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruVegQualificationMapper;
import com.ruoyi.out.domain.outFruVegQualification;
import com.ruoyi.out.service.IoutFruVegQualificationService;

/**
 * 各类蔬菜水果合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruVegQualificationServiceImpl implements IoutFruVegQualificationService 
{
    @Autowired
    private outFruVegQualificationMapper outFruVegQualificationMapper;

    /**
     * 查询各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public outFruVegQualification selectoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId)
    {
        return outFruVegQualificationMapper.selectoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId);
    }

    /**
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public List<outFruVegQualification> selectoutFruVegQualificationList(outFruVegQualification outFruVegQualification)
    {
        return outFruVegQualificationMapper.selectoutFruVegQualificationList(outFruVegQualification);
    }

    /**
     * 新增各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    @Override
    public int insertoutFruVegQualification(outFruVegQualification outFruVegQualification)
    {
        return outFruVegQualificationMapper.insertoutFruVegQualification(outFruVegQualification);
    }

    /**
     * 修改各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    @Override
    public int updateoutFruVegQualification(outFruVegQualification outFruVegQualification)
    {
        return outFruVegQualificationMapper.updateoutFruVegQualification(outFruVegQualification);
    }

    /**
     * 批量删除各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationIds 需要删除的各类蔬菜水果合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruVegQualificationByFruVegQualificationIds(Long[] fruVegQualificationIds)
    {
        return outFruVegQualificationMapper.deleteoutFruVegQualificationByFruVegQualificationIds(fruVegQualificationIds);
    }

    /**
     * 删除各类蔬菜水果合格率情况信息
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId)
    {
        return outFruVegQualificationMapper.deleteoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId);
    }
}
