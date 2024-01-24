package com.ruoyi.detection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;

/**
 * 各市样品检测结果详细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@Service
public class agriCitySampleTestDetailsServiceImpl implements IagriCitySampleTestDetailsService 
{
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;

    /**
     * 查询各市样品检测结果详细
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 各市样品检测结果详细
     */
    @Override
    public agriCitySampleTestDetails selectagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 查询各市样品检测结果详细列表
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 各市样品检测结果详细
     */
    @Override
    public List<agriCitySampleTestDetails> selectagriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
    }

    /**
     * 新增各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    @Transactional
    @Override
    public int insertagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        int rows = agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);
        insertagriPesticideDetResult(agriCitySampleTestDetails);
        return rows;
    }

    /**
     * 修改各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(agriCitySampleTestDetails.getCitySampleTestDetailsId());
        insertagriPesticideDetResult(agriCitySampleTestDetails);
        return agriCitySampleTestDetailsMapper.updateagriCitySampleTestDetails(agriCitySampleTestDetails);
    }

    /**
     * 批量删除各市样品检测结果详细
     * 
     * @param citySampleTestDetailsIds 需要删除的各市样品检测结果详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsIds(citySampleTestDetailsIds);
        return agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(citySampleTestDetailsIds);
    }

    /**
     * 删除各市样品检测结果详细信息
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(citySampleTestDetailsId);
        return agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 新增农药检测结果信息
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细对象
     */
    public void insertagriPesticideDetResult(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetails.getAgriPesticideDetResultList();
        Long citySampleTestDetailsId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
        if (StringUtils.isNotNull(agriPesticideDetResultList))
        {
            List<agriPesticideDetResult> list = new ArrayList<agriPesticideDetResult>();
            for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResultList)
            {
                agriPesticideDetResult.setCitySampleTestDetailsId(citySampleTestDetailsId);
                list.add(agriPesticideDetResult);
            }
            if (list.size() > 0)
            {
                agriCitySampleTestDetailsMapper.batchagriPesticideDetResult(list);
            }
        }
    }
}
