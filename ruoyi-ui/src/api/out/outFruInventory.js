import request from '@/utils/request'

// 查询水果种类及数量列表
export function listOutFruInventory(query) {
  return request({
    url: '/out/outFruInventory/list',
    method: 'get',
    params: query
  })
}

// 查询水果种类及数量详细
export function getOutFruInventory(vegFruInventoryId) {
  return request({
    url: '/out/outFruInventory/' + vegFruInventoryId,
    method: 'get'
  })
}

// 新增水果种类及数量
export function addOutFruInventory(data) {
  return request({
    url: '/out/outFruInventory',
    method: 'post',
    data: data
  })
}

// 修改水果种类及数量
export function updateOutFruInventory(data) {
  return request({
    url: '/out/outFruInventory',
    method: 'put',
    data: data
  })
}

// 删除水果种类及数量
export function delOutFruInventory(vegFruInventoryId) {
  return request({
    url: '/out/outFruInventory/' + vegFruInventoryId,
    method: 'delete'
  })
}
