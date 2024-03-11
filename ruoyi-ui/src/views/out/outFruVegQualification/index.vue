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
          v-hasPermi="['out:outFruVegQualification:add']"
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
          v-hasPermi="['out:outFruVegQualification:edit']"
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
          v-hasPermi="['out:outFruVegQualification:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outFruVegQualification:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outFruVegQualificationList" id="table1">
      <el-table-column label="蔬菜水果种类" prop="vegFruType" align="center"/>
      <el-table-column label="抽样数" prop="samplingNumber" align="center"/>
      <el-table-column label="合格数" prop="passNumber" align="center"/>
      <el-table-column label="合格率" prop="passRate" align="center"/>
      <el-table-column label="超标样品" prop="exceedingSamples" align="center"/>
      <el-table-column label="超标农药" prop="exceedingPesticides" align="center"/>
    </el-table>


    <!-- 添加或修改各类蔬菜水果合格率情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="抽样的总数" prop="samplingNumber">
          <el-input v-model="form.samplingNumber" placeholder="请输入抽样的总数" />
        </el-form-item>
        <el-form-item label="合格的样本数" prop="passNumber">
          <el-input v-model="form.passNumber" placeholder="请输入合格的样本数" />
        </el-form-item>
        <el-form-item label="总合格率，百分比" prop="passRate">
          <el-input v-model="form.passRate" placeholder="请输入总合格率，百分比" />
        </el-form-item>
        <el-form-item label="超标的样品名称" prop="exceedingSamples">
          <el-input v-model="form.exceedingSamples" placeholder="请输入超标的样品名称" />
        </el-form-item>
        <el-form-item label="超标的农药名称" prop="exceedingPesticides">
          <el-input v-model="form.exceedingPesticides" placeholder="请输入超标的农药名称" />
        </el-form-item>
        <el-form-item label="记录创建的时间" prop="createdDate">
          <el-date-picker clearable
            v-model="form.createdDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录创建的时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOutFruVegQualification, getOutFruVegQualification, delOutFruVegQualification, addOutFruVegQualification, updateOutFruVegQualification } from "@/api/out/outFruVegQualification";
import * as XLSX from "xlsx";
import * as XLSXS from "xlsx-style";
import FileSaver from 'file-saver'
export default {
  name: "OutFruVegQualification",
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
      // 各类蔬菜水果合格率情况表格数据
      outFruVegQualificationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vegFruType: null,
        samplingNumber: null,
        passNumber: null,
        passRate: null,
        exceedingSamples: null,
        exceedingPesticides: null,
        createdDate: null
      },
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
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询各类蔬菜水果合格率情况列表 */
    getList() {
      this.loading = true;
      listOutFruVegQualification(this.queryParams).then(response => {
        this.outFruVegQualificationList = response.rows;
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
        fruVegQualificationId: null,
        vegFruType: null,
        samplingNumber: null,
        passNumber: null,
        passRate: null,
        exceedingSamples: null,
        exceedingPesticides: null,
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
      this.ids = selection.map(item => item.fruVegQualificationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加各类蔬菜水果合格率情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fruVegQualificationId = row.fruVegQualificationId || this.ids
      getOutFruVegQualification(fruVegQualificationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改各类蔬菜水果合格率情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fruVegQualificationId != null) {
            updateOutFruVegQualification(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutFruVegQualification(this.form).then(response => {
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
      const fruVegQualificationIds = row.fruVegQualificationId || this.ids;
      this.$modal.confirm('是否确认删除各类蔬菜水果合格率情况编号为"' + fruVegQualificationIds + '"的数据项？').then(function() {
        return delOutFruVegQualification(fruVegQualificationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outFruVegQualification/export', {
        ...this.queryParams
      }, `outFruVegQualificationDetRecords_${new Date().getTime()}.xlsx`)
    },
    /*表头行的合并*/
    headerStyle({ row, column, rowIndex, columnIndex }) {
      const comStyle = {
        backgroundColor: "#428fd7",
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
<style scoped lang="scss">

//.el-table .cell {
//  white-space: pre-wrap;   /*这是重点。文本换行*/
//}

::v-deep .el-table .cell {
  white-space: pre-line;
}

</style>
