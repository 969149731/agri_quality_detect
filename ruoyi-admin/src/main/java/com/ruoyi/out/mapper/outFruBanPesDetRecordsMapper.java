package com.ruoyi.out.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.out.domain.outFruBanPesDetRecords;

import com.ruoyi.detection.domain.agriPesticideDetResult;//农药详细表
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 水果禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outFruBanPesDetRecordsMapper 
{
    /**
     * 查询水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 水果禁用农药检出及超标情况
     */
    public outFruBanPesDetRecords selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId);

    /**
     * 查询水果禁用农药检出及超标情况列表
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 水果禁用农药检出及超标情况集合
     */
    public List<outFruBanPesDetRecords> selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 新增水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 修改水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 删除水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId);

    /**
     * 批量删除水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(Long[] fruBanPesDetRecordsIds);

    public List<outReturnType> selectoutFruBanPesDetRecordsList2(outReturnType outReturnTypeRecords);



    //获取禁用农药列表
    public List<String> getFruBanPesticideList();

    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

    public List<outFruVegSelectType> getFruVegDetResultList(@Param("params")Map<String, Object> params, Date startDate, Date endDate);
}
