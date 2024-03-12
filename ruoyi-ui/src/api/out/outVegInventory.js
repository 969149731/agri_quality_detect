import request from '@/utils/request'

// 查询蔬菜种类及数量列表
export function listOutVegInventory(query) {
  return request({
    url: '/out/outVegInventory/list',
    method: 'get',
    params: query
  })
}

// 查询蔬菜种类及数量详细
export function getOutVegInventory(vegFruInventoryId) {
  return request({
    url: '/out/outVegInventory/' + vegFruInventoryId,
    method: 'get'
  })
}

// 新增蔬菜种类及数量
export function addOutVegInventory(data) {
  return request({
    url: '/out/outVegInventory',
    method: 'post',
    data: data
  })
}

// 修改蔬菜种类及数量
export function updateOutVegInventory(data) {
  return request({
    url: '/out/outVegInventory',
    method: 'put',
    data: data
  })
}

// 删除蔬菜种类及数量
export function delOutVegInventory(vegFruInventoryId) {
  return request({
    url: '/out/outVegInventory/' + vegFruInventoryId,
    method: 'delete'
  })
}
