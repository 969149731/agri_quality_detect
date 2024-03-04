import request from '@/utils/request'

// 查询农药最大残留限量参考标准列表
export function listAgriPesResStandDictionary(query) {
  return request({
    url: '/agriDictionary/agriPesResStandDictionary/list',
    method: 'get',
    params: query
  })
}

// 查询农药最大残留限量参考标准详细
export function getAgriPesResStandDictionary(pesticideResidueStandardId) {
  return request({
    url: '/agriDictionary/agriPesResStandDictionary/' + pesticideResidueStandardId,
    method: 'get'
  })
}

// 新增农药最大残留限量参考标准
export function addAgriPesResStandDictionary(data) {
  return request({
    url: '/agriDictionary/agriPesResStandDictionary',
    method: 'post',
    data: data
  })
}

// 修改农药最大残留限量参考标准
export function updateAgriPesResStandDictionary(data) {
  return request({
    url: '/agriDictionary/agriPesResStandDictionary',
    method: 'put',
    data: data
  })
}

// 删除农药最大残留限量参考标准
export function delAgriPesResStandDictionary(pesticideResidueStandardId) {
  return request({
    url: '/agriDictionary/agriPesResStandDictionary/' + pesticideResidueStandardId,
    method: 'delete'
  })
}
