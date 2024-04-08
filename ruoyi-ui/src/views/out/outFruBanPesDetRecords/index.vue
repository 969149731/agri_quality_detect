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
<!--          v-hasPermi="['out:outFruBanPesDetRecords:add']"-->
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
<!--          v-hasPermi="['out:outFruBanPesDetRecords:edit']"-->
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
<!--          v-hasPermi="['out:outFruBanPesDetRecords:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outFruBanPesDetRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      v-if="!peopleTagNumLoading"
      ref="refreshTable"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      :data="StageList"
      style="width: 100%;"
      align='center'
      id="table1"
    >
      <el-table-column label="农药名称" align="center">
        <el-table-column prop="IncludeLable"></el-table-column>
        <el-table-column  align="center" prop="StageName"></el-table-column>
      </el-table-column>

      <el-table-column
        v-for="item in pesticideNameList"
        align="center"
        :label="item.pesticideName"
        :key="item.StageId"
        prop="value"
      >
        <template slot-scope="scope">
          <div>{{ item[scope.row['StageId']] }}</div>
        </template>
      </el-table-column>
    </el-table>
    <!--占用格，界面有点拥挤，美化一下-->

<!--    <el-card   class="bottom-card" shadow="always" :body-style= "{ padding:  '0px'}">-->
<!--        <img src=""  class="bottom-img"/>-->
<!--    </el-card>-->

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listOutFruBanPesDetRecords2,listOutFruBanPesDetRecords, getOutFruBanPesDetRecords, delOutFruBanPesDetRecords, addOutFruBanPesDetRecords, updateOutFruBanPesDetRecords } from "@/api/out/outFruBanPesDetRecords";
import * as XLSX from "xlsx";
import FileSaver from 'file-saver'


export default {
  name: "OutFruBanPesDetRecords",
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
      // 水果禁用农药检出及超标情况表格数据
      outFruBanPesDetRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        methamidophos: null,
        acephate: null,
        phorate: null,
        dimethoate: null,
        isocarbophos: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
        permethrin: null,
        year: '2024', // 设置默认值为 2024
        season:'3',//设置默认值为3
        createdDate: null,
      },
      // 日期范围
      dateRange: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      StageList: [
        {
          IncludeLable:'检出次数',
          StageName: '检出次数',
          StageId: 'totalDet'

        },
        {
          IncludeLable:'超标次数',
          StageName: '超标次数',
          StageId: 'totalEx'
        },
        {
          IncludeLable:"其中",
          StageName: '生产基地检出次数',
          StageId: 'productBase'
        },
        {
          StageName: '生产基地超标次数',
          StageId: 'productBaseEx'
        },
        {
          StageName: '各类市场检出',
          StageId: 'market'
        },
        {
          StageName: '各类市场超标',
          StageId: 'marketEx'
        },
        {
          StageName: '运输车检出',
          StageId: 'vehicle'
        },
        {
          StageName: '运输车超标',
          StageId: 'vehicleEx'
        },
      ],
      pesticideNameList: [],
      text:"",
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询水果禁用农药检出及超标情况列表 */
    getList() {
      this.loading = true;
      listOutFruBanPesDetRecords2(this.addDateRange(this.queryParams, this.dateRange)).then(response => {//二维表使用的列表获取
        this.pesticideNameList = response.rows;
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
        fruBanPesDetRecordsId: null,
        pesticideName: null,
        methamidophos: null,
        acephate: null,
        phorate: null,
        dimethoate: null,
        isocarbophos: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
        permethrin: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.fruBanPesDetRecordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加水果禁用农药检出及超标情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fruBanPesDetRecordsId = row.fruBanPesDetRecordsId || this.ids
      getOutFruBanPesDetRecords(fruBanPesDetRecordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改水果禁用农药检出及超标情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fruBanPesDetRecordsId != null) {
            updateOutFruBanPesDetRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutFruBanPesDetRecords(this.form).then(response => {
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
      const fruBanPesDetRecordsIds = row.fruBanPesDetRecordsId || this.ids;
      this.$modal.confirm('是否确认删除水果禁用农药检出及超标情况编号为"' + fruBanPesDetRecordsIds + '"的数据项？').then(function() {
        return delOutFruBanPesDetRecords(fruBanPesDetRecordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outFruBanPesDetRecords/export', {
        ...this.queryParams
      }, `outFruBanPesDetRecords_${new Date().getTime()}.xlsx`)
    },
    /*表头行的合并*/
    headerStyle({ row, column, rowIndex, columnIndex }) {
      const comStyle = {
        backgroundColor: "#3D589B",
        color: "#fff",
        fontSize: "500",
      };
      if(rowIndex===0){
        row[0].rowspan=2;
      }
      if(rowIndex===1) {
        if (columnIndex === 0 || columnIndex === 1) { // 将表头第一列隐藏
          return {
            display: "none",
            ...comStyle,
          };
        }
      }
      return comStyle;
    },
    /*表头列的合并*/
    spanMethod({ row, column, rowIndex, columnIndex }) {
      if(rowIndex=== 0 || rowIndex=== 1){
        if(columnIndex ===1){
          return {rowspan: 1, colspan: 0}
        }
        if(columnIndex ===0){
          return {rowspan: 1, colspan: 2}
        }
      }

      if (rowIndex=== 2)
      {//其中的那一行
        if (columnIndex === 0) {
          return {rowspan: 6, colspan: 1} // 隐藏表头下面第一行的第一列
        }
      }
      if(rowIndex> 2){//”其中“包含的行
        if (columnIndex === 0) {
          return {rowspan: 1, colspan: 0} // 隐藏表头下面第一行的第一列
        }
        if (columnIndex === 1) {
          return {rowspan: 1, colspan: 1} // 将表头下面第一行的第一列和第二列合并
        }
      }
    },

    test(){

    },
  }
};
</script>
<style scoped>
.bottom-card{
  position: relative;/*配合下面的absolute使用*/
  width: 100%;
  margin-top: 20px;
  opacity: 100%;
  height: 40px;

  background-color: #3D589B;
}

.bottom-img{
  position: absolute;
  top: -150px;
  width: 100%;
}
</style>
