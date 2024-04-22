import request from '@/utils/request'

// 查询被抽样环节数量统计列表
export function listOutSampleStageType(query) {
  return request({
    url: '/out/outSampleStageType/list',
    method: 'get',
    params: query
  })
}
///////////////////////////////////////////
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
