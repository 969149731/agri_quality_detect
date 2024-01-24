import request from '@/utils/request'

// 查询蔬果检测列表
export function listFruitVegQualification(query) {
  return request({
    url: '/detection/fruitVegQualification/list',
    method: 'get',
    params: query
  })
}

// 查询蔬果检测详细
export function getFruitVegQualification(id) {
  return request({
    url: '/detection/fruitVegQualification/' + id,
    method: 'get'
  })
}

// 新增蔬果检测
export function addFruitVegQualification(data) {
  return request({
    url: '/detection/fruitVegQualification',
    method: 'post',
    data: data
  })
}

// 修改蔬果检测
export function updateFruitVegQualification(data) {
  return request({
    url: '/detection/fruitVegQualification',
    method: 'put',
    data: data
  })
}

// 删除蔬果检测
export function delFruitVegQualification(id) {
  return request({
    url: '/detection/fruitVegQualification/' + id,
    method: 'delete'
  })
}
