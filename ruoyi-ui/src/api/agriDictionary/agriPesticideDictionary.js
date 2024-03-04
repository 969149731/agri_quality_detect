import request from '@/utils/request'

// 查询农药字典列表
export function listAgriPesticideDictionary(query) {
  return request({
    url: '/agriDictionary/agriPesticideDictionary/list',
    method: 'get',
    params: query
  })
}

// 查询农药字典详细
export function getAgriPesticideDictionary(pesticideId) {
  return request({
    url: '/agriDictionary/agriPesticideDictionary/' + pesticideId,
    method: 'get'
  })
}

// 新增农药字典
export function addAgriPesticideDictionary(data) {
  return request({
    url: '/agriDictionary/agriPesticideDictionary',
    method: 'post',
    data: data
  })
}

// 修改农药字典
export function updateAgriPesticideDictionary(data) {
  return request({
    url: '/agriDictionary/agriPesticideDictionary',
    method: 'put',
    data: data
  })
}

// 删除农药字典
export function delAgriPesticideDictionary(pesticideId) {
  return request({
    url: '/agriDictionary/agriPesticideDictionary/' + pesticideId,
    method: 'delete'
  })
}
