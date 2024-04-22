package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
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
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况集合
     */
    public List<outFruVegQualification> selectoutFruVegQualificationList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg);

}
