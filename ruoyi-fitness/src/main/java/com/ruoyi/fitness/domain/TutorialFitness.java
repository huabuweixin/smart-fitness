package com.ruoyi.fitness.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 健身教程对象 tutorial_management
 * 
 * @author muzi
 * @date 2025-04-25
 */
public class TutorialFitness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long tutorialId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String description;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 指定教练 */
    @Excel(name = "指定教练")
    private Long coachId;

    /** 封面 */
    @Excel(name = "封面")
    private String cover;

    /** 上架下架状态，0为下架，1为上架。默认为0 */
    @Excel(name = "上架下架状态，0为下架，1为上架。默认为0")
    private Long status;

    /** 更新者id */
    @Excel(name = "更新者id")
    private Long updateId;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewNumber;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 收费功能预留字段 */
    @Excel(name = "收费功能预留字段")
    private Long costId;

    /** 收藏量 */
    @Excel(name = "收藏量")
    private Long collectionNumber;

    /** 分类功能预留字段 */
    @Excel(name = "分类功能预留字段")
    private Long categoryId;

    public void setTutorialId(Long tutorialId) 
    {
        this.tutorialId = tutorialId;
    }

    public Long getTutorialId() 
    {
        return tutorialId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setCoachId(Long coachId) 
    {
        this.coachId = coachId;
    }

    public Long getCoachId() 
    {
        return coachId;
    }

    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setUpdateId(Long updateId) 
    {
        this.updateId = updateId;
    }

    public Long getUpdateId() 
    {
        return updateId;
    }

    public void setViewNumber(Long viewNumber) 
    {
        this.viewNumber = viewNumber;
    }

    public Long getViewNumber() 
    {
        return viewNumber;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setCostId(Long costId) 
    {
        this.costId = costId;
    }

    public Long getCostId() 
    {
        return costId;
    }

    public void setCollectionNumber(Long collectionNumber) 
    {
        this.collectionNumber = collectionNumber;
    }

    public Long getCollectionNumber() 
    {
        return collectionNumber;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tutorialId", getTutorialId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("content", getContent())
            .append("coachId", getCoachId())
            .append("createTime", getCreateTime())
            .append("cover", getCover())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("viewNumber", getViewNumber())
            .append("price", getPrice())
            .append("costId", getCostId())
            .append("collectionNumber", getCollectionNumber())
            .append("categoryId", getCategoryId())
            .toString();
    }
}
