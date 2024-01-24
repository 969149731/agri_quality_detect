package com.ruoyi.detection.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.fruitVegQualificationMapper;
import com.ruoyi.detection.domain.fruitVegQualification;
import com.ruoyi.detection.service.IfruitVegQualificationService;

import javax.validation.Validator;

/**
 * 蔬果检测Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-19
 */
@Service
public class fruitVegQualificationServiceImpl implements IfruitVegQualificationService {
    @Autowired
    private fruitVegQualificationMapper fruitVegQualificationMapper;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    protected Validator validator;

    private static final Logger log = LoggerFactory.getLogger(fruitVegQualificationServiceImpl.class);

    /**
     * 查询蔬果检测
     *
     * @param id 蔬果检测主键
     * @return 蔬果检测
     */
    @Override
    public fruitVegQualification selectfruitVegQualificationById(Long id) {
        return fruitVegQualificationMapper.selectfruitVegQualificationById(id);
    }

    /**
     * 查询蔬果检测列表
     *
     * @param fruitVegQualification 蔬果检测
     * @return 蔬果检测
     */
    @Override
    public List<fruitVegQualification> selectfruitVegQualificationList(fruitVegQualification fruitVegQualification) {
        return fruitVegQualificationMapper.selectfruitVegQualificationList(fruitVegQualification);
    }

    /**
     * 新增蔬果检测
     *
     * @param fruitVegQualification 蔬果检测
     * @return 结果
     */
    @Override
    public int insertfruitVegQualification(fruitVegQualification fruitVegQualification) {
        return fruitVegQualificationMapper.insertfruitVegQualification(fruitVegQualification);
    }

    /**
     * 修改蔬果检测
     *
     * @param fruitVegQualification 蔬果检测
     * @return 结果
     */
    @Override
    public int updatefruitVegQualification(fruitVegQualification fruitVegQualification) {
        return fruitVegQualificationMapper.updatefruitVegQualification(fruitVegQualification);
    }

    /**
     * 批量删除蔬果检测
     *
     * @param ids 需要删除的蔬果检测主键
     * @return 结果
     */
    @Override
    public int deletefruitVegQualificationByIds(Long[] ids) {
        return fruitVegQualificationMapper.deletefruitVegQualificationByIds(ids);
    }

    /**
     * 删除蔬果检测信息
     *
     * @param id 蔬果检测主键
     * @return 结果
     */
    @Override
    public int deletefruitVegQualificationById(Long id) {
        return fruitVegQualificationMapper.deletefruitVegQualificationById(id);
    }

    @Override
    public String importFruitVegQualification(List<fruitVegQualification> fruitVegQualificationList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(fruitVegQualificationList) || fruitVegQualificationList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (fruitVegQualification fruitVegQualification : fruitVegQualificationList) {
            try {
                fruitVegQualificationMapper.insertfruitVegQualification(fruitVegQualification);
                successNum++;
                successMsg.append("<br/>" +"第"+ successNum +"条"+ "数据导入成功");

            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第"+ failureNum +"条"+ "数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }
}