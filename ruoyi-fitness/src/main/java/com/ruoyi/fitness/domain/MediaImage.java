package com.ruoyi.fitness.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图片素材对象 media_image
 *
 * @author muzi
 * @date 2025-05-02
 */
public class MediaImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增 */
    private Long imageId;

    /** 原始文件名 */
    @Excel(name = "原始文件名")
    private String fileName;

    /** 存储路径 */
    @Excel(name = "存储路径")
    private String storagePath;

    /** 文件大小（kb） */
    @Excel(name = "文件大小", readConverterExp = "k=b")
    private Long fileSize;

    /** 文件类型（jpg/png等） */
    @Excel(name = "文件类型", readConverterExp = "j=pg/png等")
    private String fileType;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long uploaderId;

    public void setImageId(Long imageId)
    {
        this.imageId = imageId;
    }

    public Long getImageId()
    {
        return imageId;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setStoragePath(String storagePath)
    {
        this.storagePath = storagePath;
    }

    public String getStoragePath()
    {
        return storagePath;
    }

    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setUploadTime(Date uploadTime)
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime()
    {
        return uploadTime;
    }

    public void setUploaderId(Long uploaderId)
    {
        this.uploaderId = uploaderId;
    }

    public Long getUploaderId()
    {
        return uploaderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("imageId", getImageId())
            .append("fileName", getFileName())
            .append("storagePath", getStoragePath())
            .append("fileSize", getFileSize())
            .append("fileType", getFileType())
            .append("uploadTime", getUploadTime())
            .append("uploaderId", getUploaderId())
            .toString();
    }
}
