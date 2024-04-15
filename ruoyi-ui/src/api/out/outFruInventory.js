import request from '@/utils/request'




//级联查询(抽样地点)
export function samplingAddressProvince() {
  return request({
    url: '/addressInf/address',
    method: 'get',
  })
}
export function findBySamplingProvinceCode(provinceCode) {
  return request({
    url: '/addressInf/addressCity/'+provinceCode,
    method: 'get'
  })
}

export function findBySamplingCityCode(cityCode) {
  return request({
    url: '/addressInf/addressTown/'+cityCode,
    method: 'get'
  })
}
////////////////////////////////////////////////



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
