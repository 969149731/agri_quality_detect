<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="抽样地点" prop="samplingLocation">-->
<!--        <el-input-->
<!--          v-model="queryParams.samplingLocation"-->
<!--          placeholder="请输入抽样地点"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="检测单位" prop="detectLocation">-->
<!--        <el-input-->
<!--          v-model="queryParams.detectLocation"-->
<!--          placeholder="请输入检测单位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="品种，蔬果具体名称" prop="vegFruName">-->
<!--        <el-input-->
<!--          v-model="queryParams.vegFruName"-->
<!--          placeholder="请输入品种，蔬果具体名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="抽样总数" prop="totalSamples">-->
<!--        <el-input-->
<!--          v-model="queryParams.totalSamples"-->
<!--          placeholder="请输入抽样总数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="合格数" prop="qualifiedNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.qualifiedNumber"-->
<!--          placeholder="请输入合格数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="合格率" prop="qualificationRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.qualificationRate"-->
<!--          placeholder="请输入合格率"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="禁用农药检出" prop="prohibitedPesticideDetection">-->
<!--        <el-input-->
<!--          v-model="queryParams.prohibitedPesticideDetection"-->
<!--          placeholder="请输入禁用农药检出"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="常规农药超标" prop="routinePesticideExceedance">-->
<!--        <el-input-->
<!--          v-model="queryParams.routinePesticideExceedance"-->
<!--          placeholder="请输入常规农药超标"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="记录创建的时间" prop="createdDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.createdDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择记录创建的时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['out:outHighRiskVarietyDet:add']"-->
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
<!--          v-hasPermi="['out:outHighRiskVarietyDet:edit']"-->
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
<!--          v-hasPermi="['out:outHighRiskVarietyDet:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outHighRiskVarietyDet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    <el-table v-loading="loading" :data="outHighRiskVarietyDetList" @selection-change="handleSelectionChange">-->
<!--&lt;!&ndash;      <el-table-column type="selection" width="55" align="center" />&ndash;&gt;-->
<!--&lt;!&ndash;      <el-table-column label="唯一标识符，自增" align="center" prop="outHighRiskVarietyDetId" />&ndash;&gt;-->
<!--      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />-->
<!--      <el-table-column label="检测单位" align="center" prop="detectLocation" />-->
<!--      <el-table-column label="品种，蔬果具体名称" align="center" prop="vegFruName" />-->
<!--      <el-table-column label="抽样总数" align="center" prop="totalSamples" />-->
<!--      <el-table-column label="合格数" align="center" prop="qualifiedNumber" />-->
<!--      <el-table-column label="合格率" align="center" prop="qualificationRate" />-->
<!--      <el-table-column label="禁用农药检出" align="center" prop="prohibitedPesticideDetection" />-->
<!--      <el-table-column label="常规农药超标" align="center" prop="routinePesticideExceedance" />-->
<!--      <el-table-column label="备注" align="center" prop="remarks" />-->
<!--&lt;!&ndash;      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">&ndash;&gt;-->
<!--&lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--&lt;!&ndash;          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>&ndash;&gt;-->
<!--&lt;!&ndash;        </template>&ndash;&gt;-->
<!--&lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--&lt;!&ndash;      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">&ndash;&gt;-->
<!--&lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button&ndash;&gt;-->
<!--&lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;            type="text"&ndash;&gt;-->
<!--&lt;!&ndash;            icon="el-icon-edit"&ndash;&gt;-->
<!--&lt;!&ndash;            @click="handleUpdate(scope.row)"&ndash;&gt;-->
<!--&lt;!&ndash;            v-hasPermi="['out:outHighRiskVarietyDet:edit']"&ndash;&gt;-->
<!--&lt;!&ndash;          >修改</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button&ndash;&gt;-->
<!--&lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;            type="text"&ndash;&gt;-->
<!--&lt;!&ndash;            icon="el-icon-delete"&ndash;&gt;-->
<!--&lt;!&ndash;            @click="handleDelete(scope.row)"&ndash;&gt;-->
<!--&lt;!&ndash;            v-hasPermi="['out:outHighRiskVarietyDet:remove']"&ndash;&gt;-->
<!--&lt;!&ndash;          >删除</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;        </template>&ndash;&gt;-->
<!--&lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--    </el-table>-->



<!--    <el-table v-loading="loading" :data="outHighRiskVarietyDetList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />-->
<!--      <el-table-column label="检测单位" align="center" prop="detectLocation" />-->
<!--      <el-table-column label="品种，蔬果具体名称" align="center" prop="vegFruName" />-->
<!--      <el-table-column label="抽样总数" align="center" prop="totalSamples" />-->
<!--      <el-table-column label="合格数" align="center" prop="qualifiedNumber" />-->
<!--      <el-table-column label="合格率" align="center" prop="qualificationRate" />-->
<!--      <el-table-column label="禁用农药检出" align="center" prop="prohibitedPesticideDetection" />-->
<!--      <el-table-column label="常规农药超标" align="center" prop="routinePesticideExceedance" />-->
<!--      <el-table-column label="备注" align="center" prop="remarks" />-->
<!--    </el-table>-->

    <template>
      <el-table
        v-loading="loading"
        :data="outHighRiskVarietyDetList"
        @selection-change="handleSelectionChange"
        :span-method="mergeCells"
      >
        <el-table-column label="抽样地点" align="center" prop="samplingLocation" />
        <el-table-column label="检测单位" align="center" prop="detectLocation" />
        <el-table-column label="品种，蔬果具体名称" align="center" prop="vegFruName" />
        <el-table-column label="抽样总数" align="center" prop="totalSamples" />
        <el-table-column label="合格数" align="center" prop="qualifiedNumber" />
        <el-table-column label="合格率" align="center" prop="qualificationRate" />
        <el-table-column label="禁用农药检出" align="center" prop="prohibitedPesticideDetection" />
        <el-table-column label="常规农药超标" align="center" prop="routinePesticideExceedance" />
<!--        <el-table-column label="备注" align="center" prop="remarks" />-->
      </el-table>
    </template>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改高风险品种样品检出情况对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="抽样地点" prop="samplingLocation">-->
<!--          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="检测单位" prop="detectLocation">-->
<!--          <el-input v-model="form.detectLocation" placeholder="请输入检测单位" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="品种，蔬果具体名称" prop="vegFruName">-->
<!--          <el-input v-model="form.vegFruName" placeholder="请输入品种，蔬果具体名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="抽样总数" prop="totalSamples">-->
<!--          <el-input v-model="form.totalSamples" placeholder="请输入抽样总数" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="合格数" prop="qualifiedNumber">-->
<!--          <el-input v-model="form.qualifiedNumber" placeholder="请输入合格数" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="合格率" prop="qualificationRate">-->
<!--          <el-input v-model="form.qualificationRate" placeholder="请输入合格率" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="禁用农药检出" prop="prohibitedPesticideDetection">-->
<!--          <el-input v-model="form.prohibitedPesticideDetection" placeholder="请输入禁用农药检出" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="常规农药超标" prop="routinePesticideExceedance">-->
<!--          <el-input v-model="form.routinePesticideExceedance" placeholder="请输入常规农药超标" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注" prop="remarks">-->
<!--          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="记录创建的时间" prop="createdDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择记录创建的时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listOutHighRiskVarietyDet, getOutHighRiskVarietyDet, delOutHighRiskVarietyDet, addOutHighRiskVarietyDet, updateOutHighRiskVarietyDet } from "@/api/out/outHighRiskVarietyDet";

export default {
  name: "OutHighRiskVarietyDet",
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
      // 高风险品种样品检出情况表格数据
      outHighRiskVarietyDetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        samplingLocation: null,
        detectLocation: null,
        vegFruName: null,
        totalSamples: null,
        qualifiedNumber: null,
        qualificationRate: null,
        prohibitedPesticideDetection: null,
        routinePesticideExceedance: null,
        remarks: null,
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
    /** 查询高风险品种样品检出情况列表 */
    getList() {
      this.loading = true;
      listOutHighRiskVarietyDet(this.queryParams).then(response => {
        this.outHighRiskVarietyDetList = response.rows;
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
        outHighRiskVarietyDetId: null,
        samplingLocation: null,
        detectLocation: null,
        vegFruName: null,
        totalSamples: null,
        qualifiedNumber: null,
        qualificationRate: null,
        prohibitedPesticideDetection: null,
        routinePesticideExceedance: null,
        remarks: null,
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
      this.ids = selection.map(item => item.outHighRiskVarietyDetId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加高风险品种样品检出情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const outHighRiskVarietyDetId = row.outHighRiskVarietyDetId || this.ids
      getOutHighRiskVarietyDet(outHighRiskVarietyDetId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改高风险品种样品检出情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.outHighRiskVarietyDetId != null) {
            updateOutHighRiskVarietyDet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutHighRiskVarietyDet(this.form).then(response => {
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
      const outHighRiskVarietyDetIds = row.outHighRiskVarietyDetId || this.ids;
      this.$modal.confirm('是否确认删除高风险品种样品检出情况编号为"' + outHighRiskVarietyDetIds + '"的数据项？').then(function() {
        return delOutHighRiskVarietyDet(outHighRiskVarietyDetIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outHighRiskVarietyDet/export', {
        ...this.queryParams
      }, `outHighRiskVarietyDet_${new Date().getTime()}.xlsx`)
    },

    mergeCells({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0 || columnIndex === 1) { // 只合并抽样地点和检测单位列
        if (rowIndex > 0) {
          const prevRow = this.outHighRiskVarietyDetList[rowIndex - 1];
          if (row.samplingLocation === prevRow.samplingLocation && row.detectLocation === prevRow.detectLocation) {
            return [0, 0]; // 返回 [0, 0] 表示不显示当前单元格
          }
        }
        let rowspan = 1;
        let colspan = 1;
        for (let i = rowIndex + 1; i < this.outHighRiskVarietyDetList.length; i++) {
          const nextRow = this.outHighRiskVarietyDetList[i];
          if (row.samplingLocation === nextRow.samplingLocation && row.detectLocation === nextRow.detectLocation) {
            rowspan++; // 增加合并的行数
          } else {
            break;
          }
        }
        return [rowspan, colspan];
      }
      return [1, 1]; // 其他列不合并，每个单元格占用一行一列
    },
  }
};
</script>
