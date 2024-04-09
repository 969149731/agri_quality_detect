<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

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
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outTeaBanPesDetRecords:export']"
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
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改茶叶禁用农药检出及超标情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listOutTeaBanPesDetRecords, getOutTeaBanPesDetRecords, delOutTeaBanPesDetRecords, addOutTeaBanPesDetRecords, updateOutTeaBanPesDetRecords } from "@/api/out/outTeaBanPesDetRecords";

export default {
  name: "OutTeaBanPesDetRecords",
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
        chlorpyrifos: null,
        fenthion: null,
        triazophos: null,
        isocarbophos: null,
        trichlorfon: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
        createdDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //界面表格行表头，如生产基地
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
      // 茶叶禁用农药检出及超标情况表格数据
      pesticideNameList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询茶叶禁用农药检出及超标情况列表 */
    getList() {
      this.loading = true;
      listOutTeaBanPesDetRecords(this.queryParams).then(response => {
        this.pesticideNameList = response.rows;
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
        teaBanPesDetRecordsId: null,
        pesticideName: null,
        methamidophos: null,
        acephate: null,
        phorate: null,
        dimethoate: null,
        chlorpyrifos: null,
        fenthion: null,
        triazophos: null,
        isocarbophos: null,
        trichlorfon: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.teaBanPesDetRecordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加茶叶禁用农药检出及超标情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teaBanPesDetRecordsId = row.teaBanPesDetRecordsId || this.ids
      getOutTeaBanPesDetRecords(teaBanPesDetRecordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改茶叶禁用农药检出及超标情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teaBanPesDetRecordsId != null) {
            updateOutTeaBanPesDetRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutTeaBanPesDetRecords(this.form).then(response => {
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
      const teaBanPesDetRecordsIds = row.teaBanPesDetRecordsId || this.ids;
      this.$modal.confirm('是否确认删除茶叶禁用农药检出及超标情况编号为"' + teaBanPesDetRecordsIds + '"的数据项？').then(function() {
        return delOutTeaBanPesDetRecords(teaBanPesDetRecordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outTeaBanPesDetRecords/export', {
        ...this.queryParams
      }, `outTeaBanPesDetRecords_${new Date().getTime()}.xlsx`)
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
  }
};
</script>
