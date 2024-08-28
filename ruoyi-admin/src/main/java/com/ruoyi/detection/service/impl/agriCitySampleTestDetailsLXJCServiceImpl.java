package com.ruoyi.detection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsLXJCMapper;
import com.ruoyi.detection.domain.agriCitySampleTestDetailsLXJC;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsLXJCService;

/**
 * 例行检测详细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-08-27
 */
@Service
public class agriCitySampleTestDetailsLXJCServiceImpl implements IagriCitySampleTestDetailsLXJCService 
{
    @Autowired
    private agriCitySampleTestDetailsLXJCMapper agriCitySampleTestDetailsLXJCMapper;

    /**
     * 查询例行检测详细
     * 
     * @param citySampleTestDetailsId 例行检测详细主键
     * @return 例行检测详细
     */
    @Override
    public agriCitySampleTestDetailsLXJC selectagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        return agriCitySampleTestDetailsLXJCMapper.selectagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 查询例行检测详细列表
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 例行检测详细
     */
    @Override
    public List<agriCitySampleTestDetailsLXJC> selectagriCitySampleTestDetailsLXJCList(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC)
    {
        return agriCitySampleTestDetailsLXJCMapper.selectagriCitySampleTestDetailsLXJCList(agriCitySampleTestDetailsLXJC);
    }

    /**
     * 新增例行检测详细
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 结果
     */
    @Transactional
    @Override
    public int insertagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC)
    {
        int rows = agriCitySampleTestDetailsLXJCMapper.insertagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC);
        insertagriPesticideDetResult(agriCitySampleTestDetailsLXJC);
        return rows;
    }

    /**
     * 修改例行检测详细
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC)
    {
        agriCitySampleTestDetailsLXJCMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(agriCitySampleTestDetailsLXJC.getCitySampleTestDetailsId());
        insertagriPesticideDetResult(agriCitySampleTestDetailsLXJC);
        return agriCitySampleTestDetailsLXJCMapper.updateagriCitySampleTestDetailsLXJC(agriCitySampleTestDetailsLXJC);
    }

    /**
     * 批量删除例行检测详细
     * 
     * @param citySampleTestDetailsIds 需要删除的例行检测详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds)
    {
        agriCitySampleTestDetailsLXJCMapper.deleteagriPesticideDetResultByCitySampleTestDetailsIds(citySampleTestDetailsIds);
        return agriCitySampleTestDetailsLXJCMapper.deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsIds(citySampleTestDetailsIds);
    }

    /**
     * 删除例行检测详细信息
     * 
     * @param citySampleTestDetailsId 例行检测详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        agriCitySampleTestDetailsLXJCMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(citySampleTestDetailsId);
        return agriCitySampleTestDetailsLXJCMapper.deleteagriCitySampleTestDetailsLXJCByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 新增农药检测结果信息
     * 
     * @param agriCitySampleTestDetailsLXJC 例行检测详细对象
     */
    public void insertagriPesticideDetResult(agriCitySampleTestDetailsLXJC agriCitySampleTestDetailsLXJC)
    {
        List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetailsLXJC.getagriPesticideDetResultList();
        Long citySampleTestDetailsId = agriCitySampleTestDetailsLXJC.getCitySampleTestDetailsId();
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
                agriCitySampleTestDetailsLXJCMapper.batchagriPesticideDetResult(list);
            }
        }
    }
}
