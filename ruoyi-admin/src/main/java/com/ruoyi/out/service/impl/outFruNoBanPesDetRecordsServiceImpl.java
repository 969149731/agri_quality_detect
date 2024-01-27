package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruNoBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;
import com.ruoyi.out.service.IoutFruNoBanPesDetRecordsService;

/**
 * 水果非禁止使用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruNoBanPesDetRecordsServiceImpl implements IoutFruNoBanPesDetRecordsService 
{
    @Autowired
    private outFruNoBanPesDetRecordsMapper outFruNoBanPesDetRecordsMapper;

    /**
     * 查询水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 水果非禁止使用农药检出及超标情况
     */
    @Override
    public outFruNoBanPesDetRecords selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId)
    {
        return outFruNoBanPesDetRecordsMapper.selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId);
    }

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 水果非禁止使用农药检出及超标情况
     */
    @Override
    public List<outFruNoBanPesDetRecords> selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords);
    }

    /**
     * 新增水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords);
    }

    /**
     * 修改水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords);
    }

    /**
     * 批量删除水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsIds 需要删除的水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(Long[] fruNoBanPesDetRecordsIds)
    {
        return outFruNoBanPesDetRecordsMapper.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(fruNoBanPesDetRecordsIds);
    }

    /**
     * 删除水果非禁止使用农药检出及超标情况信息
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId)
    {
        return outFruNoBanPesDetRecordsMapper.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId);
    }
}
