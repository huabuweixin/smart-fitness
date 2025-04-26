package com.BruceandLee.image.service;

import java.util.List;
import com.BruceandLee.image.domain.SysImage;
import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
import io.lettuce.core.dynamic.annotation.Param;


/**
 * 图片存储Service接口
 *
 * @author Bruce
 * @date 2025-03-22
 */
public interface ISysImageService
{
    /**
     * 查询图片存储
     *
     * @param imageId 图片存储主键
     * @return 图片存储
     */
    public SysImage selectSysImageByImageId(Long imageId);

    /**
     * 查询图片存储列表
     *
     * @param sysImage 图片存储
     * @return 图片存储集合
     */
    public List<SysImage> selectSysImageList(SysImage sysImage);

    /**
     * 新增图片存储
     *
     * @param sysImage 图片存储
     * @return 结果
     */
    public  int insertSysImage(SysImage sysImage);

    /**
     * 修改图片存储
     *
     * @param sysImage 图片存储
     * @return 结果
     */
    public int updateSysImage(SysImage sysImage);

    /**
     * 批量删除图片存储
     *
     * @param imageIds 需要删除的图片存储主键集合
     * @return 结果
     */
    public int deleteSysImageByImageIds(Long[] imageIds);
    /**
     * 删除图片存储信息
     *
     * @param imageId 图片存储主键
     * @return 结果
     */
    public int deleteSysImageByImageId(Long imageId);
}
