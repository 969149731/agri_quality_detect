package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outTeaBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outTeaBanPesDetRecords;
import com.ruoyi.out.service.IoutTeaBanPesDetRecordsService;

/**
 * 茶叶禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@Service
public class outTeaBanPesDetRecordsServiceImpl implements IoutTeaBanPesDetRecordsService 
{
    @Autowired
    private outTeaBanPesDetRecordsMapper outTeaBanPesDetRecordsMapper;

    /**
     * 查询茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 茶叶禁用农药检出及超标情况
     */
    @Override
    public outTeaBanPesDetRecords selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId)
    {
        return outTeaBanPesDetRecordsMapper.selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(teaBanPesDetRecordsId);
    }

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 茶叶禁用农药检出及超标情况
     */
    @Override
    public List<outTeaBanPesDetRecords> selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return outTeaBanPesDetRecordsMapper.selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords);
    }

    /**
     * 新增茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return outTeaBanPesDetRecordsMapper.insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords);
    }

    /**
     * 修改茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return outTeaBanPesDetRecordsMapper.updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords);
    }

    /**
     * 批量删除茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsIds 需要删除的茶叶禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(Long[] teaBanPesDetRecordsIds)
    {
        return outTeaBanPesDetRecordsMapper.deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(teaBanPesDetRecordsIds);
    }

    /**
     * 删除茶叶禁用农药检出及超标情况信息
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId)
    {
        return outTeaBanPesDetRecordsMapper.deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(teaBanPesDetRecordsId);
    }
}
