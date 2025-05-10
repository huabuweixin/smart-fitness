package com.ruoyi.fitness.mapper;

import java.util.List;
import com.ruoyi.fitness.domain.MediaImage;

/**
 * 图片素材Mapper接口
 *
 * @author muzi
 * @date 2025-05-02
 */
public interface MediaImageMapper
{
    /**
     * 查询图片素材
     *
     * @param imageId 图片素材主键
     * @return 图片素材
     */
    public MediaImage selectMediaImageByImageId(Long imageId);

    /**
     * 查询图片素材列表
     *
     * @param mediaImage 图片素材
     * @return 图片素材集合
     */
    public List<MediaImage> selectMediaImageList(MediaImage mediaImage);

    /**
     * 新增图片素材
     *
     * @param mediaImage 图片素材
     * @return 结果
     */
    public int insertMediaImage(MediaImage mediaImage);

    /**
     * 修改图片素材
     *
     * @param mediaImage 图片素材
     * @return 结果
     */
    public int updateMediaImage(MediaImage mediaImage);

    /**
     * 删除图片素材
     *
     * @param imageId 图片素材主键
     * @return 结果
     */
    public int deleteMediaImageByImageId(Long imageId);

    /**
     * 批量删除图片素材
     *
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMediaImageByImageIds(Long[] imageIds);
}
