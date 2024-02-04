import request from '@/utils/request'

// 查询水果非禁止使用农药检出及超标情况列表
export function listOutFruNoBanPesDetRecords(query) {
  return request({
    url: '/out/outFruNoBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询水果非禁止使用农药检出及超标情况详细
export function getOutFruNoBanPesDetRecords(fruNoBanPesDetRecordsId) {
  return request({
    url: '/out/outFruNoBanPesDetRecords/' + fruNoBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增水果非禁止使用农药检出及超标情况
export function addOutFruNoBanPesDetRecords(data) {
  return request({
    url: '/out/outFruNoBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改水果非禁止使用农药检出及超标情况
export function updateOutFruNoBanPesDetRecords(data) {
  return request({
    url: '/out/outFruNoBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除水果非禁止使用农药检出及超标情况
export function delOutFruNoBanPesDetRecords(fruNoBanPesDetRecordsId) {
  return request({
    url: '/out/outFruNoBanPesDetRecords/' + fruNoBanPesDetRecordsId,
    method: 'delete'
  })
}
// 查询水果非禁止使用农药检出及超标情况列表,新方法
export function listOutFruNoBanPesDetRecords2(query) {
  return request({
    url: '/out/outFruNoBanPesDetRecords/listNew',
    method: 'get',
    params: query
  })
}

