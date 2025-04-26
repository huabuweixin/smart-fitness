package com.BruceandLee.image.mapper;

import java.util.List;
import com.BruceandLee.image.domain.SysImage;
import org.apache.ibatis.annotations.Mapper;
/**
 * 图片存储Mapper接口
 *
 * @author Bruce
 * @date 2025-03-22
 */
@Mapper
public interface SysImageMapper
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
    public int insertSysImage(SysImage sysImage);

    /**
     * 修改图片存储
     *
     * @param sysImage 图片存储
     * @return 结果
     */
    public int updateSysImage(SysImage sysImage);

    /**
     * 删除图片存储
     *
     * @param imageId 图片存储主键
     * @return 结果
     */
    public int deleteSysImageByImageId(Long imageId);

    /**
     * 批量删除图片存储
     *
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysImageByImageIds(Long[] imageIds);
}
