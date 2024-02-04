package com.ruoyi.out.mapper;

import java.util.List;

import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outVegBanPesDetRecords;

/**
 * 蔬菜禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outVegBanPesDetRecordsMapper 
{
    /**
     * 查询蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 蔬菜禁用农药检出及超标情况
     */
    public outVegBanPesDetRecords selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId);

    /**
     * 查询蔬菜禁用农药检出及超标情况列表
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 蔬菜禁用农药检出及超标情况集合
     */
    public List<outVegBanPesDetRecords> selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 新增蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 修改蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 删除蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId);

    /**
     * 批量删除蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(Long[] vegBanPesDetRecordsIds);

    public List<String> getVegBanPesticideList();
    public List<outFruVegSelectType> getFruVegDetResultList();
    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticidName, String vegFruName);
}
