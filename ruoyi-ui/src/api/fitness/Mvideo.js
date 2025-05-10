import request from '@/utils/request'

// 查询视频素材列表
export function listMvideo(query) {
  return request({
    url: '/fitness/Mvideo/list',
    method: 'get',
    params: query
  })
}

// 查询视频素材详细
export function getMvideo(videoId) {
  return request({
    url: '/fitness/Mvideo/' + videoId,
    method: 'get'
  })
}

// 新增视频素材
export function addMvideo(data) {
  return request({
    url: '/fitness/Mvideo',
    method: 'post',
    data: data
  })
}

// 修改视频素材
export function updateMvideo(data) {
  return request({
    url: '/fitness/Mvideo',
    method: 'put',
    data: data
  })
}

// 删除视频素材
export function delMvideo(videoId) {
  return request({
    url: '/fitness/Mvideo/' + videoId,
    method: 'delete'
  })
}
