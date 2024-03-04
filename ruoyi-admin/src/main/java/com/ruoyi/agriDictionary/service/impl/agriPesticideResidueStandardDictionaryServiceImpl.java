package com.ruoyi.agriDictionary.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agriDictionary.mapper.agriPesticideResidueStandardDictionaryMapper;
import com.ruoyi.agriDictionary.domain.agriPesticideResidueStandardDictionary;
import com.ruoyi.agriDictionary.service.IagriPesticideResidueStandardDictionaryService;

/**
 * 农药最大残留限量参考标准Service业务层处理
 * 
 * @author chenjie
 * @date 2024-03-02
 */
@Service
public class agriPesticideResidueStandardDictionaryServiceImpl implements IagriPesticideResidueStandardDictionaryService 
{
    @Autowired
    private agriPesticideResidueStandardDictionaryMapper agriPesticideResidueStandardDictionaryMapper;

    /**
     * 查询农药最大残留限量参考标准
     * 
     * @param pesticideResidueStandardId 农药最大残留限量参考标准主键
     * @return 农药最大残留限量参考标准
     */
    @Override
    public agriPesticideResidueStandardDictionary selectagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(Long pesticideResidueStandardId)
    {
        return agriPesticideResidueStandardDictionaryMapper.selectagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(pesticideResidueStandardId);
    }

    /**
     * 查询农药最大残留限量参考标准列表
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 农药最大残留限量参考标准
     */
    @Override
    public List<agriPesticideResidueStandardDictionary> selectagriPesticideResidueStandardDictionaryList(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        return agriPesticideResidueStandardDictionaryMapper.selectagriPesticideResidueStandardDictionaryList(agriPesticideResidueStandardDictionary);
    }

    /**
     * 新增农药最大残留限量参考标准
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 结果
     */
    @Override
    public int insertagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        return agriPesticideResidueStandardDictionaryMapper.insertagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary);
    }

    /**
     * 修改农药最大残留限量参考标准
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 结果
     */
    @Override
    public int updateagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        return agriPesticideResidueStandardDictionaryMapper.updateagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary);
    }

    /**
     * 批量删除农药最大残留限量参考标准
     * 
     * @param pesticideResidueStandardIds 需要删除的农药最大残留限量参考标准主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardIds(Long[] pesticideResidueStandardIds)
    {
        return agriPesticideResidueStandardDictionaryMapper.deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardIds(pesticideResidueStandardIds);
    }

    /**
     * 删除农药最大残留限量参考标准信息
     * 
     * @param pesticideResidueStandardId 农药最大残留限量参考标准主键
     * @return 结果
     */
    @Override
    public int deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(Long pesticideResidueStandardId)
    {
        return agriPesticideResidueStandardDictionaryMapper.deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(pesticideResidueStandardId);
    }
}
