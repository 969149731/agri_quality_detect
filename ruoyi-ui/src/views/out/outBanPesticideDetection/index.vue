<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检测单位" prop="detectUnit">
        <el-input
          v-model="queryParams.detectUnit"
          placeholder="请输入检测单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品编号" prop="sampleCode">
        <el-input
          v-model="queryParams.sampleCode"
          placeholder="请输入样品编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品名称" prop="vegFruName">
        <el-input
          v-model="queryParams.vegFruName"
          placeholder="请输入样品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抽样地点" prop="samplingLocation">
        <el-input
          v-model="queryParams.samplingLocation"
          placeholder="请输入抽样地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="超标农药品种" prop="exceedPesticideName">
        <el-input
          v-model="queryParams.exceedPesticideName"
          placeholder="请输入超标农药品种"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="超标农药超标值" prop="exceedPesticideValue">
        <el-input
          v-model="queryParams.exceedPesticideValue"
          placeholder="请输入超标农药超标值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="限量值" prop="limitValue">
        <el-input
          v-model="queryParams.limitValue"
          placeholder="请输入限量值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['out:outBanPesticideDetection:add']"
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
          v-hasPermi="['out:outBanPesticideDetection:edit']"
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
          v-hasPermi="['out:outBanPesticideDetection:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outBanPesticideDetection:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outBanPesticideDetectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识符，自增" align="center" prop="banPesticideDetectionId" />
      <el-table-column label="检测单位" align="center" prop="detectUnit" />
      <el-table-column label="样品编号" align="center" prop="sampleCode" />
      <el-table-column label="样品名称" align="center" prop="vegFruName" />
      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />
      <el-table-column label="超标农药品种" align="center" prop="exceedPesticideName" />
      <el-table-column label="超标农药超标值" align="center" prop="exceedPesticideValue" />
      <el-table-column label="限量值" align="center" prop="limitValue" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['out:outBanPesticideDetection:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outBanPesticideDetection:remove']"
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

    <!-- 添加或修改蔬菜水果禁用农药检出样品明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检测单位" prop="detectUnit">
          <el-input v-model="form.detectUnit" placeholder="请输入检测单位" />
        </el-form-item>
        <el-form-item label="样品编号" prop="sampleCode">
          <el-input v-model="form.sampleCode" placeholder="请输入样品编号" />
        </el-form-item>
        <el-form-item label="样品名称" prop="vegFruName">
          <el-input v-model="form.vegFruName" placeholder="请输入样品名称" />
        </el-form-item>
        <el-form-item label="抽样地点" prop="samplingLocation">
          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点" />
        </el-form-item>
        <el-form-item label="超标农药品种" prop="exceedPesticideName">
          <el-input v-model="form.exceedPesticideName" placeholder="请输入超标农药品种" />
        </el-form-item>
        <el-form-item label="超标农药超标值" prop="exceedPesticideValue">
          <el-input v-model="form.exceedPesticideValue" placeholder="请输入超标农药超标值" />
        </el-form-item>
        <el-form-item label="限量值" prop="limitValue">
          <el-input v-model="form.limitValue" placeholder="请输入限量值" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />
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
import { listOutBanPesticideDetection, getOutBanPesticideDetection, delOutBanPesticideDetection, addOutBanPesticideDetection, updateOutBanPesticideDetection } from "@/api/out/outBanPesticideDetection";

export default {
  name: "OutBanPesticideDetection",
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
      // 蔬菜水果禁用农药检出样品明细表格数据
      outBanPesticideDetectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        detectUnit: null,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        exceedPesticideName: null,
        exceedPesticideValue: null,
        limitValue: null,
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
    /** 查询蔬菜水果禁用农药检出样品明细列表 */
    getList() {
      this.loading = true;
      listOutBanPesticideDetection(this.queryParams).then(response => {
        this.outBanPesticideDetectionList = response.rows;
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
        banPesticideDetectionId: null,
        detectUnit: null,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        exceedPesticideName: null,
        exceedPesticideValue: null,
        limitValue: null,
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
      this.ids = selection.map(item => item.banPesticideDetectionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬菜水果禁用农药检出样品明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const banPesticideDetectionId = row.banPesticideDetectionId || this.ids
      getOutBanPesticideDetection(banPesticideDetectionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬菜水果禁用农药检出样品明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.banPesticideDetectionId != null) {
            updateOutBanPesticideDetection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutBanPesticideDetection(this.form).then(response => {
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
      const banPesticideDetectionIds = row.banPesticideDetectionId || this.ids;
      this.$modal.confirm('是否确认删除蔬菜水果禁用农药检出样品明细编号为"' + banPesticideDetectionIds + '"的数据项？').then(function() {
        return delOutBanPesticideDetection(banPesticideDetectionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outBanPesticideDetection/export', {
        ...this.queryParams
      }, `outBanPesticideDetection_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
