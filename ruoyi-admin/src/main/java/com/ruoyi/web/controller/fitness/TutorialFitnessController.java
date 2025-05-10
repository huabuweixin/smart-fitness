package com.ruoyi.web.controller.fitness;

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
import com.ruoyi.fitness.domain.TutorialFitness;
import com.ruoyi.fitness.service.ITutorialFitnessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 健身教程Controller
 *
 * @author muzi
 * @date 2025-04-25
 */
@RestController
@RequestMapping("/fitness/Tfitness")
public class TutorialFitnessController extends BaseController
{
    @Autowired
    private ITutorialFitnessService tutorialFitnessService;

    /**
     * 查询健身教程列表
     */
    //@PreAuthorize("@ss.hasPermi('fitness:Tfitness:list')")
    @GetMapping("/list")
    public TableDataInfo list(TutorialFitness tutorialFitness)
    {
        startPage();
        List<TutorialFitness> list = tutorialFitnessService.selectTutorialFitnessList(tutorialFitness);
        return getDataTable(list);
    }

    /**
     * 导出健身教程列表
     */
    @PreAuthorize("@ss.hasPermi('fitness:Tfitness:export')")
    @Log(title = "健身教程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TutorialFitness tutorialFitness)
    {
        List<TutorialFitness> list = tutorialFitnessService.selectTutorialFitnessList(tutorialFitness);
        ExcelUtil<TutorialFitness> util = new ExcelUtil<TutorialFitness>(TutorialFitness.class);
        util.exportExcel(response, list, "健身教程数据");
    }

    /**
     * 获取健身教程详细信息
     */
    @PreAuthorize("@ss.hasPermi('fitness:Tfitness:query')")
    @GetMapping(value = "/{tutorialId}")
    public AjaxResult getInfo(@PathVariable("tutorialId") Long tutorialId)
    {
        return success(tutorialFitnessService.selectTutorialFitnessByTutorialId(tutorialId));
    }

    /**
     * 新增健身教程
     */
    @PreAuthorize("@ss.hasPermi('fitness:Tfitness:add')")
    @Log(title = "健身教程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TutorialFitness tutorialFitness)
    {
        return toAjax(tutorialFitnessService.insertTutorialFitness(tutorialFitness));
    }

    /**
     * 修改健身教程
     */
    @PreAuthorize("@ss.hasPermi('fitness:Tfitness:edit')")
    @Log(title = "健身教程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TutorialFitness tutorialFitness)
    {
        return toAjax(tutorialFitnessService.updateTutorialFitness(tutorialFitness));
    }

    /**
     * 删除健身教程
     */
    @PreAuthorize("@ss.hasPermi('fitness:Tfitness:remove')")
    @Log(title = "健身教程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tutorialIds}")
    public AjaxResult remove(@PathVariable Long[] tutorialIds)
    {
        return toAjax(tutorialFitnessService.deleteTutorialFitnessByTutorialIds(tutorialIds));
    }
}
