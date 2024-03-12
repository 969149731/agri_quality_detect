<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="农药名" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入农药名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蔬菜或水果名" prop="vegFruName" label-width="30">
        <el-input
          v-model="queryParams.vegFruName"
          placeholder="请输入蔬菜或水果名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标准分类" prop="standardCategory">
        <el-select v-model="queryParams.standardCategory" placeholder="请选择标准分类" clearable>
          <el-option
            v-for="dict in dict.type.standard_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="标准值（mg/kg）" prop="standardValue" label-width="30">
        <el-input
          v-model="queryParams.standardValue"
          placeholder="请输入标准值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="记录创建时间" prop="createdAt">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.createdAt"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择记录创建时间">-->
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
          v-hasPermi="['agriDictionary:agriPesResStandDictionary:add']"
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
          v-hasPermi="['agriDictionary:agriPesResStandDictionary:edit']"
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
          v-hasPermi="['agriDictionary:agriPesResStandDictionary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['agriDictionary:agriPesResStandDictionary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agriPesResStandDictionaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键，自增长" align="center" prop="pesticideResidueStandardId" />-->
      <el-table-column label="农药名" align="center" prop="pesticideName" />
      <el-table-column label="蔬菜或水果名" align="center" prop="vegFruName" />
      <el-table-column label="标准分类" align="center" prop="standardCategory">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.standard_category" :value="scope.row.standardCategory"/>
        </template>
      </el-table-column>
      <el-table-column label="标准值（mg/kg）" align="center" prop="standardValue" />
<!--      <el-table-column label="记录创建时间" align="center" prop="createdAt" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['agriDictionary:agriPesResStandDictionary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['agriDictionary:agriPesResStandDictionary:remove']"
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

    <!-- 添加或修改农药最大残留限量参考标准对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农药名" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入农药名" />
        </el-form-item>
        <el-form-item label="蔬菜或水果名" prop="vegFruName">
          <el-input v-model="form.vegFruName" placeholder="请输入蔬菜或水果名" />
        </el-form-item>
        <el-form-item label="标准分类" prop="standardCategory">
          <el-select v-model="form.standardCategory" placeholder="请选择标准分类">
            <el-option
              v-for="dict in dict.type.standard_category"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标准值（mg/kg）" prop="standardValue">
          <el-input v-model="form.standardValue" placeholder="请输入标准值" />
        </el-form-item>
<!--        <el-form-item label="记录创建时间" prop="createdAt">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdAt"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择记录创建时间">-->
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
import { listAgriPesResStandDictionary, getAgriPesResStandDictionary, delAgriPesResStandDictionary, addAgriPesResStandDictionary, updateAgriPesResStandDictionary } from "@/api/agriDictionary/agriPesResStandDictionary";

export default {
  name: "AgriPesResStandDictionary",
  dicts: ['standard_category'],
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
      // 农药最大残留限量参考标准表格数据
      agriPesResStandDictionaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        vegFruName: null,
        standardCategory: null,
        standardValue: null,
        createdAt: null
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
    /** 查询农药最大残留限量参考标准列表 */
    getList() {
      this.loading = true;
      listAgriPesResStandDictionary(this.queryParams).then(response => {
        this.agriPesResStandDictionaryList = response.rows;
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
        pesticideResidueStandardId: null,
        pesticideName: null,
        vegFruName: null,
        standardCategory: null,
        standardValue: null,
        createdAt: null
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
      this.ids = selection.map(item => item.pesticideResidueStandardId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加农药最大残留限量参考标准";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pesticideResidueStandardId = row.pesticideResidueStandardId || this.ids
      getAgriPesResStandDictionary(pesticideResidueStandardId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农药最大残留限量参考标准";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pesticideResidueStandardId != null) {
            updateAgriPesResStandDictionary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAgriPesResStandDictionary(this.form).then(response => {
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
      const pesticideResidueStandardIds = row.pesticideResidueStandardId || this.ids;
      this.$modal.confirm('是否确认删除农药最大残留限量参考标准编号为"' + pesticideResidueStandardIds + '"的数据项？').then(function() {
        return delAgriPesResStandDictionary(pesticideResidueStandardIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('agriDictionary/agriPesResStandDictionary/export', {
        ...this.queryParams
      }, `agriPesResStandDictionary_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
