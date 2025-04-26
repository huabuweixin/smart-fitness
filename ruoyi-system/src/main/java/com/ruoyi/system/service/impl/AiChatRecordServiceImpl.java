package com.ruoyi.system.service.impl;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AiChatRecordMapper;
import com.ruoyi.system.domain.AiChatRecord;
import com.ruoyi.system.service.IAiChatRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * AI对话记录Service业务层处理
 *
 * @author Bruce
 * @date 2025-04-24
 */
@Service
public class AiChatRecordServiceImpl implements IAiChatRecordService
{
    @Autowired
    private AiChatRecordMapper aiChatRecordMapper;

    /**
     * 查询AI对话记录
     *
     * @param id AI对话记录主键
     * @return AI对话记录
     */
    @Override
    public AiChatRecord selectAiChatRecordById(Long id)
    {
        return aiChatRecordMapper.selectAiChatRecordById(id);
    }

    /**
     * 查询AI对话记录列表
     *
     * @param aiChatRecord AI对话记录
     * @return AI对话记录
     */
    @Override
    public List<AiChatRecord> selectAiChatRecordList(AiChatRecord aiChatRecord)
    {
        return aiChatRecordMapper.selectAiChatRecordList(aiChatRecord);
    }

    /**
     * 新增AI对话记录
     *
     * @param aiChatRecord AI对话记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAiChatRecord(AiChatRecord aiChatRecord)
    {
        aiChatRecord.setCreateTime(DateUtils.getNowDate());
        return aiChatRecordMapper.insertAiChatRecord(aiChatRecord);
    }

    /**
     * 修改AI对话记录
     *
     * @param aiChatRecord AI对话记录
     * @return 结果
     */
    @Override
    public int updateAiChatRecord(AiChatRecord aiChatRecord)
    {
        return aiChatRecordMapper.updateAiChatRecord(aiChatRecord);
    }

    /**
     * 批量删除AI对话记录
     *
     * @param ids 需要删除的AI对话记录主键
     * @return 结果
     */
    @Override
    public int deleteAiChatRecordByIds(Long[] ids)
    {
        return aiChatRecordMapper.deleteAiChatRecordByIds(ids);
    }

    /**
     * 删除AI对话记录信息
     *
     * @param id AI对话记录主键
     * @return 结果
     */
    @Override
    public int deleteAiChatRecordById(Long id)
    {
        return aiChatRecordMapper.deleteAiChatRecordById(id);
    }
}