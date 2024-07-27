import request from '@/utils/request'

// 查询我的导入列表
export function listMyDetectionDetails(query) {
  return request({
    url: '/detection/myDetectionDetails/list',
    method: 'get',
    params: query
  })
}

// 查询我的导入详细
export function getMyDetectionDetails(citySampleTestDetailsId) {
  return request({
    url: '/detection/myDetectionDetails/' + citySampleTestDetailsId,
    method: 'get'
  })
}

// 新增我的导入
export function addMyDetectionDetails(data) {
  return request({
    url: '/detection/myDetectionDetails',
    method: 'post',
    data: data
  })
}

// 修改我的导入
export function updateMyDetectionDetails(data) {
  return request({
    url: '/detection/myDetectionDetails',
    method: 'put',
    data: data
  })
}

// 删除我的导入
export function delMyDetectionDetails(citySampleTestDetailsId) {
  return request({
    url: '/detection/myDetectionDetails/' + citySampleTestDetailsId,
    method: 'delete'
  })
}
