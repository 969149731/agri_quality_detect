import request from '@/utils/request'

// 查询蔬菜上非禁止使用农药检出及超标情况列表
export function listOutVegNoBanPesDetRecords(query) {
  return request({
    url: '/out/outVegNoBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询蔬菜上非禁止使用农药检出及超标情况详细
export function getOutVegNoBanPesDetRecords(vegNoBanPesDetRecordsId) {
  return request({
    url: '/out/outVegNoBanPesDetRecords/' + vegNoBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增蔬菜上非禁止使用农药检出及超标情况
export function addOutVegNoBanPesDetRecords(data) {
  return request({
    url: '/out/outVegNoBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改蔬菜上非禁止使用农药检出及超标情况
export function updateOutVegNoBanPesDetRecords(data) {
  return request({
    url: '/out/outVegNoBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除蔬菜上非禁止使用农药检出及超标情况
export function delOutVegNoBanPesDetRecords(vegNoBanPesDetRecordsId) {
  return request({
    url: '/out/outVegNoBanPesDetRecords/' + vegNoBanPesDetRecordsId,
    method: 'delete'
  })
}
