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
import com.ruoyi.fitness.domain.MediaImage;
import com.ruoyi.fitness.service.IMediaImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图片素材Controller
 *
 * @author muzi
 * @date 2025-05-02
 */
@RestController
@RequestMapping("/fitness/Mimage")
public class MediaImageController extends BaseController
{
    @Autowired
    private IMediaImageService mediaImageService;

    /**
     * 查询图片素材列表
     */
    //@PreAuthorize("@ss.hasPermi('fitness:Mimage:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediaImage mediaImage)
    {
        startPage();
        List<MediaImage> list = mediaImageService.selectMediaImageList(mediaImage);
        return getDataTable(list);
    }

    /**
     * 导出图片素材列表
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mimage:export')")
    @Log(title = "图片素材", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediaImage mediaImage)
    {
        List<MediaImage> list = mediaImageService.selectMediaImageList(mediaImage);
        ExcelUtil<MediaImage> util = new ExcelUtil<MediaImage>(MediaImage.class);
        util.exportExcel(response, list, "图片素材数据");
    }

    /**
     * 获取图片素材详细信息
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mimage:query')")
    @GetMapping(value = "/{imageId}")
    public AjaxResult getInfo(@PathVariable("imageId") Long imageId)
    {
        return success(mediaImageService.selectMediaImageByImageId(imageId));
    }

    /**
     * 新增图片素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mimage:add')")
    @Log(title = "图片素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediaImage mediaImage)
    {
        return toAjax(mediaImageService.insertMediaImage(mediaImage));
    }

    /**
     * 修改图片素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mimage:edit')")
    @Log(title = "图片素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediaImage mediaImage)
    {
        return toAjax(mediaImageService.updateMediaImage(mediaImage));
    }

    /**
     * 删除图片素材
     */
    @PreAuthorize("@ss.hasPermi('fitness:Mimage:remove')")
    @Log(title = "图片素材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable Long[] imageIds)
    {
        return toAjax(mediaImageService.deleteMediaImageByImageIds(imageIds));
    }

}
