import request from '@/utils/request'

// 查询各类蔬菜水果合格率情况列表
export function listOutFruVegQualification(query) {
  return request({
    url: '/out/outFruVegQualification/list',
    method: 'get',
    params: query
  })
}

// 查询各类蔬菜水果合格率情况详细
export function getOutFruVegQualification(fruVegQualificationId) {
  return request({
    url: '/out/outFruVegQualification/' + fruVegQualificationId,
    method: 'get'
  })
}

// 新增各类蔬菜水果合格率情况
export function addOutFruVegQualification(data) {
  return request({
    url: '/out/outFruVegQualification',
    method: 'post',
    data: data
  })
}

// 修改各类蔬菜水果合格率情况
export function updateOutFruVegQualification(data) {
  return request({
    url: '/out/outFruVegQualification',
    method: 'put',
    data: data
  })
}

// 删除各类蔬菜水果合格率情况
export function delOutFruVegQualification(fruVegQualificationId) {
  return request({
    url: '/out/outFruVegQualification/' + fruVegQualificationId,
    method: 'delete'
  })
}
