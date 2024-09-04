package com.ruoyi.detection.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.detection.domain.AgriCheck;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-09-04
 */
public interface IAgriCheckService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AgriCheck selectAgriCheckById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param argiCheck 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AgriCheck> selectAgriCheckList(AgriCheck argiCheck);

    /**
     * 新增【请填写功能名称】
     * 
     * @param argiCheck 【请填写功能名称】
     * @return 结果
     */
    public int insertAgriCheck(AgriCheck argiCheck);

    /**
     * 修改【请填写功能名称】
     * 
     * @param argiCheck 【请填写功能名称】
     * @return 结果
     */
    public int updateAgriCheck(AgriCheck argiCheck);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAgriCheckByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAgriCheckById(Long id);


    /**
     * 插入数据
     * @param citySampleTestDetailsId
     * @return
     */
    AjaxResult insertCheck(Long citySampleTestDetailsId,String name);

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    AjaxResult updateCheck(Long id);
}
