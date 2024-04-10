import request from '@/utils/request'
import axios from "axios";



//级联查询
export function AddressProvince() {
  return request({
    url: '/detectInf/address',
    method: 'get',
  })
}
export function findByprovinceCode(provinceCode) {
  return request({
    url: '/detectInf/addressCity/'+provinceCode,
    method: 'get'
  })
}

export function findBycityCode(cityCode) {
  return request({
    url: '/detectInf/addressTown/'+cityCode,
    method: 'get'
  })
}




// 查询各市样品检测结果详细列表
export function listDetectionDetails(query) {
  return request({
    url: '/detection/detectionDetails/list',
    method: 'get',
    params: query
  })
}



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
