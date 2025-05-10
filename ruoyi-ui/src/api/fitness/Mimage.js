import request from '@/utils/request'

// 查询图片素材列表
export function listMimage(query) {
  return request({
    url: '/fitness/Mimage/list',
    method: 'get',
    params: query
  })
}

// 查询图片素材详细
export function getMimage(imageId) {
  return request({
    url: '/fitness/Mimage/' + imageId,
    method: 'get'
  })
}

// 新增图片素材
export function addMimage(data) {
  return request({
    url: '/fitness/Mimage',
    method: 'post',
    data: data
  })
}

// 修改图片素材
export function updateMimage(data) {
  return request({
    url: '/fitness/Mimage',
    method: 'put',
    data: data
  })
}

// 删除图片素材
export function delMimage(imageId) {
  return request({
    url: '/fitness/Mimage/' + imageId,
    method: 'delete'
  })
}
