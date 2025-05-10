package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TutorialManagement;

/**
 * 教程管理Service接口
 *
 * @author muzi
 * @date 2025-04-21
 */
public interface ITutorialManagementService
{
    /**
     * 查询教程管理
     *
     * @param tutorialId 教程管理主键
     * @return 教程管理
     */
    public TutorialManagement selectTutorialManagementByTutorialId(Long tutorialId);

    /**
     * 查询教程管理列表
     *
     * @param tutorialManagement 教程管理
     * @return 教程管理集合
     */
    public List<TutorialManagement> selectTutorialManagementList(TutorialManagement tutorialManagement);

    /**
     * 新增教程管理
     *
     * @param tutorialManagement 教程管理
     * @return 结果
     */
    public int insertTutorialManagement(TutorialManagement tutorialManagement);

    /**
     * 修改教程管理
     *
     * @param tutorialManagement 教程管理
     * @return 结果
     */
    public int updateTutorialManagement(TutorialManagement tutorialManagement);

    /**
     * 批量删除教程管理
     *
     * @param tutorialIds 需要删除的教程管理主键集合
     * @return 结果
     */
    public int deleteTutorialManagementByTutorialIds(Long[] tutorialIds);

    /**
     * 删除教程管理信息
     *
     * @param tutorialId 教程管理主键
     * @return 结果
     */
    public int deleteTutorialManagementByTutorialId(Long tutorialId);
    /**
     * 批量更新教程状态
     * @param tutorialIds 教程ID数组
     * @param status 新状态值（1-上架，2-下架）
     */
    int updateStatusBatch(Long[] tutorialIds, int status);
    /**
     * 获取带教练信息的教程列表
     */
    List<TutorialManagement> selectTutorialWithCoach();
}
