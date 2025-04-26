import request from '@/utils/request'

// 查询健身教程列表
export function listTfitness(query) {
  return request({
    url: '/fitness/Tfitness/list',
    method: 'get',
    params: query
  })
}

// 查询健身教程详细
export function getTfitness(tutorialId) {
  return request({
    url: '/fitness/Tfitness/' + tutorialId,
    method: 'get'
  })
}

// 新增健身教程
export function addTfitness(data) {
  return request({
    url: '/fitness/Tfitness',
    method: 'post',
    data: data
  })
}

// 修改健身教程
export function updateTfitness(data) {
  return request({
    url: '/fitness/Tfitness',
    method: 'put',
    data: data
  })
}

// 删除健身教程
export function delTfitness(tutorialId) {
  return request({
    url: '/fitness/Tfitness/' + tutorialId,
    method: 'delete'
  })
}
