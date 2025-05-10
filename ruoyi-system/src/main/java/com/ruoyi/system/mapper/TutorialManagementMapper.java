package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TutorialManagement;

/**
 * 教程管理Mapper接口
 *
 * @author muzi
 * @date 2025-04-21
 */
public interface TutorialManagementMapper
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
     * 删除教程管理
     *
     * @param tutorialId 教程管理主键
     * @return 结果
     */
    public int deleteTutorialManagementByTutorialId(Long tutorialId);

    /**
     * 批量删除教程管理
     *
     * @param tutorialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorialManagementByTutorialIds(Long[] tutorialIds);
    /**
     * 批量更新教程状态
     * @param tutorialIds 教程ID数组
     * @param status 新状态值（1-上架，2-下架）
     * @return 受影响的行数
     */
    public int updateStatusBatch(Long[] tutorialIds, int status);
    /**
     * 查询教程列表（含教练信息）
     */
    List<TutorialManagement> selectTutorialWithCoach();
}
