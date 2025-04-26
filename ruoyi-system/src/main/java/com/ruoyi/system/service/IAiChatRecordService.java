package com.ruoyi.system.service;
import java.util.List;
import com.ruoyi.system.domain.AiChatRecord;

/**
 * AI对话记录Service接口
 *
 * @author Bruce
 * @date 2025-04-24
 */
public interface IAiChatRecordService
{
    /**
     * 查询AI对话记录
     *
     * @param id AI对话记录主键
     * @return AI对话记录
     */
    public AiChatRecord selectAiChatRecordById(Long id);

    /**
     * 查询AI对话记录列表
     *
     * @param aiChatRecord AI对话记录
     * @return AI对话记录集合
     */
    public List<AiChatRecord> selectAiChatRecordList(AiChatRecord aiChatRecord);

    /**
     * 新增AI对话记录
     *
     * @param aiChatRecord AI对话记录
     * @return 结果
     */
    public int insertAiChatRecord(AiChatRecord aiChatRecord);

    /**
     * 修改AI对话记录
     *
     * @param aiChatRecord AI对话记录
     * @return 结果
     */
    public int updateAiChatRecord(AiChatRecord aiChatRecord);

    /**
     * 批量删除AI对话记录
     *
     * @param ids 需要删除的AI对话记录主键集合
     * @return 结果
     */
    public int deleteAiChatRecordByIds(Long[] ids);

    /**
     * 删除AI对话记录信息
     *
     * @param id AI对话记录主键
     * @return 结果
     */
    public int deleteAiChatRecordById(Long id);
}