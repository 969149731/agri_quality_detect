import request from '@/utils/request'

// 查询参照国际组织或国家标准合格率情况列表
export function listOutStandCompliance(query) {
  return request({
    url: '/out/outStandCompliance/list',
    method: 'get',
    params: query
  })
}

// 查询参照国际组织或国家标准合格率情况详细
export function getOutStandCompliance(interStandId) {
  return request({
    url: '/out/outStandCompliance/' + interStandId,
    method: 'get'
  })
}

// 新增参照国际组织或国家标准合格率情况
export function addOutStandCompliance(data) {
  return request({
    url: '/out/outStandCompliance',
    method: 'post',
    data: data
  })
}

// 修改参照国际组织或国家标准合格率情况
export function updateOutStandCompliance(data) {
  return request({
    url: '/out/outStandCompliance',
    method: 'put',
    data: data
  })
}

// 删除参照国际组织或国家标准合格率情况
export function delOutStandCompliance(interStandId) {
  return request({
    url: '/out/outStandCompliance/' + interStandId,
    method: 'delete'
  })
}

// 查询参照国际组织或国家标准合格率情况列表
export function listOutStandCompliance2(query) {
  return request({
    url: '/out/outStandCompliance/listNew',
    method: 'get',
    params: query
  })
}
