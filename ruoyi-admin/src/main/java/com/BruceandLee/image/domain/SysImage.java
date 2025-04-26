package com.BruceandLee.image.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class SysImage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 图片ID */
    private Long imageId;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件类型（如image/jpeg） */
    @Excel(name = "文件类型", readConverterExp = "如image/jpeg")
    private String fileType;

    /** 文件存储路径（新增字段） */
    private String filePath;

    /** 图片二进制数据（可选，如果直接存文件则不需要） */
    private byte[] data;

    // Getter 和 Setter
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("imageId", imageId)
                .append("fileName", fileName)
                .append("fileType", fileType)
                .append("filePath", filePath)
                .append("data", data != null ? "byte[" + data.length + "]" : "null")
                .toString();
    }
}
