<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="抽样年份" prop="year">-->
<!--        <el-input-->
<!--          v-model="queryParams.year"-->
<!--          placeholder="请输入抽样年份"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      <el-form-item label="抽样季度" prop="season">-->
<!--        <el-input-->
<!--          v-model="queryParams.season"-->
<!--          placeholder="请输入抽样季度"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="抽样日期">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>

        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['out:outSampleStageType:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['out:outSampleStageType:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['out:outSampleStageType:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
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
      v-loading="loading"
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
      // 日期范围
      dateRange: [],
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
      listOutSampleStageType(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
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
      this.dateRange=[];
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
      this.download('out/outSampleStageType/export', {
        ...this.queryParams
      }, `outSampleStageTypeRecords_${new Date().getTime()}.xlsx`)
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
