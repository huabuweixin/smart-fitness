package com.ruoyi.system.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AiVideoAnalyMapper;
import com.ruoyi.system.domain.AiVideoAnaly;
import com.ruoyi.system.service.IAiVideoAnalyService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author Bruce
 * @date 2025-05-16
 */
@Service
public class AiVideoAnalyServiceImpl implements IAiVideoAnalyService
{
    @Autowired
    private AiVideoAnalyMapper aiVideoAnalyMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AiVideoAnaly selectAiVideoAnalyById(Long id)
    {
        return aiVideoAnalyMapper.selectAiVideoAnalyById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AiVideoAnaly> selectAiVideoAnalyList(AiVideoAnaly aiVideoAnaly)
    {
        return aiVideoAnalyMapper.selectAiVideoAnalyList(aiVideoAnaly);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAiVideoAnaly(AiVideoAnaly aiVideoAnaly)
    {
        return aiVideoAnalyMapper.insertAiVideoAnaly(aiVideoAnaly);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAiVideoAnaly(AiVideoAnaly aiVideoAnaly)
    {
        return aiVideoAnalyMapper.updateAiVideoAnaly(aiVideoAnaly);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoAnalyByIds(Long[] ids)
    {
        return aiVideoAnalyMapper.deleteAiVideoAnalyByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoAnalyById(Long id)
    {
        return aiVideoAnalyMapper.deleteAiVideoAnalyById(id);
    }
}