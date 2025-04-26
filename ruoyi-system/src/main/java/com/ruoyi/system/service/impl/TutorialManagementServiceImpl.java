package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TutorialManagementMapper;
import com.ruoyi.system.domain.TutorialManagement;
import com.ruoyi.system.service.ITutorialManagementService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教程管理Service业务层处理
 *
 * @author muzi
 * @date 2025-04-21
 */
@Service
public class TutorialManagementServiceImpl implements ITutorialManagementService {
    @Autowired
    private TutorialManagementMapper tutorialManagementMapper;

    /**
     * 查询教程管理
     *
     * @param tutorialId 教程管理主键
     * @return 教程管理
     */
    @Override
    public TutorialManagement selectTutorialManagementByTutorialId(Long tutorialId) {
        return tutorialManagementMapper.selectTutorialManagementByTutorialId(tutorialId);
    }

    /**
     * 查询教程管理列表
     *
     * @param tutorialManagement 教程管理
     * @return 教程管理
     */
    @Override
    public List<TutorialManagement> selectTutorialManagementList(TutorialManagement tutorialManagement) {
        return tutorialManagementMapper.selectTutorialManagementList(tutorialManagement);
    }

    /**
     * 新增教程管理
     *
     * @param tutorialManagement 教程管理
     * @return 结果
     */
    @Override
    public int insertTutorialManagement(TutorialManagement tutorialManagement) {
        tutorialManagement.setCreateTime(DateUtils.getNowDate());
        return tutorialManagementMapper.insertTutorialManagement(tutorialManagement);
    }

    /**
     * 修改教程管理
     *
     * @param tutorialManagement 教程管理
     * @return 结果
     */
    @Override
    public int updateTutorialManagement(TutorialManagement tutorialManagement) {
        tutorialManagement.setUpdateTime(DateUtils.getNowDate());
        return tutorialManagementMapper.updateTutorialManagement(tutorialManagement);
    }

    /**
     * 批量删除教程管理
     *
     * @param tutorialIds 需要删除的教程管理主键
     * @return 结果
     */
    @Override
    public int deleteTutorialManagementByTutorialIds(Long[] tutorialIds) {
        return tutorialManagementMapper.deleteTutorialManagementByTutorialIds(tutorialIds);
    }

    /**
     * 删除教程管理信息
     *
     * @param tutorialId 教程管理主键
     * @return 结果
     */
    @Override
    public int deleteTutorialManagementByTutorialId(Long tutorialId) {
        return tutorialManagementMapper.deleteTutorialManagementByTutorialId(tutorialId);
    }

    @Override
    @Transactional
    public int updateStatusBatch(Long[] tutorialIds, int status) {
        return tutorialManagementMapper.updateStatusBatch(tutorialIds, status);
    }
}
