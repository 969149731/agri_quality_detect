package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outFruBanPesDetRecords;
import com.ruoyi.out.service.IoutFruBanPesDetRecordsService;

/**
 * 水果禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruBanPesDetRecordsServiceImpl implements IoutFruBanPesDetRecordsService 
{
    @Autowired
    private outFruBanPesDetRecordsMapper outFruBanPesDetRecordsMapper;

    /**
     * 查询水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 水果禁用农药检出及超标情况
     */
    @Override
    public outFruBanPesDetRecords selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId)
    {
        return outFruBanPesDetRecordsMapper.selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(fruBanPesDetRecordsId);
    }

    /**
     * 查询水果禁用农药检出及超标情况列表
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 水果禁用农药检出及超标情况
     */
    @Override
    public List<outFruBanPesDetRecords> selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords);
    }

    /**
     * 新增水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.insertoutFruBanPesDetRecords(outFruBanPesDetRecords);
    }

    /**
     * 修改水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.updateoutFruBanPesDetRecords(outFruBanPesDetRecords);
    }

    /**
     * 批量删除水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsIds 需要删除的水果禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(Long[] fruBanPesDetRecordsIds)
    {
        return outFruBanPesDetRecordsMapper.deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(fruBanPesDetRecordsIds);
    }

    /**
     * 删除水果禁用农药检出及超标情况信息
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId)
    {
        return outFruBanPesDetRecordsMapper.deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(fruBanPesDetRecordsId);
    }
}
