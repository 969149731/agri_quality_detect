package com.ruoyi.address.mapper;

import java.util.List;
import com.ruoyi.address.domain.AddressCity;

/**
 * 城市信息Mapper接口
 * 
 * @author chenjie
 * @date 2024-04-10
 */
public interface AddressCityMapper 
{
    /**
     * 查询城市信息
     * 
     * @param id 城市信息主键
     * @return 城市信息
     */
    public AddressCity selectAddressCityById(Long id);

    /**
     * 查询城市信息列表
     * 
     * @param addressCity 城市信息
     * @return 城市信息集合
     */
    public List<AddressCity> selectAddressCityList(AddressCity addressCity);

    /**
     * 新增城市信息
     * 
     * @param addressCity 城市信息
     * @return 结果
     */
    public int insertAddressCity(AddressCity addressCity);

    /**
     * 修改城市信息
     * 
     * @param addressCity 城市信息
     * @return 结果
     */
    public int updateAddressCity(AddressCity addressCity);

    /**
     * 删除城市信息
     * 
     * @param id 城市信息主键
     * @return 结果
     */
    public int deleteAddressCityById(Long id);

    /**
     * 批量删除城市信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAddressCityByIds(Long[] ids);


    public List<AddressCity> findAddressCityByProvinceCode(String provinceCode);

}
