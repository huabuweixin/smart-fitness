package com.ruoyi.web.controller.Imf;
import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysUserRoleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和角色关联Controller
 *
 * @author Bruce
 * @date 2025-05-31
 */
@Anonymous
@RestController
@RequestMapping("/system/userRole")
public class SysUserRoleController extends BaseController
{
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 查询用户和角色关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')or @ss.hasPermi('imf:stu')or @ss.hasPermi('imf:coa')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserRole sysUserRole)
    {
        startPage();
        List<SysUserRole> list = sysUserRoleService.selectSysUserRoleList(sysUserRole);
        return getDataTable(list);
    }
    /**
     * 获取用户和角色关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')or @ss.hasPermi('imf:stu')or @ss.hasPermi('imf:coa')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserRoleService.selectSysUserRoleByUserId(userId));
    }


}