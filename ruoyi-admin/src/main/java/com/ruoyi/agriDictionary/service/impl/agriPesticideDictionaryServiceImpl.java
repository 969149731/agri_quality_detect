package com.ruoyi.agriDictionary.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agriDictionary.mapper.agriPesticideDictionaryMapper;
import com.ruoyi.agriDictionary.domain.agriPesticideDictionary;
import com.ruoyi.agriDictionary.service.IagriPesticideDictionaryService;

/**
 * 农药字典Service业务层处理
 * 
 * @author chenjie
 * @date 2024-03-02
 */
@Service
public class agriPesticideDictionaryServiceImpl implements IagriPesticideDictionaryService 
{
    @Autowired
    private agriPesticideDictionaryMapper agriPesticideDictionaryMapper;

    /**
     * 查询农药字典
     * 
     * @param pesticideId 农药字典主键
     * @return 农药字典
     */
    @Override
    public agriPesticideDictionary selectagriPesticideDictionaryByPesticideId(Long pesticideId)
    {
        return agriPesticideDictionaryMapper.selectagriPesticideDictionaryByPesticideId(pesticideId);
    }

    /**
     * 查询农药字典列表
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 农药字典
     */
    @Override
    public List<agriPesticideDictionary> selectagriPesticideDictionaryList(agriPesticideDictionary agriPesticideDictionary)
    {
        return agriPesticideDictionaryMapper.selectagriPesticideDictionaryList(agriPesticideDictionary);
    }

    /**
     * 新增农药字典
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 结果
     */
    @Override
    public int insertagriPesticideDictionary(agriPesticideDictionary agriPesticideDictionary)
    {
        return agriPesticideDictionaryMapper.insertagriPesticideDictionary(agriPesticideDictionary);
    }

    /**
     * 修改农药字典
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 结果
     */
    @Override
    public int updateagriPesticideDictionary(agriPesticideDictionary agriPesticideDictionary)
    {
        return agriPesticideDictionaryMapper.updateagriPesticideDictionary(agriPesticideDictionary);
    }

    /**
     * 批量删除农药字典
     * 
     * @param pesticideIds 需要删除的农药字典主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideDictionaryByPesticideIds(Long[] pesticideIds)
    {
        return agriPesticideDictionaryMapper.deleteagriPesticideDictionaryByPesticideIds(pesticideIds);
    }

    /**
     * 删除农药字典信息
     * 
     * @param pesticideId 农药字典主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideDictionaryByPesticideId(Long pesticideId)
    {
        return agriPesticideDictionaryMapper.deleteagriPesticideDictionaryByPesticideId(pesticideId);
    }
}
