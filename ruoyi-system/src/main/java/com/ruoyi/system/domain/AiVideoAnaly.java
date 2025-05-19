package com.ruoyi.system.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI分析对象 ai_video_analy
 *
 * @author Bruce
 * @date 2025-05-16
 */
public class AiVideoAnaly extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String originalPath;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String processedPath;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String mode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String analysisResult;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setOriginalPath(String originalPath)
    {
        this.originalPath = originalPath;
    }

    public String getOriginalPath()
    {
        return originalPath;
    }

    public void setProcessedPath(String processedPath)
    {
        this.processedPath = processedPath;
    }

    public String getProcessedPath()
    {
        return processedPath;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public String getMode()
    {
        return mode;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setAnalysisResult(String analysisResult)
    {
        this.analysisResult = analysisResult;
    }

    public String getAnalysisResult()
    {
        return analysisResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("originalPath", getOriginalPath())
                .append("processedPath", getProcessedPath())
                .append("userId", getUserId())
                .append("mode", getMode())
                .append("status", getStatus())
                .append("createdAt", getCreatedAt())
                .append("analysisResult", getAnalysisResult())
                .toString();
    }
}