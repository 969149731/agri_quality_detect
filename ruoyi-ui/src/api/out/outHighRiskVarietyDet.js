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


// 查询高风险品种样品检出情况列表
export function listOutHighRiskVarietyDet(query) {
  return request({
    url: '/out/outHighRiskVarietyDet/list',
    method: 'get',
    params: query
  })
}

// 查询高风险品种样品检出情况详细
export function getOutHighRiskVarietyDet(outHighRiskVarietyDetId) {
  return request({
    url: '/out/outHighRiskVarietyDet/' + outHighRiskVarietyDetId,
    method: 'get'
  })
}

// 新增高风险品种样品检出情况
export function addOutHighRiskVarietyDet(data) {
  return request({
    url: '/out/outHighRiskVarietyDet',
    method: 'post',
    data: data
  })
}

// 修改高风险品种样品检出情况
export function updateOutHighRiskVarietyDet(data) {
  return request({
    url: '/out/outHighRiskVarietyDet',
    method: 'put',
    data: data
  })
}

// 删除高风险品种样品检出情况
export function delOutHighRiskVarietyDet(outHighRiskVarietyDetId) {
  return request({
    url: '/out/outHighRiskVarietyDet/' + outHighRiskVarietyDetId,
    method: 'delete'
  })
}
