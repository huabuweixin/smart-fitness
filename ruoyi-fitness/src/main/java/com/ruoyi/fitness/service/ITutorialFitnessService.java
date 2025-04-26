package com.ruoyi.fitness.service;

import java.util.List;
import com.ruoyi.fitness.domain.TutorialFitness;

/**
 * 健身教程Service接口
 * 
 * @author muzi
 * @date 2025-04-25
 */
public interface ITutorialFitnessService 
{
    /**
     * 查询健身教程
     * 
     * @param tutorialId 健身教程主键
     * @return 健身教程
     */
    public TutorialFitness selectTutorialFitnessByTutorialId(Long tutorialId);

    /**
     * 查询健身教程列表
     * 
     * @param tutorialFitness 健身教程
     * @return 健身教程集合
     */
    public List<TutorialFitness> selectTutorialFitnessList(TutorialFitness tutorialFitness);

    /**
     * 新增健身教程
     * 
     * @param tutorialFitness 健身教程
     * @return 结果
     */
    public int insertTutorialFitness(TutorialFitness tutorialFitness);

    /**
     * 修改健身教程
     * 
     * @param tutorialFitness 健身教程
     * @return 结果
     */
    public int updateTutorialFitness(TutorialFitness tutorialFitness);

    /**
     * 批量删除健身教程
     * 
     * @param tutorialIds 需要删除的健身教程主键集合
     * @return 结果
     */
    public int deleteTutorialFitnessByTutorialIds(Long[] tutorialIds);

    /**
     * 删除健身教程信息
     * 
     * @param tutorialId 健身教程主键
     * @return 结果
     */
    public int deleteTutorialFitnessByTutorialId(Long tutorialId);
}
