package com.ruoyi.fitness.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fitness.mapper.MediaVedioMapper;
import com.ruoyi.fitness.domain.MediaVedio;
import com.ruoyi.fitness.service.IMediaVedioService;

/**
 * 视频素材Service业务层处理
 * 
 * @author muzi
 * @date 2025-05-02
 */
@Service
public class MediaVedioServiceImpl implements IMediaVedioService 
{
    @Autowired
    private MediaVedioMapper mediaVedioMapper;

    /**
     * 查询视频素材
     * 
     * @param videoId 视频素材主键
     * @return 视频素材
     */
    @Override
    public MediaVedio selectMediaVedioByVideoId(Long videoId)
    {
        return mediaVedioMapper.selectMediaVedioByVideoId(videoId);
    }

    /**
     * 查询视频素材列表
     * 
     * @param mediaVedio 视频素材
     * @return 视频素材
     */
    @Override
    public List<MediaVedio> selectMediaVedioList(MediaVedio mediaVedio)
    {
        return mediaVedioMapper.selectMediaVedioList(mediaVedio);
    }

    /**
     * 新增视频素材
     * 
     * @param mediaVedio 视频素材
     * @return 结果
     */
    @Override
    public int insertMediaVedio(MediaVedio mediaVedio)
    {
        return mediaVedioMapper.insertMediaVedio(mediaVedio);
    }

    /**
     * 修改视频素材
     * 
     * @param mediaVedio 视频素材
     * @return 结果
     */
    @Override
    public int updateMediaVedio(MediaVedio mediaVedio)
    {
        return mediaVedioMapper.updateMediaVedio(mediaVedio);
    }

    /**
     * 批量删除视频素材
     * 
     * @param videoIds 需要删除的视频素材主键
     * @return 结果
     */
    @Override
    public int deleteMediaVedioByVideoIds(Long[] videoIds)
    {
        return mediaVedioMapper.deleteMediaVedioByVideoIds(videoIds);
    }

    /**
     * 删除视频素材信息
     * 
     * @param videoId 视频素材主键
     * @return 结果
     */
    @Override
    public int deleteMediaVedioByVideoId(Long videoId)
    {
        return mediaVedioMapper.deleteMediaVedioByVideoId(videoId);
    }
}
