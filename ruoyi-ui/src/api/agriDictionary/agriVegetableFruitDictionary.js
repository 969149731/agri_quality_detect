import request from '@/utils/request'

// 查询蔬果表列表
export function listAgriVegetableFruitDictionary(query) {
  return request({
    url: '/agriDictionary/agriVegetableFruitDictionary/list',
    method: 'get',
    params: query
  })
}

// 查询蔬果表详细
export function getAgriVegetableFruitDictionary(vegetableFruitId) {
  return request({
    url: '/agriDictionary/agriVegetableFruitDictionary/' + vegetableFruitId,
    method: 'get'
  })
}

// 新增蔬果表
export function addAgriVegetableFruitDictionary(data) {
  return request({
    url: '/agriDictionary/agriVegetableFruitDictionary',
    method: 'post',
    data: data
  })
}

// 修改蔬果表
export function updateAgriVegetableFruitDictionary(data) {
  return request({
    url: '/agriDictionary/agriVegetableFruitDictionary',
    method: 'put',
    data: data
  })
}

// 删除蔬果表
export function delAgriVegetableFruitDictionary(vegetableFruitId) {
  return request({
    url: '/agriDictionary/agriVegetableFruitDictionary/' + vegetableFruitId,
    method: 'delete'
  })
}
