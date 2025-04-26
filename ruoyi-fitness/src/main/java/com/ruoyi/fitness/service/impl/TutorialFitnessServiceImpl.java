package com.ruoyi.fitness.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fitness.mapper.TutorialFitnessMapper;
import com.ruoyi.fitness.domain.TutorialFitness;
import com.ruoyi.fitness.service.ITutorialFitnessService;

/**
 * 健身教程Service业务层处理
 * 
 * @author muzi
 * @date 2025-04-25
 */
@Service
public class TutorialFitnessServiceImpl implements ITutorialFitnessService 
{
    @Autowired
    private TutorialFitnessMapper tutorialFitnessMapper;

    /**
     * 查询健身教程
     * 
     * @param tutorialId 健身教程主键
     * @return 健身教程
     */
    @Override
    public TutorialFitness selectTutorialFitnessByTutorialId(Long tutorialId)
    {
        return tutorialFitnessMapper.selectTutorialFitnessByTutorialId(tutorialId);
    }

    /**
     * 查询健身教程列表
     * 
     * @param tutorialFitness 健身教程
     * @return 健身教程
     */
    @Override
    public List<TutorialFitness> selectTutorialFitnessList(TutorialFitness tutorialFitness)
    {
        return tutorialFitnessMapper.selectTutorialFitnessList(tutorialFitness);
    }

    /**
     * 新增健身教程
     * 
     * @param tutorialFitness 健身教程
     * @return 结果
     */
    @Override
    public int insertTutorialFitness(TutorialFitness tutorialFitness)
    {
        tutorialFitness.setCreateTime(DateUtils.getNowDate());
        return tutorialFitnessMapper.insertTutorialFitness(tutorialFitness);
    }

    /**
     * 修改健身教程
     * 
     * @param tutorialFitness 健身教程
     * @return 结果
     */
    @Override
    public int updateTutorialFitness(TutorialFitness tutorialFitness)
    {
        tutorialFitness.setUpdateTime(DateUtils.getNowDate());
        return tutorialFitnessMapper.updateTutorialFitness(tutorialFitness);
    }

    /**
     * 批量删除健身教程
     * 
     * @param tutorialIds 需要删除的健身教程主键
     * @return 结果
     */
    @Override
    public int deleteTutorialFitnessByTutorialIds(Long[] tutorialIds)
    {
        return tutorialFitnessMapper.deleteTutorialFitnessByTutorialIds(tutorialIds);
    }

    /**
     * 删除健身教程信息
     * 
     * @param tutorialId 健身教程主键
     * @return 结果
     */
    @Override
    public int deleteTutorialFitnessByTutorialId(Long tutorialId)
    {
        return tutorialFitnessMapper.deleteTutorialFitnessByTutorialId(tutorialId);
    }
}
