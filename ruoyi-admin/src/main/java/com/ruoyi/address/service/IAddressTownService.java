package com.ruoyi.address.service;

import java.util.List;
import com.ruoyi.address.domain.AddressTown;

/**
 * 区县信息Service接口
 * 
 * @author chenjie
 * @date 2024-04-10
 */
public interface IAddressTownService 
{
    /**
     * 查询区县信息
     * 
     * @param id 区县信息主键
     * @return 区县信息
     */
    public AddressTown selectAddressTownById(Long id);

    /**
     * 查询区县信息列表
     * 
     * @param addressTown 区县信息
     * @return 区县信息集合
     */
    public List<AddressTown> selectAddressTownList(AddressTown addressTown);

    /**
     * 新增区县信息
     * 
     * @param addressTown 区县信息
     * @return 结果
     */
    public int insertAddressTown(AddressTown addressTown);

    /**
     * 修改区县信息
     * 
     * @param addressTown 区县信息
     * @return 结果
     */
    public int updateAddressTown(AddressTown addressTown);

    /**
     * 批量删除区县信息
     * 
     * @param ids 需要删除的区县信息主键集合
     * @return 结果
     */
    public int deleteAddressTownByIds(Long[] ids);

    /**
     * 删除区县信息信息
     * 
     * @param id 区县信息主键
     * @return 结果
     */
    public int deleteAddressTownById(Long id);


    public List<AddressTown> findAddressTownByCityCode(String cityCode);

}
