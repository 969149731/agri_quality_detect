import request from '@/utils/request'

// 查询被抽样环节数量统计列表
export function listOutSampleStageType(query) {
  return request({
    url: '/out/outSampleStageType/list',
    method: 'get',
    params: query
  })
}

// 查询被抽样环节数量统计详细
export function getOutSampleStageType(sampleQualityId) {
  return request({
    url: '/out/outSampleStageType/' + sampleQualityId,
    method: 'get'
  })
}

// 新增被抽样环节数量统计
export function addOutSampleStageType(data) {
  return request({
    url: '/out/outSampleStageType',
    method: 'post',
    data: data
  })
}

// 修改被抽样环节数量统计
export function updateOutSampleStageType(data) {
  return request({
    url: '/out/outSampleStageType',
    method: 'put',
    data: data
  })
}

// 删除被抽样环节数量统计
export function delOutSampleStageType(sampleQualityId) {
  return request({
    url: '/out/outSampleStageType/' + sampleQualityId,
    method: 'delete'
  })
}
