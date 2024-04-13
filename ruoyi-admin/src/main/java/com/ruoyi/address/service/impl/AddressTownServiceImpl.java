package com.ruoyi.address.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.address.mapper.AddressTownMapper;
import com.ruoyi.address.domain.AddressTown;
import com.ruoyi.address.service.IAddressTownService;

/**
 * 区县信息Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@Service
public class AddressTownServiceImpl implements IAddressTownService 
{
    @Autowired
    private AddressTownMapper addressTownMapper;

    /**
     * 查询区县信息
     * 
     * @param id 区县信息主键
     * @return 区县信息
     */
    @Override
    public AddressTown selectAddressTownById(Long id)
    {
        return addressTownMapper.selectAddressTownById(id);
    }

    /**
     * 查询区县信息列表
     * 
     * @param addressTown 区县信息
     * @return 区县信息
     */
    @Override
    public List<AddressTown> selectAddressTownList(AddressTown addressTown)
    {
        return addressTownMapper.selectAddressTownList(addressTown);
    }

    /**
     * 新增区县信息
     * 
     * @param addressTown 区县信息
     * @return 结果
     */
    @Override
    public int insertAddressTown(AddressTown addressTown)
    {
        return addressTownMapper.insertAddressTown(addressTown);
    }

    /**
     * 修改区县信息
     * 
     * @param addressTown 区县信息
     * @return 结果
     */
    @Override
    public int updateAddressTown(AddressTown addressTown)
    {
        return addressTownMapper.updateAddressTown(addressTown);
    }

    /**
     * 批量删除区县信息
     * 
     * @param ids 需要删除的区县信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressTownByIds(Long[] ids)
    {
        return addressTownMapper.deleteAddressTownByIds(ids);
    }

    /**
     * 删除区县信息信息
     * 
     * @param id 区县信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressTownById(Long id)
    {
        return addressTownMapper.deleteAddressTownById(id);
    }

    @Override
    public List<AddressTown> findAddressTownByCityCode(String cityCode) {
        return addressTownMapper.findAddressTownByCityCode(cityCode);
    }

    @Override
    public String selectTownNameByTownCode(String TownCode) {
        return addressTownMapper.selectTownNameByTownCode(TownCode);
    }
}
