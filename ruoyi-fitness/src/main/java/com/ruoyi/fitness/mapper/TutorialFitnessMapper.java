package com.ruoyi.fitness.mapper;

import java.util.List;
import com.ruoyi.fitness.domain.TutorialFitness;
import org.apache.ibatis.annotations.Mapper;

/**
 * 健身教程Mapper接口
 *
 * @author muzi
 * @date 2025-04-25
 */
@Mapper // 确保添加此注解
public interface TutorialFitnessMapper
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
     * 删除健身教程
     *
     * @param tutorialId 健身教程主键
     * @return 结果
     */
    public int deleteTutorialFitnessByTutorialId(Long tutorialId);

    /**
     * 批量删除健身教程
     *
     * @param tutorialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorialFitnessByTutorialIds(Long[] tutorialIds);
}
