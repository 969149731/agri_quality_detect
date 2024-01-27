import request from '@/utils/request'

// 查询定量监测结果汇总列表
export function listOutDlDetectRecords(query) {
  return request({
    url: '/out/outDlDetectRecords/list',
    method: 'get',
    params: query
  })
}

// 查询定量监测结果汇总详细
export function getOutDlDetectRecords(recordDlId) {
  return request({
    url: '/out/outDlDetectRecords/' + recordDlId,
    method: 'get'
  })
}

// 新增定量监测结果汇总
export function addOutDlDetectRecords(data) {
  return request({
    url: '/out/outDlDetectRecords',
    method: 'post',
    data: data
  })
}

// 修改定量监测结果汇总
export function updateOutDlDetectRecords(data) {
  return request({
    url: '/out/outDlDetectRecords',
    method: 'put',
    data: data
  })
}

// 删除定量监测结果汇总
export function delOutDlDetectRecords(recordDlId) {
  return request({
    url: '/out/outDlDetectRecords/' + recordDlId,
    method: 'delete'
  })
}
