import request from '@/utils/request'

// 查询省份信息列表
export function listProvince(query) {
  return request({
    url: '/address/province/list',
    method: 'get',
    params: query
  })
}

// 查询省份信息详细
export function getProvince(id) {
  return request({
    url: '/address/province/' + id,
    method: 'get'
  })
}

// 新增省份信息
export function addProvince(data) {
  return request({
    url: '/address/province',
    method: 'post',
    data: data
  })
}

// 修改省份信息
export function updateProvince(data) {
  return request({
    url: '/address/province',
    method: 'put',
    data: data
  })
}

// 删除省份信息
export function delProvince(id) {
  return request({
    url: '/address/province/' + id,
    method: 'delete'
  })
}
