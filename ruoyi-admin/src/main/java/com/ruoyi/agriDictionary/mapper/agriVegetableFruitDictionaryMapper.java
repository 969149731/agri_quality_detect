package com.ruoyi.agriDictionary.mapper;

import java.util.List;
import com.ruoyi.agriDictionary.domain.agriVegetableFruitDictionary;

/**
 * 蔬果表Mapper接口
 * 
 * @author chenjie
 * @date 2024-03-02
 */
public interface agriVegetableFruitDictionaryMapper 
{
    /**
     * 查询蔬果表
     * 
     * @param vegetableFruitId 蔬果表主键
     * @return 蔬果表
     */
    public agriVegetableFruitDictionary selectagriVegetableFruitDictionaryByVegetableFruitId(Long vegetableFruitId);

    /**
     * 查询蔬果表列表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 蔬果表集合
     */
    public List<agriVegetableFruitDictionary> selectagriVegetableFruitDictionaryList(agriVegetableFruitDictionary agriVegetableFruitDictionary);

    /**
     * 新增蔬果表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 结果
     */
    public int insertagriVegetableFruitDictionary(agriVegetableFruitDictionary agriVegetableFruitDictionary);

    /**
     * 修改蔬果表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 结果
     */
    public int updateagriVegetableFruitDictionary(agriVegetableFruitDictionary agriVegetableFruitDictionary);

    /**
     * 删除蔬果表
     * 
     * @param vegetableFruitId 蔬果表主键
     * @return 结果
     */
    public int deleteagriVegetableFruitDictionaryByVegetableFruitId(Long vegetableFruitId);

    /**
     * 批量删除蔬果表
     * 
     * @param vegetableFruitIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteagriVegetableFruitDictionaryByVegetableFruitIds(Long[] vegetableFruitIds);
}
