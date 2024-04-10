package com.ruoyi.address.service;

import java.util.List;
import com.ruoyi.address.domain.AddressProvince;

/**
 * 省份信息Service接口
 * 
 * @author chenjie
 * @date 2024-04-10
 */
public interface IAddressProvinceService 
{
    /**
     * 查询省份信息
     * 
     * @param id 省份信息主键
     * @return 省份信息
     */
    public AddressProvince selectAddressProvinceById(Long id);

    /**
     * 查询省份信息列表
     * 
     * @param addressProvince 省份信息
     * @return 省份信息集合
     */
    public List<AddressProvince> selectAddressProvinceList(AddressProvince addressProvince);

    /**
     * 新增省份信息
     * 
     * @param addressProvince 省份信息
     * @return 结果
     */
    public int insertAddressProvince(AddressProvince addressProvince);

    /**
     * 修改省份信息
     * 
     * @param addressProvince 省份信息
     * @return 结果
     */
    public int updateAddressProvince(AddressProvince addressProvince);

    /**
     * 批量删除省份信息
     * 
     * @param ids 需要删除的省份信息主键集合
     * @return 结果
     */
    public int deleteAddressProvinceByIds(Long[] ids);

    /**
     * 删除省份信息信息
     * 
     * @param id 省份信息主键
     * @return 结果
     */
    public int deleteAddressProvinceById(Long id);

    public List<AddressProvince> selectAllAddressProvinceList();

}
