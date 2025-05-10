import request from '@/utils/request'

// 查询教程管理列表
export function listTmanagement(query) {
  return request({
    url: '/system/Tmanagement/list',
    method: 'get',
    params: query
  })
}

// 查询教程管理详细
export function getTmanagement(tutorialId) {
  return request({
    url: '/system/Tmanagement/' + tutorialId,
    method: 'get'
  })
}

// 新增教程管理
export function addTmanagement(data) {
  return request({
    url: '/system/Tmanagement',
    method: 'post',
    data: data
  })
}

// 修改教程管理
export function updateTmanagement(data) {
  return request({
    url: '/system/Tmanagement',
    method: 'put',
    data: data
  })
}

// 删除教程管理
export function delTmanagement(tutorialId) {
  return request({
    url: '/system/Tmanagement/' + tutorialId,
    method: 'delete'
  })
}
// 新增：批量上架教程
export function shelfTmanagement(tutorialIds) {
  return request({
    url: '/system/Tmanagement/shelf',
    method: 'post',
    data: tutorialIds
  })
}

// 新增：批量下架教程
export function unshelfTmanagement(tutorialIds) {
  return request({
    url: '/system/Tmanagement/unshelf',
    method: 'post',
    data: tutorialIds
  })
}

