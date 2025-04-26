package com.ruoyi.system.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI对话记录对象 ai_chat_record
 *
 * @author Bruce
 * @date 2025-04-24
 */
public class AiChatRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户ID（关联 sys_user） */
    @Excel(name = "用户ID", readConverterExp = "关=联,s=ys_user")
    private Long userId;

    /** 会话ID（用于多轮对话） */
    @Excel(name = "会话ID", readConverterExp = "用=于多轮对话")
    private String sessionId;

    /** 用户输入内容 */
    @Excel(name = "用户输入内容")
    private String userInput;

    /** AI响应内容 */
    @Excel(name = "AI响应内容")
    private String aiResponse;

    /** 使用的模型名称（如 llama3） */
    @Excel(name = "使用的模型名称", readConverterExp = "如=,l=lama3")
    private String modelName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setUserInput(String userInput)
    {
        this.userInput = userInput;
    }

    public String getUserInput()
    {
        return userInput;
    }

    public void setAiResponse(String aiResponse)
    {
        this.aiResponse = aiResponse;
    }

    public String getAiResponse()
    {
        return aiResponse;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("sessionId", getSessionId())
                .append("userInput", getUserInput())
                .append("aiResponse", getAiResponse())
                .append("createTime", getCreateTime())
                .append("modelName", getModelName())
                .toString();
    }
}