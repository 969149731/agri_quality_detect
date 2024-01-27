package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outVegBanPesDetRecords;
import com.ruoyi.out.service.IoutVegBanPesDetRecordsService;

/**
 * 蔬菜禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegBanPesDetRecordsServiceImpl implements IoutVegBanPesDetRecordsService 
{
    @Autowired
    private outVegBanPesDetRecordsMapper outVegBanPesDetRecordsMapper;

    /**
     * 查询蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 蔬菜禁用农药检出及超标情况
     */
    @Override
    public outVegBanPesDetRecords selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId)
    {
        return outVegBanPesDetRecordsMapper.selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(vegBanPesDetRecordsId);
    }

    /**
     * 查询蔬菜禁用农药检出及超标情况列表
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 蔬菜禁用农药检出及超标情况
     */
    @Override
    public List<outVegBanPesDetRecords> selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords);
    }

    /**
     * 新增蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.insertoutVegBanPesDetRecords(outVegBanPesDetRecords);
    }

    /**
     * 修改蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.updateoutVegBanPesDetRecords(outVegBanPesDetRecords);
    }

    /**
     * 批量删除蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsIds 需要删除的蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(Long[] vegBanPesDetRecordsIds)
    {
        return outVegBanPesDetRecordsMapper.deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(vegBanPesDetRecordsIds);
    }

    /**
     * 删除蔬菜禁用农药检出及超标情况信息
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId)
    {
        return outVegBanPesDetRecordsMapper.deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(vegBanPesDetRecordsId);
    }
}
