package com.ruoyi.detection.mapper;

import java.util.List;
import com.ruoyi.detection.domain.fruitVegQualification;
import org.springframework.stereotype.Repository;

/**
 * 蔬果检测Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-19
 */
@Repository
public interface fruitVegQualificationMapper 
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
     * 删除蔬果检测
     * 
     * @param id 蔬果检测主键
     * @return 结果
     */
    public int deletefruitVegQualificationById(Long id);

    /**
     * 批量删除蔬果检测
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletefruitVegQualificationByIds(Long[] ids);
}
