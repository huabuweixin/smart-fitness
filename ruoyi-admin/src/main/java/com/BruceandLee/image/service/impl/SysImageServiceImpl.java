package com.BruceandLee.image.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BruceandLee.image.mapper.SysImageMapper;
import com.BruceandLee.image.domain.SysImage;
import com.BruceandLee.image.service.ISysImageService;

/**
 * 图片存储Service业务层处理
 *
 * @author Bruce
 * @date 2025-03-22
 */
@Service
public class SysImageServiceImpl implements ISysImageService
{
    @Autowired
    private SysImageMapper sysImageMapper;

    /**
     * 查询图片存储
     *
     * @param imageId 图片存储主键
     * @return 图片存储
     */
    @Override
    public SysImage selectSysImageByImageId(Long imageId)
    {
        return sysImageMapper.selectSysImageByImageId(imageId);
    }

    /**
     * 查询图片存储列表
     *
     * @param sysImage 图片存储
     * @return 图片存储
     */
    @Override
    public List<SysImage> selectSysImageList(SysImage sysImage)
    {
        return sysImageMapper.selectSysImageList(sysImage);
    }

    /**
     * 新增图片存储
     *
     * @param sysImage 图片存储
     * @return 结果
     */
    @Override
    public int insertSysImage(SysImage sysImage)
    {
        sysImage.setCreateTime(DateUtils.getNowDate());
        return sysImageMapper.insertSysImage(sysImage);
    }

    /**
     * 修改图片存储
     *
     * @param sysImage 图片存储
     * @return 结果
     */
    @Override
    public int updateSysImage(SysImage sysImage)
    {
        return sysImageMapper.updateSysImage(sysImage);
    }

    /**
     * 批量删除图片存储
     *
     * @param imageIds 需要删除的图片存储主键
     * @return 结果
     */
    @Override
    public int deleteSysImageByImageIds(Long[] imageIds)
    {
        return sysImageMapper.deleteSysImageByImageIds(imageIds);
    }

    /**
     * 删除图片存储信息
     *
     * @param imageId 图片存储主键
     * @return 结果
     */
    @Override
    public int deleteSysImageByImageId(Long imageId)
    {
        return sysImageMapper.deleteSysImageByImageId(imageId);
    }
}
