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

// 查询蔬菜水果禁用农药检出样品明细列表
export function listOutBanPesticideDetection(query) {
  return request({
    url: '/out/outBanPesticideDetection/list',
    method: 'get',
    params: query
  })
}

// 查询蔬菜水果禁用农药检出样品明细详细
export function getOutBanPesticideDetection(banPesticideDetectionId) {
  return request({
    url: '/out/outBanPesticideDetection/' + banPesticideDetectionId,
    method: 'get'
  })
}

// 新增蔬菜水果禁用农药检出样品明细
export function addOutBanPesticideDetection(data) {
  return request({
    url: '/out/outBanPesticideDetection',
    method: 'post',
    data: data
  })
}

// 修改蔬菜水果禁用农药检出样品明细
export function updateOutBanPesticideDetection(data) {
  return request({
    url: '/out/outBanPesticideDetection',
    method: 'put',
    data: data
  })
}

// 删除蔬菜水果禁用农药检出样品明细
export function delOutBanPesticideDetection(banPesticideDetectionId) {
  return request({
    url: '/out/outBanPesticideDetection/' + banPesticideDetectionId,
    method: 'delete'
  })
}
