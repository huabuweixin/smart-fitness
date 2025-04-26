package com.ruoyi.web.controller.system;
import java.util.List;
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
import com.ruoyi.system.domain.TutorialManagement;
import com.ruoyi.system.service.ITutorialManagementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教程管理Controller
 *
 * @author muzi
 * @date 2025-04-21
 */
@RestController
@RequestMapping("/system/Tmanagement")
public class TutorialManagementController extends BaseController
{
    @Autowired
    private ITutorialManagementService tutorialManagementService;

    /**
     * 查询教程管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(TutorialManagement tutorialManagement)
    {
        startPage();
        List<TutorialManagement> list = tutorialManagementService.selectTutorialManagementList(tutorialManagement);
        return getDataTable(list);
    }

    /**
     * 导出教程管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:export')")
    @Log(title = "教程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TutorialManagement tutorialManagement)
    {
        List<TutorialManagement> list = tutorialManagementService.selectTutorialManagementList(tutorialManagement);
        ExcelUtil<TutorialManagement> util = new ExcelUtil<TutorialManagement>(TutorialManagement.class);
        util.exportExcel(response, list, "教程管理数据");
    }

    /**
     * 获取教程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:query')")
    @GetMapping(value = "/{tutorialId}")
    public AjaxResult getInfo(@PathVariable("tutorialId") Long tutorialId)
    {
        return success(tutorialManagementService.selectTutorialManagementByTutorialId(tutorialId));
    }

    /**
     * 新增教程管理
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:add')")
    @Log(title = "教程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TutorialManagement tutorialManagement)
    {
        return toAjax(tutorialManagementService.insertTutorialManagement(tutorialManagement));
    }

    /**
     * 修改教程管理
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:edit')")
    @Log(title = "教程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TutorialManagement tutorialManagement)
    {
        return toAjax(tutorialManagementService.updateTutorialManagement(tutorialManagement));
    }

    /**
     * 删除教程管理
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:remove')")
    @Log(title = "教程管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tutorialIds}")
    public AjaxResult remove(@PathVariable Long[] tutorialIds)
    {
        return toAjax(tutorialManagementService.deleteTutorialManagementByTutorialIds(tutorialIds));
    }
    /**
     * 批量上架教程
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:shelf')")
    @Log(title = "教程管理", businessType = BusinessType.UPDATE)
    @PostMapping("/shelf")
    public AjaxResult shelf(@RequestBody Long[] tutorialIds) {
        return toAjax(tutorialManagementService.updateStatusBatch(tutorialIds, 1));
    }

    /**
     * 批量下架教程
     */
    @PreAuthorize("@ss.hasPermi('system:Tmanagement:unshelf')")
    @Log(title = "教程管理", businessType = BusinessType.UPDATE)
    @PostMapping("/unshelf")
    public AjaxResult unshelf(@RequestBody Long[] tutorialIds) {
        return toAjax(tutorialManagementService.updateStatusBatch(tutorialIds, 2));
    }
}

