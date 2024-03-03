<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="记录创建的时间" prop="createdDate">
        <el-date-picker clearable
          v-model="queryParams.createdDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录创建的时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['out:outSampleStageType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['out:outSampleStageType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['out:outSampleStageType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outSampleStageType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table
      v-if="!peopleTagNumLoading"
      ref="refreshTable"
      :data="outSampleStageTypeList"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      style="width: 100%;"
      id="table1"
    >
      <el-table-column label="样品来源"  align="center">
        <el-table-column  prop="stageIncludeType" width="150px" align="center"/>
        <el-table-column  prop="samplingStageType" width="150px" align="center"/>
      </el-table-column>

      <el-table-column label="数量" prop="unitNum" width="200px" align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listOutSampleStageType, getOutSampleStageType, delOutSampleStageType, addOutSampleStageType, updateOutSampleStageType } from "@/api/out/outSampleStageType";
import * as XLSX from "xlsx";
import * as XLSXS from "xlsx-style";
import FileSaver from 'file-saver'

export default {
  name: "OutSampleStageType",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 被抽样环节数量统计表格数据
      outSampleStageTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        samplingStageType: null,
        ollutionFreeBase: null,
        landmarkProductBase: null,
        greenProductBase: null,
        organicProductBase: null,
        individualHousehold: null,
        otherBase: null,
        wholesaleMarket: null,
        transportVehicle: null,
        allCount: null,
        createdDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询被抽样环节数量统计列表 */
    getList() {
      this.loading = true;
      listOutSampleStageType(this.queryParams).then(response => {
        this.outSampleStageTypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        sampleQualityId: null,
        samplingStageType: null,
        ollutionFreeBase: null,
        landmarkProductBase: null,
        greenProductBase: null,
        organicProductBase: null,
        individualHousehold: null,
        otherBase: null,
        wholesaleMarket: null,
        transportVehicle: null,
        allCount: null,
        createdDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sampleQualityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加被抽样环节数量统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleQualityId = row.sampleQualityId || this.ids
      getOutSampleStageType(sampleQualityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改被抽样环节数量统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleQualityId != null) {
            updateOutSampleStageType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutSampleStageType(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sampleQualityIds = row.sampleQualityId || this.ids;
      this.$modal.confirm('是否确认删除被抽样环节数量统计编号为"' + sampleQualityIds + '"的数据项？').then(function() {
        return delOutSampleStageType(sampleQualityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      let workSheet = XLSX.utils.table_to_sheet(document.querySelector("#table1"));
      let bookNew = XLSX.utils.book_new();
      let header=[];

      // 在这里添加样式代码
      for (const key in workSheet) {
        if (workSheet[key] instanceof Object) {
          workSheet[key].s = {
            alignment: {
              vertical: 'center',
              horizontal: 'center',
              indent: 0,
              wrapText: true
            },
            font: {
              name: '宋体',
              sz: 10,
              color: { rgb: '#FF000000' },
              bold: false,
              italic: false,
              underline: false
            },
            border: {
              top: { style: 'thin' },
              bottom: { style: 'thin' },
              left: { style: 'thin' },
              right: { style: 'thin' }
            }
          }
        }
      }
      console.log("进入之前");
      let cellAdress={c:0,r:0};
      let cell_ref = XLSX.utils.encode_cell(cellAdress);
      let cell=workSheet[cell_ref].v;
      console.log("打印cell的内容",cell);
      //默认表头合并
      header.push({ s: { r: 0, c: 0 }, e: { r: 1, c: 1 } });//样品来源

      header.push({ s: { r: 0, c: 2 }, e: { r: 1, c: 2 } });//数量

      header.push({ s: { r: 2, c: 0 }, e: { r: 2, c: 1 } });//生产基地、批发市场、运输车、合计
      header.push({ s: { r: 9, c: 0 }, e: { r: 9, c: 1 } });
      header.push({ s: { r: 10, c: 0 }, e: { r: 10, c: 1 } });
      header.push({ s: { r: 11, c: 0 }, e: { r: 11, c: 1 } });

      header.push({ s: { r: 3, c: 0 }, e: { r: 8, c: 0 } });//其中

      workSheet['!merges'] = header;



      for (const key in workSheet) {
        if (workSheet[key] instanceof Object) {
          workSheet[key].s = {
            alignment: {
              vertical: 'center',
              horizontal: 'center',
              indent: 0,
              wrapText: true
            },
            font: {
              name: '宋体',
              sz: 10,
              color: { rgb: '#FF000000' },
              bold: false,
              italic: false,
              underline: false
            },
            border: {
              top: { style: 'thin' },
              bottom: { style: 'thin' },
              left: { style: 'thin' },
              right: { style: 'thin' }
            }
          }
        }
      }
      let name = '被抽样环节数量统计表'
      XLSX.utils.book_append_sheet(bookNew, workSheet, name) // 工作簿名称
      var wopts = {
        bookType: "xlsx", // 要生成的文件类型
        bookSST: false, // 是否生成Shared String Table，官方解释是，如果开启生成速度会下降，但在低版本IOS设备上有更好的兼容性
        type: "binary",
      };
      let wbout = XLSXS.write(bookNew, {
        bookType: 'xlsx',
        bookSST: false,
        type: 'binary',
      })
      // XLSXS.writeFile(bookNew, '水果禁用表', wopts);
      FileSaver.saveAs(
        new Blob([s2ab(wbout)], {
          type: 'application/octet-stream'
        }),
        name+ '.xlsx' // 保存的文件名
      )
      // 工具方法
      function s2ab(s) {
        var buf = new ArrayBuffer(s.length)
        var view = new Uint8Array(buf)
        for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xff
        return buf
      }
    },
    /*表头行的合并*/
    headerStyle({row, column, rowIndex, columnIndex }) {
      // console.log(row, column, rowIndex, columnIndex);
      const comStyle = {
        backgroundColor: "#428fd7",
        color: "#fff",
        fontSize: "500",
      };
      if(rowIndex===1){
        row[0].rowspan=2;
      }
      return {...comStyle}

    },
    /*表头列的合并*/
    spanMethod({ row, column, rowIndex, columnIndex }) {
      if(rowIndex=== 0 || rowIndex=== 7 ||rowIndex=== 8 || rowIndex=== 9){
        if(columnIndex ===1){
          return {rowspan: 1, colspan: 0}
        }
        if(columnIndex ===0){
          return {rowspan: 1, colspan: 2}
        }
      }

      if (rowIndex=== 1)
      {//其中的那一行
        if (columnIndex === 0) {
          return {rowspan: 6, colspan: 1} // 隐藏表头下面第一行的第一列
        }
      }
      if(rowIndex> 1){//”其中“包含的行
        if (columnIndex === 0) {
          return {rowspan: 1, colspan: 0} // 隐藏表头下面第一行的第一列
        }
        if (columnIndex === 1) {
          return {rowspan: 1, colspan: 1} // 将表头下面第一行的第一列和第二列合并
        }
      }
    },
  }
};
</script>
