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


// 查询超标蔬菜水果样品明细列表
export function listOutExceedSampleDetail(query) {
  return request({
    url: '/out/outExceedSampleDetail/list',
    method: 'get',
    params: query
  })
}

// 查询超标蔬菜水果样品明细详细
export function getOutExceedSampleDetail(exceedSampleDetailId) {
  return request({
    url: '/out/outExceedSampleDetail/' + exceedSampleDetailId,
    method: 'get'
  })
}

// 新增超标蔬菜水果样品明细
export function addOutExceedSampleDetail(data) {
  return request({
    url: '/out/outExceedSampleDetail',
    method: 'post',
    data: data
  })
}

// 修改超标蔬菜水果样品明细
export function updateOutExceedSampleDetail(data) {
  return request({
    url: '/out/outExceedSampleDetail',
    method: 'put',
    data: data
  })
}

// 删除超标蔬菜水果样品明细
export function delOutExceedSampleDetail(exceedSampleDetailId) {
  return request({
    url: '/out/outExceedSampleDetail/' + exceedSampleDetailId,
    method: 'delete'
  })
}
