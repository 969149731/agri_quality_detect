package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.fruitVegQualification;

/**
 * 蔬果检测Service接口
 * 
 * @author chenjie
 * @date 2024-01-19
 */
public interface IfruitVegQualificationService 
{
    /**
     * 查询蔬果检测
     * 
     * @param id 蔬果检测主键
     * @return 蔬果检测
     */
    public fruitVegQualification selectfruitVegQualificationById(Long id);

    /**
     * 查询蔬果检测列表
     * 
     * @param fruitVegQualification 蔬果检测
     * @return 蔬果检测集合
     */
    public List<fruitVegQualification> selectfruitVegQualificationList(fruitVegQualification fruitVegQualification);

    /**
     * 新增蔬果检测
     * 
     * @param fruitVegQualification 蔬果检测
     * @return 结果
     */
    public int insertfruitVegQualification(fruitVegQualification fruitVegQualification);

    /**
     * 修改蔬果检测
     * 
     * @param fruitVegQualification 蔬果检测
     * @return 结果
     */
    public int updatefruitVegQualification(fruitVegQualification fruitVegQualification);

    /**
     * 批量删除蔬果检测
     * 
     * @param ids 需要删除的蔬果检测主键集合
     * @return 结果
     */
    public int deletefruitVegQualificationByIds(Long[] ids);

    /**
     * 删除蔬果检测信息
     * 
     * @param id 蔬果检测主键
     * @return 结果
     */
    public int deletefruitVegQualificationById(Long id);

    public String importFruitVegQualification(List<fruitVegQualification> fruitVegQualificationList, boolean updateSupport, String operName);
}
