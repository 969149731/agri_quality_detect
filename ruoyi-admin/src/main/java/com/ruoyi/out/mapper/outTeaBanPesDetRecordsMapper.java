package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outTeaBanPesDetRecords;
import org.apache.ibatis.annotations.Param;

/**
 * 茶叶禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public interface outTeaBanPesDetRecordsMapper 
{
    /**
     * 查询茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 茶叶禁用农药检出及超标情况
     */
    public outTeaBanPesDetRecords selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId);

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 茶叶禁用农药检出及超标情况集合
     */
    public List<outReturnType> selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 新增茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 修改茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 删除茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId);

    /**
     * 批量删除茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(Long[] teaBanPesDetRecordsIds);


    public List<String> getTeaBanPesticideList();

    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

    public List<outFruVegSelectType> getDetResultList(@Param("params") Map<String, Object> params);
}