import request from '@/utils/request'

// 查询区县信息列表
export function listTown(query) {
  return request({
    url: '/address/town/list',
    method: 'get',
    params: query
  })
}

// 查询区县信息详细
export function getTown(id) {
  return request({
    url: '/address/town/' + id,
    method: 'get'
  })
}

// 新增区县信息
export function addTown(data) {
  return request({
    url: '/address/town',
    method: 'post',
    data: data
  })
}

// 修改区县信息
export function updateTown(data) {
  return request({
    url: '/address/town',
    method: 'put',
    data: data
  })
}

// 删除区县信息
export function delTown(id) {
  return request({
    url: '/address/town/' + id,
    method: 'delete'
  })
}
