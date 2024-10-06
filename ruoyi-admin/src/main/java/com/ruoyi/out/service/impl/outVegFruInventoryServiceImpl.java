package com.ruoyi.out.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.myUtils.AgriUtils;
import com.ruoyi.out.domain.VegFruStatistic;
import com.ruoyi.out.domain.vo.OutDetailTypeInventoryVo;
import com.ruoyi.out.domain.vo.OutVegFruNameInventoryVo;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegFruInventoryMapper;
import com.ruoyi.out.domain.outVegFruInventory;
import com.ruoyi.out.service.IoutVegFruInventoryService;

/**
 * 蔬菜水果种类及数量Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegFruInventoryServiceImpl implements IoutVegFruInventoryService {
    @Autowired
    private outVegFruInventoryMapper outVegFruInventoryMapper;

    /**
     * 查询蔬菜水果种类及数量
     *
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 蔬菜水果种类及数量
     */
    @Override
    public outVegFruInventory selectoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId) {
        return outVegFruInventoryMapper.selectoutVegFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    /**
     * 查询蔬菜水果种类及数量列表
     *
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 蔬菜水果种类及数量
     */
    @Override
    public List<outVegFruInventory> selectoutVegFruInventoryList(outVegFruInventory outVegFruInventory) {
        return outVegFruInventoryMapper.selectoutVegFruInventoryList(outVegFruInventory);
    }

    /**
     * 新增蔬菜水果种类及数量
     *
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    @Override
    public int insertoutVegFruInventory(outVegFruInventory outVegFruInventory) {
        return outVegFruInventoryMapper.insertoutVegFruInventory(outVegFruInventory);
    }

    /**
     * 修改蔬菜水果种类及数量
     *
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    @Override
    public int updateoutVegFruInventory(outVegFruInventory outVegFruInventory) {
        return outVegFruInventoryMapper.updateoutVegFruInventory(outVegFruInventory);
    }

    /**
     * 批量删除蔬菜水果种类及数量
     *
     * @param vegFruInventoryIds 需要删除的蔬菜水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds) {
        return outVegFruInventoryMapper.deleteoutVegFruInventoryByVegFruInventoryIds(vegFruInventoryIds);
    }

    /**
     * 删除蔬菜水果种类及数量信息
     *
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId) {
        return outVegFruInventoryMapper.deleteoutVegFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    @Override
    public List<VegFruStatistic> selectVegFruStatistic(agriCitySampleTestDetails agriCitySampleTestDetails) {
        return outVegFruInventoryMapper.selectVegFruStatistic(agriCitySampleTestDetails);
    }


//    2024年8月6日  下面注释掉的3个方法给重新封装成一个selectSampleDetailTypeInventory方法了
//    @Override
//    public List<OutDetailTypeInventoryVo> selectVegDetailTypeInventory(agriCitySampleTestDetails agriCitySampleTestDetails,String vegFruType) {
//
//        String otherType = "蔬菜其它类";
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoList = outVegFruInventoryMapper.selectVegDetailTypeInventory(agriCitySampleTestDetails,vegFruType);
//        //这个时候获取到的List中蔬菜其它类不是在最后，需要把它调整到最后。
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoListUse = new ArrayList<>();
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (!outDetailTypeInventoryVo.getDetailType().equals(otherType)) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        //最后加上detailTypeInventoryVoList中蔬菜其它类，这个时候的detailTypeInventoryVoListUse就是蔬菜其它类在最后的了。
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (outDetailTypeInventoryVo.getDetailType().equals(otherType)) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        int seqNo=1;
//        //遍历detailTypeInventoryVoListUse
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoListUse) {
//            outDetailTypeInventoryVo.setSeqNo(seqNo++);
//            String detailType = outDetailTypeInventoryVo.getDetailType();
//            List<OutVegFruNameInventoryVo> vegFruNameInventory = outVegFruInventoryMapper.selectVegFruNameInventoryByDetailType(detailType,"蔬菜");
//            outDetailTypeInventoryVo.setDetailList(vegFruNameInventory);
//
//
//        }
//        return detailTypeInventoryVoListUse;
//    }
//
//    @Override
//    public List<OutDetailTypeInventoryVo> selectFruDetailTypeInventory(agriCitySampleTestDetails agriCitySampleTestDetails, String vegFruType) {
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoList = outVegFruInventoryMapper.selectFruDetailTypeInventory(agriCitySampleTestDetails,vegFruType);
//        //这个时候获取到的List中其它类不是在最后，需要把它调整到最后。
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoListUse = new ArrayList<>();
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (!outDetailTypeInventoryVo.getDetailType().equals("水果其它类")) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        //最后加上detailTypeInventoryVoList中其它类，这个时候的detailTypeInventoryVoListUse就是其它类在最后的了。
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (outDetailTypeInventoryVo.getDetailType().equals("水果其它类")) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        int seqNo=1;
//
//        //遍历detailTypeInventoryVoListUse
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoListUse) {
//            outDetailTypeInventoryVo.setSeqNo(seqNo++);
//            String detailType = outDetailTypeInventoryVo.getDetailType();
//            //如果是其它类，就先跳过循环，其它类放在最后加进去
//            if (detailType.equals("水果其它类")) {
//                continue;
//            }
//            List<OutVegFruNameInventoryVo> vegFruNameInventory = outVegFruInventoryMapper.selectVegFruNameInventoryByDetailType(detailType,"水果");
//            outDetailTypeInventoryVo.setDetailList(vegFruNameInventory);
//        }
//        //最后加上detailTypeInventoryVoList中其它类，这个时候的detailTypeInventoryVoListUse就是其它类在最后的了。
//        List<OutVegFruNameInventoryVo> vegFruNameInventory = outVegFruInventoryMapper.selectVegFruNameInventoryByDetailType("水果其它类","水果");//把其它类加到list里面
//        detailTypeInventoryVoListUse.get(detailTypeInventoryVoListUse.size()-1).setDetailList(vegFruNameInventory);//把其它类加到list里面
//
//        return detailTypeInventoryVoListUse;
//    }
//
//    @Override
//    public List<OutDetailTypeInventoryVo> selectTeaDetailTypeInventory(agriCitySampleTestDetails agriCitySampleTestDetails, String vegFruType) {
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoList = outVegFruInventoryMapper.selectTeaDetailTypeInventory(agriCitySampleTestDetails,vegFruType);
//        //这个时候获取到的List中其它类不是在最后，需要把它调整到最后。
//        List<OutDetailTypeInventoryVo> detailTypeInventoryVoListUse = new ArrayList<>();
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (!outDetailTypeInventoryVo.getDetailType().equals("茶叶其它类")) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        //最后加上detailTypeInventoryVoList中其它类，这个时候的detailTypeInventoryVoListUse就是其它类在最后的了。
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
//            if (outDetailTypeInventoryVo.getDetailType().equals("茶叶其它类")) {
//                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
//            }
//        }
//        int seqNo=1;
//        //遍历detailTypeInventoryVoListUse
//        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoListUse) {
//            outDetailTypeInventoryVo.setSeqNo(seqNo++);
//            String detailType = outDetailTypeInventoryVo.getDetailType();
//            List<OutVegFruNameInventoryVo> vegFruNameInventory = outVegFruInventoryMapper.selectVegFruNameInventoryByDetailType(detailType,"茶叶");
//            outDetailTypeInventoryVo.setDetailList(vegFruNameInventory);
//        }
//        return detailTypeInventoryVoListUse;
//    }

    @Override
    public List<OutDetailTypeInventoryVo> selectSampleDetailTypeInventory(agriCitySampleTestDetails agriCitySampleTestDetails, String vegFruType) {

        AgriUtils.permissionToDifferentiateData(agriCitySampleTestDetails);

        String otherType = null;
        List<OutDetailTypeInventoryVo> detailTypeInventoryVoList = new ArrayList<>();
        switch (vegFruType) {
            case "蔬菜":
                detailTypeInventoryVoList = outVegFruInventoryMapper.selectVegDetailTypeInventory(agriCitySampleTestDetails, vegFruType);
                break;
            case "水果":
                detailTypeInventoryVoList = outVegFruInventoryMapper.selectFruDetailTypeInventory(agriCitySampleTestDetails, vegFruType);
                break;
            case "茶叶":
                detailTypeInventoryVoList = outVegFruInventoryMapper.selectTeaDetailTypeInventory(agriCitySampleTestDetails, vegFruType);
                break;
        }
        switch (vegFruType) {
            case "蔬菜":
                otherType = "蔬菜其它类";
                break;
            case "水果":
                otherType = "水果其它类";
                break;
            case "茶叶":
                otherType = "茶叶其它类";
                break;
            // 其他 case ...
            default:
                System.out.println("vegFruType not recognized");
                break;
        }

        //这个时候获取到的List中其它类不是在最后，需要把它调整到最后。
        List<OutDetailTypeInventoryVo> detailTypeInventoryVoListUse = new ArrayList<>();
        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
            if (!outDetailTypeInventoryVo.getDetailType().equals(otherType)) {
                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
            }
        }
        //最后加上detailTypeInventoryVoList中其它类，这个时候的detailTypeInventoryVoListUse就是其它类在最后的了。
        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoList) {
            if (outDetailTypeInventoryVo.getDetailType().equals(otherType)) {
                detailTypeInventoryVoListUse.add(outDetailTypeInventoryVo);
            }
        }
        int seqNo = 1;
        //遍历detailTypeInventoryVoListUse
        for (OutDetailTypeInventoryVo outDetailTypeInventoryVo : detailTypeInventoryVoListUse) {
            outDetailTypeInventoryVo.setSeqNo(seqNo++);
            String detailType = outDetailTypeInventoryVo.getDetailType();
            List<OutVegFruNameInventoryVo> vegFruNameInventory = new ArrayList<>();
            switch (vegFruType) {
                case "蔬菜":
                    vegFruNameInventory = outVegFruInventoryMapper.selectVegNameInventoryByDetailType(agriCitySampleTestDetails,detailType, vegFruType);
                    break;
                case "水果":
                    vegFruNameInventory = outVegFruInventoryMapper.selectFruNameInventoryByDetailType(agriCitySampleTestDetails,detailType, vegFruType);
                    break;
                case "茶叶":
                    vegFruNameInventory = outVegFruInventoryMapper.selectTeaNameInventoryByDetailType(agriCitySampleTestDetails,detailType, vegFruType);
                    break;
                default:
                    System.out.println("outVegFruInventoryServiceImpl.java中的selectSampleDetailTypeInventory方法参数输入错误");
                    break;
            }
            outDetailTypeInventoryVo.setDetailList(vegFruNameInventory);
        }
        return detailTypeInventoryVoListUse;
    }


}
