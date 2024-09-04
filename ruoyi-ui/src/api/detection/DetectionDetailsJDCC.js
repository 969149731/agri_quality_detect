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



export function updateCheck(citySampleTestDetailsId) {
  return request({
    url: '/detection/DetectionDetailsLXJC/updateCheck/' + citySampleTestDetailsId+'/'+'监督抽查',
    method: 'get'
  })
}


// 查询例行检测详细列表
export function listDetectionDetailsJDCC(query) {
  return request({
    url: '/detection/DetectionDetailsJDCC/list',
    method: 'get',
    params: query
  })
}

// // 查询例行检测详细详细
// export function getDetectionDetailsLXJC(citySampleTestDetailsId) {
//   return request({
//     url: '/detection/DetectionDetailsLXJC/' + citySampleTestDetailsId,
//     method: 'get'
//   })
// }
//
// // 新增例行检测详细
// export function addDetectionDetailsLXJC(data) {
//   return request({
//     url: '/detection/DetectionDetailsLXJC',
//     method: 'post',
//     data: data
//   })
// }
//
// // 修改例行检测详细
// export function updateDetectionDetailsLXJC(data) {
//   return request({
//     url: '/detection/DetectionDetailsLXJC',
//     method: 'put',
//     data: data
//   })
// }
//
// // 删除例行检测详细
// export function delDetectionDetailsLXJC(citySampleTestDetailsId) {
//   return request({
//     url: '/detection/DetectionDetailsLXJC/' + citySampleTestDetailsId,
//     method: 'delete'
//   })
// }






// 查询各市样品检测结果详细详细
export function getDetectionDetails(citySampleTestDetailsId) {
  return request({
    url: '/detection/detectionDetails/' + citySampleTestDetailsId,
    method: 'get'
  })
}

// 新增各市样品检测结果详细
export function addDetectionDetails(data) {
  return request({
    url: '/detection/detectionDetails',
    method: 'post',
    data: data
  })
}

// 修改各市样品检测结果详细
export function updateDetectionDetails(data) {
  return request({
    url: '/detection/detectionDetails',
    method: 'put',
    data: data
  })
}

// 删除各市样品检测结果详细
export function delDetectionDetails(citySampleTestDetailsId) {
  return request({
    url: '/detection/detectionDetails/' + citySampleTestDetailsId,
    method: 'delete'
  })
}
