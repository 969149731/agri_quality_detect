package com.ruoyi.address.controller;

import com.ruoyi.address.domain.AddressCity;
import com.ruoyi.address.domain.AddressProvince;
import com.ruoyi.address.domain.AddressTown;
import com.ruoyi.address.service.IAddressCityService;
import com.ruoyi.address.service.impl.AddressCityServiceImpl;
import com.ruoyi.address.service.impl.AddressProvinceServiceImpl;
import com.ruoyi.address.service.impl.AddressTownServiceImpl;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 城市信息Controller
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@RestController
@RequestMapping("addressInf")
public class AddressController extends BaseController
{
    @Autowired
    private IAddressCityService addressCityService;

    @Autowired
    private AddressProvinceServiceImpl provinceService;
    @Autowired
    private AddressCityServiceImpl cityService;
    @Autowired
    private AddressTownServiceImpl townService;


    /**
     * 查询所有省份信息
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('address:city:list')")
    @GetMapping("address")
    public List<AddressProvince> findAll(){

        List<AddressProvince> addressProvinces = provinceService.selectAllAddressProvinceList();
        return addressProvinces;
    }

    /**
     * 根据省份编号查询市级信息
     * @param provinceCode
     * @return
     */
    @GetMapping("addressCity/{provinceCode}")
    public List<AddressCity> findByprovinceCode(@PathVariable("provinceCode") String provinceCode){
        return  cityService.findAddressCityByProvinceCode(provinceCode);
    }

    /**
     * 根据市级编号查询区域信息
     * @param cityCode
     * @return
     */
    @GetMapping("addressTown/{cityCode}")
    public List<AddressTown> findBycityCode(@PathVariable("cityCode") String cityCode){
        return townService.findAddressTownByCityCode(cityCode);
    }





//    /**
//     * 查询城市信息列表
//     */
//    @PreAuthorize("@ss.hasPermi('address:city:list')")
//    @GetMapping("/1111list")
//    public TableDataInfo list(AddressCity addressCity)
//    {
//        startPage();
//        List<AddressCity> list = addressCityService.selectAddressCityList(addressCity);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 获取城市信息详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('address:city:query')")
//    @GetMapping(value = "/1111{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return success(addressCityService.selectAddressCityById(id));
//    }


}
