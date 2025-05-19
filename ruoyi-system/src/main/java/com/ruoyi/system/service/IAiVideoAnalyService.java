package com.ruoyi.system.service;
import java.util.List;
import com.ruoyi.system.domain.AiVideoAnaly;

/**
 * 【请填写功能名称】Service接口
 *
 * @author Bruce
 * @date 2025-05-16
 */
public interface IAiVideoAnalyService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AiVideoAnaly selectAiVideoAnalyById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AiVideoAnaly> selectAiVideoAnalyList(AiVideoAnaly aiVideoAnaly);

    /**
     * 新增【请填写功能名称】
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 结果
     */
    public int insertAiVideoAnaly(AiVideoAnaly aiVideoAnaly);

    /**
     * 修改【请填写功能名称】
     *
     * @param aiVideoAnaly 【请填写功能名称】
     * @return 结果
     */
    public int updateAiVideoAnaly(AiVideoAnaly aiVideoAnaly);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAiVideoAnalyByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAiVideoAnalyById(Long id);
}