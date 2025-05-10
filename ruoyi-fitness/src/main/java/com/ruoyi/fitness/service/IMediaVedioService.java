package com.ruoyi.fitness.service;

import java.util.List;
import com.ruoyi.fitness.domain.MediaVedio;

/**
 * 视频素材Service接口
 * 
 * @author muzi
 * @date 2025-05-02
 */
public interface IMediaVedioService 
{
    /**
     * 查询视频素材
     * 
     * @param videoId 视频素材主键
     * @return 视频素材
     */
    public MediaVedio selectMediaVedioByVideoId(Long videoId);

    /**
     * 查询视频素材列表
     * 
     * @param mediaVedio 视频素材
     * @return 视频素材集合
     */
    public List<MediaVedio> selectMediaVedioList(MediaVedio mediaVedio);

    /**
     * 新增视频素材
     * 
     * @param mediaVedio 视频素材
     * @return 结果
     */
    public int insertMediaVedio(MediaVedio mediaVedio);

    /**
     * 修改视频素材
     * 
     * @param mediaVedio 视频素材
     * @return 结果
     */
    public int updateMediaVedio(MediaVedio mediaVedio);

    /**
     * 批量删除视频素材
     * 
     * @param videoIds 需要删除的视频素材主键集合
     * @return 结果
     */
    public int deleteMediaVedioByVideoIds(Long[] videoIds);

    /**
     * 删除视频素材信息
     * 
     * @param videoId 视频素材主键
     * @return 结果
     */
    public int deleteMediaVedioByVideoId(Long videoId);
}
