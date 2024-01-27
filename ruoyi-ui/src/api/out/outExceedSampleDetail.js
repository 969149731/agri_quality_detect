import request from '@/utils/request'

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
