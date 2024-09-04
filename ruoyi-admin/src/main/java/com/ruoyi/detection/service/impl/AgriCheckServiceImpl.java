package com.ruoyi.detection.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.domain.AgriCheck;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.mapper.AgriCheckMapper;
import com.ruoyi.detection.service.IAgriCheckService;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-04
 */
@Service
public class AgriCheckServiceImpl implements IAgriCheckService {
    @Autowired
    private AgriCheckMapper argiCheckMapper;

    @Autowired
    private IagriCitySampleTestDetailsService agriCitySampleTestDetailsService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AgriCheck selectAgriCheckById(Long id) {
        return argiCheckMapper.selectAgriCheckById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param argiCheck 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AgriCheck> selectAgriCheckList(AgriCheck argiCheck) {
        return argiCheckMapper.selectAgriCheckList(argiCheck);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param argiCheck 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAgriCheck(AgriCheck argiCheck) {
        return argiCheckMapper.insertAgriCheck(argiCheck);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param argiCheck 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAgriCheck(AgriCheck argiCheck) {
        return argiCheckMapper.updateAgriCheck(argiCheck);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAgriCheckByIds(Long[] ids) {
        return argiCheckMapper.deleteAgriCheckByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAgriCheckById(Long id) {
        return argiCheckMapper.deleteAgriCheckById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult insertCheck(Long citySampleTestDetailsId, String name) {
        agriCitySampleTestDetails details = agriCitySampleTestDetailsService.findById(citySampleTestDetailsId);

        LoginUser loginUser = SecurityUtils.getLoginUser();

        AgriCheck agriCheck = new AgriCheck();
        agriCheck.setId(loginUser.getUserId());
        agriCheck.setUserName(loginUser.getUsername());
        agriCheck.setFlag(0);
        agriCheck.setContent(loginUser.getUsername() + "申请修改" + name + "中样品编号为" + details.getSampleCode() + "的检测记录");
        agriCheck.setCitySampleTestDetailsId(citySampleTestDetailsId);
        int flag = argiCheckMapper.insertAgriCheck(agriCheck);
        if (flag > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult updateCheck(Long id) {
        AgriCheck agriCheck = argiCheckMapper.selectAgriCheckById(id);
        agriCheck.setFlag(1);
        int flag1 = argiCheckMapper.updateAgriCheck(agriCheck);

        int flag2 = agriCitySampleTestDetailsService.updateagriCitySampleTestDetailsById(agriCheck.getCitySampleTestDetailsId());
        if (flag1 > 0 && flag2 > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
}
