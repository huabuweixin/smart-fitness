package com.ruoyi.fitness.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fitness.mapper.MediaImageMapper;
import com.ruoyi.fitness.domain.MediaImage;
import com.ruoyi.fitness.service.IMediaImageService;

/**
 * 图片素材Service业务层处理
 *
 * @author muzi
 * @date 2025-05-02
 */
@Service
public class MediaImageServiceImpl implements IMediaImageService
{
    @Autowired
    private MediaImageMapper mediaImageMapper;

    /**
     * 查询图片素材
     *
     * @param imageId 图片素材主键
     * @return 图片素材
     */
    @Override
    public MediaImage selectMediaImageByImageId(Long imageId)
    {
        return mediaImageMapper.selectMediaImageByImageId(imageId);
    }

    /**
     * 查询图片素材列表
     *
     * @param mediaImage 图片素材
     * @return 图片素材
     */
    @Override
    public List<MediaImage> selectMediaImageList(MediaImage mediaImage)
    {
        return mediaImageMapper.selectMediaImageList(mediaImage);
    }

    /**
     * 新增图片素材
     *
     * @param mediaImage 图片素材
     * @return 结果
     */
    @Override
    public int insertMediaImage(MediaImage mediaImage)
    {
        return mediaImageMapper.insertMediaImage(mediaImage);
    }

    /**
     * 修改图片素材
     *
     * @param mediaImage 图片素材
     * @return 结果
     */
    @Override
    public int updateMediaImage(MediaImage mediaImage)
    {
        return mediaImageMapper.updateMediaImage(mediaImage);
    }

    /**
     * 批量删除图片素材
     *
     * @param imageIds 需要删除的图片素材主键
     * @return 结果
     */
    @Override
    public int deleteMediaImageByImageIds(Long[] imageIds)
    {
        return mediaImageMapper.deleteMediaImageByImageIds(imageIds);
    }

    /**
     * 删除图片素材信息
     *
     * @param imageId 图片素材主键
     * @return 结果
     */
    @Override
    public int deleteMediaImageByImageId(Long imageId)
    {
        return mediaImageMapper.deleteMediaImageByImageId(imageId);
    }
}
