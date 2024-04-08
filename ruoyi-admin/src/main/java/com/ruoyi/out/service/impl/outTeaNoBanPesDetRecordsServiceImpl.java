package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outTeaNoBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outTeaNoBanPesDetRecords;
import com.ruoyi.out.service.IoutTeaNoBanPesDetRecordsService;

/**
 * 茶叶上非禁止使用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@Service
public class outTeaNoBanPesDetRecordsServiceImpl implements IoutTeaNoBanPesDetRecordsService 
{
    @Autowired
    private outTeaNoBanPesDetRecordsMapper outTeaNoBanPesDetRecordsMapper;

    /**
     * 查询茶叶上非禁止使用农药检出及超标情况
     * 
     * @param teaNoBanPesDetRecordsId 茶叶上非禁止使用农药检出及超标情况主键
     * @return 茶叶上非禁止使用农药检出及超标情况
     */
    @Override
    public outTeaNoBanPesDetRecords selectoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(Long teaNoBanPesDetRecordsId)
    {
        return outTeaNoBanPesDetRecordsMapper.selectoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(teaNoBanPesDetRecordsId);
    }

    /**
     * 查询茶叶上非禁止使用农药检出及超标情况列表
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 茶叶上非禁止使用农药检出及超标情况
     */
    @Override
    public List<outTeaNoBanPesDetRecords> selectoutTeaNoBanPesDetRecordsList(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        return outTeaNoBanPesDetRecordsMapper.selectoutTeaNoBanPesDetRecordsList(outTeaNoBanPesDetRecords);
    }

    /**
     * 新增茶叶上非禁止使用农药检出及超标情况
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        return outTeaNoBanPesDetRecordsMapper.insertoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords);
    }

    /**
     * 修改茶叶上非禁止使用农药检出及超标情况
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        return outTeaNoBanPesDetRecordsMapper.updateoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords);
    }

    /**
     * 批量删除茶叶上非禁止使用农药检出及超标情况
     * 
     * @param teaNoBanPesDetRecordsIds 需要删除的茶叶上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsIds(Long[] teaNoBanPesDetRecordsIds)
    {
        return outTeaNoBanPesDetRecordsMapper.deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsIds(teaNoBanPesDetRecordsIds);
    }

    /**
     * 删除茶叶上非禁止使用农药检出及超标情况信息
     * 
     * @param teaNoBanPesDetRecordsId 茶叶上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(Long teaNoBanPesDetRecordsId)
    {
        return outTeaNoBanPesDetRecordsMapper.deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(teaNoBanPesDetRecordsId);
    }
}
