package com.ruoyi.agriDictionary.service;

import java.util.List;
import com.ruoyi.agriDictionary.domain.agriPesticideDictionary;

/**
 * 农药字典Service接口
 * 
 * @author chenjie
 * @date 2024-05-08
 */
public interface IagriPesticideDictionaryService 
{
    /**
     * 查询农药字典
     * 
     * @param pesticideId 农药字典主键
     * @return 农药字典
     */
    public agriPesticideDictionary selectagriPesticideDictionaryByPesticideId(Long pesticideId);

    /**
     * 查询农药字典列表
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 农药字典集合
     */
    public List<agriPesticideDictionary> selectagriPesticideDictionaryList(agriPesticideDictionary agriPesticideDictionary);

    /**
     * 新增农药字典
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 结果
     */
    public int insertagriPesticideDictionary(agriPesticideDictionary agriPesticideDictionary);

    /**
     * 修改农药字典
     * 
     * @param agriPesticideDictionary 农药字典
     * @return 结果
     */
    public int updateagriPesticideDictionary(agriPesticideDictionary agriPesticideDictionary);

    /**
     * 批量删除农药字典
     * 
     * @param pesticideIds 需要删除的农药字典主键集合
     * @return 结果
     */
    public int deleteagriPesticideDictionaryByPesticideIds(Long[] pesticideIds);

    /**
     * 删除农药字典信息
     * 
     * @param pesticideId 农药字典主键
     * @return 结果
     */
    public int deleteagriPesticideDictionaryByPesticideId(Long pesticideId);
}
