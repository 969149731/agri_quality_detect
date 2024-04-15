import request from '@/utils/request'

export function listOutFruBanPesDetRecords(query) {
  return request({
    url: '/out/outFruBanPesDetRecords/list',
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
