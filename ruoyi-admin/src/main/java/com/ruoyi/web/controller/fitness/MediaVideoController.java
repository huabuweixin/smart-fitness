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
import com.ruoyi.fitness.domain.MediaVedio;
import com.ruoyi.fitness.service.IMediaVedioService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频素材Controller
 *
 * @author muzi
 * @date 2025-05-02
 */
@RestController
@RequestMapping("/fitness/Mvideo")
public class MediaVideoController extends BaseController
{
    @Autowired
    private IMediaVedioService mediaVedioService;

    /**
     * 查询视频素材列表
     */
    //@PreAuthorize("@ss.hasPermi('fitness:Mvideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediaVedio mediaVedio)
    {
        startPage();
        List<MediaVedio> list = mediaVedioService.selectMediaVedioList(mediaVedio);
        return getDataTable(list);
    }

    /**
     * 导出视频素材列表
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mvideo:export')")
    @Log(title = "视频素材", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediaVedio mediaVedio)
    {
        List<MediaVedio> list = mediaVedioService.selectMediaVedioList(mediaVedio);
        ExcelUtil<MediaVedio> util = new ExcelUtil<MediaVedio>(MediaVedio.class);
        util.exportExcel(response, list, "视频素材数据");
    }

    /**
     * 获取视频素材详细信息
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mvideo:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return success(mediaVedioService.selectMediaVedioByVideoId(videoId));
    }

    /**
     * 新增视频素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mvideo:add')")
    @Log(title = "视频素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediaVedio mediaVedio)
    {
        return toAjax(mediaVedioService.insertMediaVedio(mediaVedio));
    }

    /**
     * 修改视频素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mvideo:edit')")
    @Log(title = "视频素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediaVedio mediaVedio)
    {
        return toAjax(mediaVedioService.updateMediaVedio(mediaVedio));
    }

    /**
     * 删除视频素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mvideo:remove')")
    @Log(title = "视频素材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(mediaVedioService.deleteMediaVedioByVideoIds(videoIds));
    }
}
