import request from '@/utils/request'

// 查询各抽样环节合格率情况列表
export function listOutSampleQuality(query) {
  return request({
    url: '/out/outSampleQuality/list',
    method: 'get',
    params: query
  })
}

// 查询各抽样环节合格率情况详细
export function getOutSampleQuality(sampleQualityId) {
  return request({
    url: '/out/outSampleQuality/' + sampleQualityId,
    method: 'get'
  })
}

// 新增各抽样环节合格率情况
export function addOutSampleQuality(data) {
  return request({
    url: '/out/outSampleQuality',
    method: 'post',
    data: data
  })
}

// 修改各抽样环节合格率情况
export function updateOutSampleQuality(data) {
  return request({
    url: '/out/outSampleQuality',
    method: 'put',
    data: data
  })
}

// 删除各抽样环节合格率情况
export function delOutSampleQuality(sampleQualityId) {
  return request({
    url: '/out/outSampleQuality/' + sampleQualityId,
    method: 'delete'
  })
}
