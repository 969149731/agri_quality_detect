package com.ruoyi.agriDictionary.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agriDictionary.mapper.agriVegetableFruitDictionaryMapper;
import com.ruoyi.agriDictionary.domain.agriVegetableFruitDictionary;
import com.ruoyi.agriDictionary.service.IagriVegetableFruitDictionaryService;

/**
 * 蔬果表Service业务层处理
 * 
 * @author chenjie
 * @date 2024-03-02
 */
@Service
public class agriVegetableFruitDictionaryServiceImpl implements IagriVegetableFruitDictionaryService 
{
    @Autowired
    private agriVegetableFruitDictionaryMapper agriVegetableFruitDictionaryMapper;

    /**
     * 查询蔬果表
     * 
     * @param vegetableFruitId 蔬果表主键
     * @return 蔬果表
     */
    @Override
    public agriVegetableFruitDictionary selectagriVegetableFruitDictionaryByVegetableFruitId(Long vegetableFruitId)
    {
        return agriVegetableFruitDictionaryMapper.selectagriVegetableFruitDictionaryByVegetableFruitId(vegetableFruitId);
    }

    /**
     * 查询蔬果表列表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 蔬果表
     */
    @Override
    public List<agriVegetableFruitDictionary> selectagriVegetableFruitDictionaryList(agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        return agriVegetableFruitDictionaryMapper.selectagriVegetableFruitDictionaryList(agriVegetableFruitDictionary);
    }

    /**
     * 新增蔬果表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 结果
     */
    @Override
    public int insertagriVegetableFruitDictionary(agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        return agriVegetableFruitDictionaryMapper.insertagriVegetableFruitDictionary(agriVegetableFruitDictionary);
    }

    /**
     * 修改蔬果表
     * 
     * @param agriVegetableFruitDictionary 蔬果表
     * @return 结果
     */
    @Override
    public int updateagriVegetableFruitDictionary(agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        return agriVegetableFruitDictionaryMapper.updateagriVegetableFruitDictionary(agriVegetableFruitDictionary);
    }

    /**
     * 批量删除蔬果表
     * 
     * @param vegetableFruitIds 需要删除的蔬果表主键
     * @return 结果
     */
    @Override
    public int deleteagriVegetableFruitDictionaryByVegetableFruitIds(Long[] vegetableFruitIds)
    {
        return agriVegetableFruitDictionaryMapper.deleteagriVegetableFruitDictionaryByVegetableFruitIds(vegetableFruitIds);
    }

    /**
     * 删除蔬果表信息
     * 
     * @param vegetableFruitId 蔬果表主键
     * @return 结果
     */
    @Override
    public int deleteagriVegetableFruitDictionaryByVegetableFruitId(Long vegetableFruitId)
    {
        return agriVegetableFruitDictionaryMapper.deleteagriVegetableFruitDictionaryByVegetableFruitId(vegetableFruitId);
    }
}
