package com.ruoyi.address.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.address.mapper.AddressProvinceMapper;
import com.ruoyi.address.domain.AddressProvince;
import com.ruoyi.address.service.IAddressProvinceService;

/**
 * 省份信息Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@Service
public class AddressProvinceServiceImpl implements IAddressProvinceService 
{
    @Autowired
    private AddressProvinceMapper addressProvinceMapper;

    /**
     * 查询省份信息
     * 
     * @param id 省份信息主键
     * @return 省份信息
     */
    @Override
    public AddressProvince selectAddressProvinceById(Long id)
    {
        return addressProvinceMapper.selectAddressProvinceById(id);
    }

    /**
     * 查询省份信息列表
     * 
     * @param addressProvince 省份信息
     * @return 省份信息
     */
    @Override
    public List<AddressProvince> selectAddressProvinceList(AddressProvince addressProvince)
    {
        return addressProvinceMapper.selectAddressProvinceList(addressProvince);
    }

    /**
     * 新增省份信息
     * 
     * @param addressProvince 省份信息
     * @return 结果
     */
    @Override
    public int insertAddressProvince(AddressProvince addressProvince)
    {
        return addressProvinceMapper.insertAddressProvince(addressProvince);
    }

    /**
     * 修改省份信息
     * 
     * @param addressProvince 省份信息
     * @return 结果
     */
    @Override
    public int updateAddressProvince(AddressProvince addressProvince)
    {
        return addressProvinceMapper.updateAddressProvince(addressProvince);
    }

    /**
     * 批量删除省份信息
     * 
     * @param ids 需要删除的省份信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressProvinceByIds(Long[] ids)
    {
        return addressProvinceMapper.deleteAddressProvinceByIds(ids);
    }

    /**
     * 删除省份信息信息
     * 
     * @param id 省份信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressProvinceById(Long id)
    {
        return addressProvinceMapper.deleteAddressProvinceById(id);
    }

    @Override
    public List<AddressProvince> selectAllAddressProvinceList() {
        return addressProvinceMapper.selectAllAddressProvinceList();
    }


}
