package com.ruoyi.myUtils;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class AgriUtils {

    public static void permissionToDifferentiateData(agriCitySampleTestDetails agriCitySampleTestDetails){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取当前的用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();

        //获取当前登录的用户账号
        String username = authentication.getName();

        //获取当前用户所属部门
        SysDept sysDept = loginUser.getUser().getDept();
        String deptName = sysDept.getDeptName();

        //用当前用户名查询当前用户所属角色，如果是属于监测机构，则只能查看自己上传过的数据
        List<SysRole> roles = loginUser.getUser().getRoles();
        String roleName = roles.get(0).getRoleName();
        if (roleName.equals("监测机构")) {
            agriCitySampleTestDetails.setUserName(username);
        }
        //如果是属于安监部门，则只能查看本辖区内的数据
        if (roleName.equals("安监部门")) {
            //截取depName中的字符，从开头到市结尾的字符
            String substringDeptName = deptName.substring(0, deptName.indexOf("市") + 1);
//            if (agriCitySampleTestDetails.getSamplingLocationCity()!=null&& !Objects.equals(agriCitySampleTestDetails.getSamplingLocationCity(), substringDeptName)){
//                throw new ServiceException("您当前角色为：安监部门，所在部门为："+deptName+"，只允许查看："+substringDeptName+" 下的数据，没有权限查看别的地方的数据");
//            }
            agriCitySampleTestDetails.setSamplingLocationCity(substringDeptName);
        }

    }

}
