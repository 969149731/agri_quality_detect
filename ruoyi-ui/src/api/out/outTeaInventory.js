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




// 查询茶叶种类及数量列表
export function listOutTeaInventory(query) {
  return request({
    url: '/out/outTeaInventory/list',
    method: 'get',
    params: query
  })
}

// 查询茶叶种类及数量详细
export function getOutTeaInventory(teaInventoryId) {
  return request({
    url: '/out/outTeaInventory/' + teaInventoryId,
    method: 'get'
  })
}

// 新增茶叶种类及数量
export function addOutTeaInventory(data) {
  return request({
    url: '/out/outTeaInventory',
    method: 'post',
    data: data
  })
}

// 修改茶叶种类及数量
export function updateOutTeaInventory(data) {
  return request({
    url: '/out/outTeaInventory',
    method: 'put',
    data: data
  })
}

// 删除茶叶种类及数量
export function delOutTeaInventory(teaInventoryId) {
  return request({
    url: '/out/outTeaInventory/' + teaInventoryId,
    method: 'delete'
  })
}
