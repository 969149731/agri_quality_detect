import request from '@/utils/request'

// 查询蔬菜禁用农药检出及超标情况列表
export function listOutVegBanPesDetRecords(query) {
  return request({
    url: '/out/outVegBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询蔬菜禁用农药检出及超标情况详细
export function getOutVegBanPesDetRecords(vegBanPesDetRecordsId) {
  return request({
    url: '/out/outVegBanPesDetRecords/' + vegBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增蔬菜禁用农药检出及超标情况
export function addOutVegBanPesDetRecords(data) {
  return request({
    url: '/out/outVegBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改蔬菜禁用农药检出及超标情况
export function updateOutVegBanPesDetRecords(data) {
  return request({
    url: '/out/outVegBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除蔬菜禁用农药检出及超标情况
export function delOutVegBanPesDetRecords(vegBanPesDetRecordsId) {
  return request({
    url: '/out/outVegBanPesDetRecords/' + vegBanPesDetRecordsId,
    method: 'delete'
  })
}
