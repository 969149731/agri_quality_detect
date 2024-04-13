package com.ruoyi.address.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.address.mapper.AddressCityMapper;
import com.ruoyi.address.domain.AddressCity;
import com.ruoyi.address.service.IAddressCityService;

/**
 * 城市信息Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@Service
public class AddressCityServiceImpl implements IAddressCityService 
{
    @Autowired
    private AddressCityMapper addressCityMapper;

    /**
     * 查询城市信息
     * 
     * @param id 城市信息主键
     * @return 城市信息
     */
    @Override
    public AddressCity selectAddressCityById(Long id)
    {
        return addressCityMapper.selectAddressCityById(id);
    }

    /**
     * 查询城市信息列表
     * 
     * @param addressCity 城市信息
     * @return 城市信息
     */
    @Override
    public List<AddressCity> selectAddressCityList(AddressCity addressCity)
    {
        return addressCityMapper.selectAddressCityList(addressCity);
    }

    /**
     * 新增城市信息
     * 
     * @param addressCity 城市信息
     * @return 结果
     */
    @Override
    public int insertAddressCity(AddressCity addressCity)
    {
        return addressCityMapper.insertAddressCity(addressCity);
    }

    /**
     * 修改城市信息
     * 
     * @param addressCity 城市信息
     * @return 结果
     */
    @Override
    public int updateAddressCity(AddressCity addressCity)
    {
        return addressCityMapper.updateAddressCity(addressCity);
    }

    /**
     * 批量删除城市信息
     * 
     * @param ids 需要删除的城市信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressCityByIds(Long[] ids)
    {
        return addressCityMapper.deleteAddressCityByIds(ids);
    }

    /**
     * 删除城市信息信息
     * 
     * @param id 城市信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressCityById(Long id)
    {
        return addressCityMapper.deleteAddressCityById(id);
    }

    @Override
    public List<AddressCity> findAddressCityByProvinceCode(String provinceCode) {
        return addressCityMapper.findAddressCityByProvinceCode(provinceCode);
    }

    @Override
    public String selectCityNameByCityCode(String CityCode) {
        return addressCityMapper.selectCityNameByCityCode(CityCode);
    }
}
