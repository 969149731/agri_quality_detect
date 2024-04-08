package com.ruoyi.out.mapper;

import java.util.List;
import com.ruoyi.out.domain.outTeaNoBanPesDetRecords;

/**
 * 茶叶上非禁止使用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public interface outTeaNoBanPesDetRecordsMapper 
{
    /**
     * 查询茶叶上非禁止使用农药检出及超标情况
     * 
     * @param teaNoBanPesDetRecordsId 茶叶上非禁止使用农药检出及超标情况主键
     * @return 茶叶上非禁止使用农药检出及超标情况
     */
    public outTeaNoBanPesDetRecords selectoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(Long teaNoBanPesDetRecordsId);

    /**
     * 查询茶叶上非禁止使用农药检出及超标情况列表
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 茶叶上非禁止使用农药检出及超标情况集合
     */
    public List<outTeaNoBanPesDetRecords> selectoutTeaNoBanPesDetRecordsList(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords);

    /**
     * 新增茶叶上非禁止使用农药检出及超标情况
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int insertoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords);

    /**
     * 修改茶叶上非禁止使用农药检出及超标情况
     * 
     * @param outTeaNoBanPesDetRecords 茶叶上非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int updateoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords);

    /**
     * 删除茶叶上非禁止使用农药检出及超标情况
     * 
     * @param teaNoBanPesDetRecordsId 茶叶上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(Long teaNoBanPesDetRecordsId);

    /**
     * 批量删除茶叶上非禁止使用农药检出及超标情况
     * 
     * @param teaNoBanPesDetRecordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsIds(Long[] teaNoBanPesDetRecordsIds);
}
