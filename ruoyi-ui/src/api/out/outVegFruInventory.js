import request from '@/utils/request'

// 查询蔬菜水果种类及数量列表
export function listOutVegFruInventory(query) {
  return request({
    url: '/out/outVegFruInventory/list',
    method: 'get',
    params: query
  })
}

// 查询蔬菜水果种类及数量详细
export function getOutVegFruInventory(vegFruInventoryId) {
  return request({
    url: '/out/outVegFruInventory/' + vegFruInventoryId,
    method: 'get'
  })
}

// 新增蔬菜水果种类及数量
export function addOutVegFruInventory(data) {
  return request({
    url: '/out/outVegFruInventory',
    method: 'post',
    data: data
  })
}

// 修改蔬菜水果种类及数量
export function updateOutVegFruInventory(data) {
  return request({
    url: '/out/outVegFruInventory',
    method: 'put',
    data: data
  })
}

// 删除蔬菜水果种类及数量
export function delOutVegFruInventory(vegFruInventoryId) {
  return request({
    url: '/out/outVegFruInventory/' + vegFruInventoryId,
    method: 'delete'
  })
}
