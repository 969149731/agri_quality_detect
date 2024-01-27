package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegNoBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outVegNoBanPesDetRecords;
import com.ruoyi.out.service.IoutVegNoBanPesDetRecordsService;

/**
 * 蔬菜上非禁止使用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegNoBanPesDetRecordsServiceImpl implements IoutVegNoBanPesDetRecordsService 
{
    @Autowired
    private outVegNoBanPesDetRecordsMapper outVegNoBanPesDetRecordsMapper;

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 蔬菜上非禁止使用农药检出及超标情况
     */
    @Override
    public outVegNoBanPesDetRecords selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId)
    {
        return outVegNoBanPesDetRecordsMapper.selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(vegNoBanPesDetRecordsId);
    }

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况列表
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 蔬菜上非禁止使用农药检出及超标情况
     */
    @Override
    public List<outVegNoBanPesDetRecords> selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords);
    }

    /**
     * 新增蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords);
    }

    /**
     * 修改蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords);
    }

    /**
     * 批量删除蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsIds 需要删除的蔬菜上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(Long[] vegNoBanPesDetRecordsIds)
    {
        return outVegNoBanPesDetRecordsMapper.deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(vegNoBanPesDetRecordsIds);
    }

    /**
     * 删除蔬菜上非禁止使用农药检出及超标情况信息
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId)
    {
        return outVegNoBanPesDetRecordsMapper.deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(vegNoBanPesDetRecordsId);
    }
}
