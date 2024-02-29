<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检测单位" prop="detectLocation">
        <el-input
          v-model="queryParams.detectLocation"
          placeholder="请输入检测单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="抽样日期" prop="samplingDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.samplingDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择抽样日期">-->
<!--        </el-date-picker>-->
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

<!--      <el-form-item label="抽样年份" prop="samplingYear">-->
<!--        <el-input-->
<!--          v-model="queryParams.samplingYear"-->
<!--          placeholder="请输入抽样年份"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="抽样月份" prop="samplingMonth">-->
<!--        <el-input-->
<!--          v-model="queryParams.samplingMonth"-->
<!--          placeholder="请输入抽样月份"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="抽样数" prop="samplingCount">
        <el-input
          v-model="queryParams.samplingCount"
          placeholder="请输入抽样数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合格数" prop="qualifiedCount">
        <el-input
          v-model="queryParams.qualifiedCount"
          placeholder="请输入合格数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合格率" prop="passRate">
        <el-input
          v-model="queryParams.passRate"
          placeholder="请输入合格率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="记录创建的时间" prop="createdDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.createdDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择记录创建的时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
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
          v-hasPermi="['out:outDxDetectRecords:add']"
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
          v-hasPermi="['out:outDxDetectRecords:edit']"
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
          v-hasPermi="['out:outDxDetectRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outDxDetectRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outDxDetectRecordsList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="唯一标识符，自增" align="center" prop="recordDxId" />-->
      <el-table-column type="index" label="序号" width="55" align="center" />
      <el-table-column label="检测单位" align="center" prop="detectLocation" />
      <el-table-column label="抽样日期" align="center" prop="samplingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.samplingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="抽样年份" align="center" prop="samplingYear" />-->
<!--      <el-table-column label="抽样月份" align="center" prop="samplingMonth" />-->
      <el-table-column label="抽样数" align="center" prop="samplingCount" />
      <el-table-column label="合格数" align="center" prop="qualifiedCount" />
      <el-table-column label="合格率" align="center" prop="passRate" />
<!--      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['out:outDxDetectRecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outDxDetectRecords:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改定性监测结果汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检测单位" prop="detectLocation">
          <el-input v-model="form.detectLocation" placeholder="请输入检测单位" />
        </el-form-item>
        <el-form-item label="抽样日期" prop="samplingDate">
          <el-date-picker clearable
            v-model="form.samplingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择抽样日期">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="抽样年份" prop="samplingYear">-->
<!--          <el-input v-model="form.samplingYear" placeholder="请输入抽样年份" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="抽样月份" prop="samplingMonth">-->
<!--          <el-input v-model="form.samplingMonth" placeholder="请输入抽样月份" />-->
<!--        </el-form-item>-->
        <el-form-item label="抽样数" prop="samplingCount">
          <el-input v-model="form.samplingCount" placeholder="请输入抽样数" />
        </el-form-item>
        <el-form-item label="合格数" prop="qualifiedCount">
          <el-input v-model="form.qualifiedCount" placeholder="请输入合格数" />
        </el-form-item>
        <el-form-item label="合格率" prop="passRate">
          <el-input v-model="form.passRate" placeholder="请输入合格率" />
        </el-form-item>
<!--        <el-form-item label="记录创建的时间" prop="createdDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择记录创建的时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOutDxDetectRecords, getOutDxDetectRecords, delOutDxDetectRecords, addOutDxDetectRecords, updateOutDxDetectRecords } from "@/api/out/outDxDetectRecords";
import {listOutDlDetectRecords} from "@/api/out/outDlDetectRecords";

export default {
  name: "OutDxDetectRecords",
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
      // 定性监测结果汇总表格数据
      outDxDetectRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        detectLocation: null,
        samplingDate: null,
        samplingYear: null,
        samplingMonth: null,
        samplingCount: null,
        qualifiedCount: null,
        passRate: null,
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
    /** 查询定性监测结果汇总列表 */
    getList() {
      this.loading = true;
      // listOutDxDetectRecords(this.queryParams).then(response => {
      listOutDxDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.outDxDetectRecordsList = response.rows;
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
        recordDxId: null,
        detectLocation: null,
        samplingDate: null,
        samplingYear: null,
        samplingMonth: null,
        samplingCount: null,
        qualifiedCount: null,
        passRate: null,
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
      this.ids = selection.map(item => item.recordDxId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加定性监测结果汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordDxId = row.recordDxId || this.ids
      getOutDxDetectRecords(recordDxId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改定性监测结果汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordDxId != null) {
            updateOutDxDetectRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutDxDetectRecords(this.form).then(response => {
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
      const recordDxIds = row.recordDxId || this.ids;
      this.$modal.confirm('是否确认删除定性监测结果汇总编号为"' + recordDxIds + '"的数据项？').then(function() {
        return delOutDxDetectRecords(recordDxIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outDxDetectRecords/export', {
        ...this.queryParams
      }, `outDxDetectRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
