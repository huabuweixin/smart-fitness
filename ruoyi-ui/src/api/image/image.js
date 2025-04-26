import request from '@/utils/request'

// 查询图片存储列表
export function listImage(query) {
  return request({
    url: '/image/image/list',
    method: 'get',
    params: query
  })
}

// 查询图片存储详细
export function getImage(imageId) {
  return request({
    url: '/image/image/' + imageId,
    method: 'get'
  })
}

// 新增图片存储
export function addImage(data) {
  return request({
    url: '/image/image',
    method: 'post',
    data: data
  })
}

// 修改图片存储
export function updateImage(data) {
  return request({
    url: '/image/image',
    method: 'put',
    data: data
  })
}

// 在api/image/image.js 中明确定义上传接口
export function uploadImage(data) {
  return request({
    url: '/image/image/upload',
    method: 'post',
    data
  })
}
// 删除图片存储
export function delImage(imageId) {
  return request({
    url: '/image/image/' + imageId,
    method: 'delete'
  })
}
