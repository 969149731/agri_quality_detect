import request from '@/utils/request'

// 查询茶叶上非禁止使用农药检出及超标情况列表
export function listOutTeaNoBanPesDetRecords(query) {
  return request({
    url: '/out/outTeaNoBanPesDetRecords/list',
    method: 'get',
    params: query
  })
}

// 查询茶叶上非禁止使用农药检出及超标情况详细
export function getOutTeaNoBanPesDetRecords(teaNoBanPesDetRecordsId) {
  return request({
    url: '/out/outTeaNoBanPesDetRecords/' + teaNoBanPesDetRecordsId,
    method: 'get'
  })
}

// 新增茶叶上非禁止使用农药检出及超标情况
export function addOutTeaNoBanPesDetRecords(data) {
  return request({
    url: '/out/outTeaNoBanPesDetRecords',
    method: 'post',
    data: data
  })
}

// 修改茶叶上非禁止使用农药检出及超标情况
export function updateOutTeaNoBanPesDetRecords(data) {
  return request({
    url: '/out/outTeaNoBanPesDetRecords',
    method: 'put',
    data: data
  })
}

// 删除茶叶上非禁止使用农药检出及超标情况
export function delOutTeaNoBanPesDetRecords(teaNoBanPesDetRecordsId) {
  return request({
    url: '/out/outTeaNoBanPesDetRecords/' + teaNoBanPesDetRecordsId,
    method: 'delete'
  })
}
