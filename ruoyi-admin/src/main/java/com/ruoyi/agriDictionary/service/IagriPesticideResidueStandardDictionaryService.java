package com.ruoyi.agriDictionary.service;

import java.util.List;
import com.ruoyi.agriDictionary.domain.agriPesticideResidueStandardDictionary;

/**
 * 农药最大残留限量参考标准Service接口
 * 
 * @author chenjie
 * @date 2024-03-02
 */
public interface IagriPesticideResidueStandardDictionaryService 
{
    /**
     * 查询农药最大残留限量参考标准
     * 
     * @param pesticideResidueStandardId 农药最大残留限量参考标准主键
     * @return 农药最大残留限量参考标准
     */
    public agriPesticideResidueStandardDictionary selectagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(Long pesticideResidueStandardId);

    /**
     * 查询农药最大残留限量参考标准列表
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 农药最大残留限量参考标准集合
     */
    public List<agriPesticideResidueStandardDictionary> selectagriPesticideResidueStandardDictionaryList(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary);

    /**
     * 新增农药最大残留限量参考标准
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 结果
     */
    public int insertagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary);

    /**
     * 修改农药最大残留限量参考标准
     * 
     * @param agriPesticideResidueStandardDictionary 农药最大残留限量参考标准
     * @return 结果
     */
    public int updateagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary);

    /**
     * 批量删除农药最大残留限量参考标准
     * 
     * @param pesticideResidueStandardIds 需要删除的农药最大残留限量参考标准主键集合
     * @return 结果
     */
    public int deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardIds(Long[] pesticideResidueStandardIds);

    /**
     * 删除农药最大残留限量参考标准信息
     * 
     * @param pesticideResidueStandardId 农药最大残留限量参考标准主键
     * @return 结果
     */
    public int deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(Long pesticideResidueStandardId);
}
