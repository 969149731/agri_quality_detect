package com.ruoyi.out.mapper;

import java.util.List;
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;

/**
 * 水果非禁止使用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outFruNoBanPesDetRecordsMapper 
{
    /**
     * 查询水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 水果非禁止使用农药检出及超标情况
     */
    public outFruNoBanPesDetRecords selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId);

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 水果非禁止使用农药检出及超标情况集合
     */
    public List<outFruNoBanPesDetRecords> selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 新增水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 修改水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 删除水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId);

    /**
     * 批量删除水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(Long[] fruNoBanPesDetRecordsIds);
}
