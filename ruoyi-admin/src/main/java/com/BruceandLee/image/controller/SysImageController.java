package com.BruceandLee.image.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.BruceandLee.image.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.AjaxResult;
import com.BruceandLee.image.domain.SysImage;
import com.BruceandLee.image.service.ISysImageService;
import com.BruceandLee.image.service.FileStorageService;

@RestController
@RequestMapping("/image/image")
@CrossOrigin(origins = "*")
public class SysImageController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private ISysImageService sysImageService;
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('image:image:list')")
    public AjaxResult list(@Validated @RequestBody SysImage sysImage) {
        //startPage(); // 如果需要分页，调用分页方法（需配合PageHelper）
        List<SysImage> list = sysImageService.selectSysImageList(sysImage);
        return AjaxResult.success(list);
    }
    @PostMapping("/upload")
    @PreAuthorize("@ss.hasPermi('image:image:upload')")
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 文件存储
            String fileName = storageService.storeFile(file);

            // 数据库记录
            SysImage image = new SysImage();
            image.setFileName(file.getOriginalFilename());
            image.setFilePath(fileName);
            sysImageService.insertSysImage(image);

            return AjaxResult.success("上传成功", Map.of("path", fileName));
        } catch (Exception e) {
            return AjaxResult.error("上传失败: " + e.getMessage());
        }
    }
    @GetMapping("/download/{imageId}")
    public void downloadImage(
            @PathVariable Long imageId,
            HttpServletResponse response
    ) {
        try {
            // 获取图片信息
            SysImage image = sysImageService.selectSysImageByImageId(imageId);

            // 构建完整路径
            String fullPath = storageService.getImagePath() + image.getFilePath();
            Path filePath = Paths.get(fullPath);

            // 检查文件是否存在
            if (!Files.exists(filePath)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "文件不存在");
                return;
            }

            // 设置响应头
            String contentType = "image/" + image.getFileName().split("\\.")[1];
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + image.getFileName() + "\"");

            // 文件流传输
            try (InputStream is = Files.newInputStream(filePath)) {
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (IOException e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "下载失败: " + e.getMessage());
            } catch (IOException ex) {
                // 处理发送错误时的异常
            }
        }
    }
}
