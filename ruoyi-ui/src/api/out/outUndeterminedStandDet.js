import request from '@/utils/request'

// 查询无判定标准检出值详细列表
export function listOutUndeterminedStandDet(query) {
  return request({
    url: '/out/outUndeterminedStandDet/list',
    method: 'get',
    params: query
  })
}

// 查询无判定标准检出值详细详细
export function getOutUndeterminedStandDet(undeterminedStandardDetectionId) {
  return request({
    url: '/out/outUndeterminedStandDet/' + undeterminedStandardDetectionId,
    method: 'get'
  })
}

// 新增无判定标准检出值详细
export function addOutUndeterminedStandDet(data) {
  return request({
    url: '/out/outUndeterminedStandDet',
    method: 'post',
    data: data
  })
}

// 修改无判定标准检出值详细
export function updateOutUndeterminedStandDet(data) {
  return request({
    url: '/out/outUndeterminedStandDet',
    method: 'put',
    data: data
  })
}

// 删除无判定标准检出值详细
export function delOutUndeterminedStandDet(undeterminedStandardDetectionId) {
  return request({
    url: '/out/outUndeterminedStandDet/' + undeterminedStandardDetectionId,
    method: 'delete'
  })
}
