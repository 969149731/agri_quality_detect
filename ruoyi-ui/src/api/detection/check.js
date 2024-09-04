import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listCheck(query) {
  return request({
    url: '/system/check/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addCheck(data) {
  return request({
    url: '/system/check',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
// export function updateCheck(data) {
//   return request({
//     url: '/system/check',
//     method: 'put',
//     data: data
//   })
// }

export function updateCheck(id) {
  return request({
    url: '/system/check/updateFlag/' + id,
    method: 'get'
  })
}

// 删除【请填写功能名称】
export function delCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'delete'
  })
}
