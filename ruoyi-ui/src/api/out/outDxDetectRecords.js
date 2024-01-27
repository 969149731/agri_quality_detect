import request from '@/utils/request'

// 查询定性监测结果汇总列表
export function listOutDxDetectRecords(query) {
  return request({
    url: '/out/outDxDetectRecords/list',
    method: 'get',
    params: query
  })
}

// 查询定性监测结果汇总详细
export function getOutDxDetectRecords(recordDxId) {
  return request({
    url: '/out/outDxDetectRecords/' + recordDxId,
    method: 'get'
  })
}

// 新增定性监测结果汇总
export function addOutDxDetectRecords(data) {
  return request({
    url: '/out/outDxDetectRecords',
    method: 'post',
    data: data
  })
}

// 修改定性监测结果汇总
export function updateOutDxDetectRecords(data) {
  return request({
    url: '/out/outDxDetectRecords',
    method: 'put',
    data: data
  })
}

// 删除定性监测结果汇总
export function delOutDxDetectRecords(recordDxId) {
  return request({
    url: '/out/outDxDetectRecords/' + recordDxId,
    method: 'delete'
  })
}
