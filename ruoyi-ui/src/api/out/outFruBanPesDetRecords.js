import request from '@/utils/request'

// 查询水果禁用农药检出及超标情况列表
export function listOutFruBanPesDetRecords(query) {
  return request({
    url: '/out/outFruBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询水果禁用农药检出及超标情况详细
export function getOutFruBanPesDetRecords(fruBanPesDetRecordsId) {
  return request({
    url: '/out/outFruBanPesDetRecords/' + fruBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增水果禁用农药检出及超标情况
export function addOutFruBanPesDetRecords(data) {
  return request({
    url: '/out/outFruBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改水果禁用农药检出及超标情况
export function updateOutFruBanPesDetRecords(data) {
  return request({
    url: '/out/outFruBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除水果禁用农药检出及超标情况
export function delOutFruBanPesDetRecords(fruBanPesDetRecordsId) {
  return request({
    url: '/out/outFruBanPesDetRecords/' + fruBanPesDetRecordsId,
    method: 'delete'
  })
}
