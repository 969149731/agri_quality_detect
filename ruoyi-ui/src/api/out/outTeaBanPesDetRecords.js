import request from '@/utils/request'

// 查询茶叶禁用农药检出及超标情况列表
export function listOutTeaBanPesDetRecords(query) {
  return request({
    url: '/out/outTeaBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询茶叶禁用农药检出及超标情况详细
export function getOutTeaBanPesDetRecords(teaBanPesDetRecordsId) {
  return request({
    url: '/out/outTeaBanPesDetRecords/' + teaBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增茶叶禁用农药检出及超标情况
export function addOutTeaBanPesDetRecords(data) {
  return request({
    url: '/out/outTeaBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改茶叶禁用农药检出及超标情况
export function updateOutTeaBanPesDetRecords(data) {
  return request({
    url: '/out/outTeaBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除茶叶禁用农药检出及超标情况
export function delOutTeaBanPesDetRecords(teaBanPesDetRecordsId) {
  return request({
    url: '/out/outTeaBanPesDetRecords/' + teaBanPesDetRecordsId,
    method: 'delete'
  })
}
