<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="农药名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入农药名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="检测标准" prop="standard">-->
<!--        <el-input-->
<!--          v-model="queryParams.standard"-->
<!--          placeholder="请输入检测标准"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="蔬菜农药是否禁用" prop="vegPetPermit" label-width="30">
        <el-select v-model="queryParams.vegPetPermit" placeholder="请选择蔬菜农药是否禁用" clearable>
          <el-option
            v-for="dict in dict.type.veg_pet_permit"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="水果农药是否禁用" prop="fruPetPermit"  label-width="30">
        <el-select v-model="queryParams.fruPetPermit" placeholder="请选择水果农药是否禁用" clearable>
          <el-option
            v-for="dict in dict.type.fru_pet_permit"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['agriDictionary:agriPesticideDictionary:add']"
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
          v-hasPermi="['agriDictionary:agriPesticideDictionary:edit']"
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
          v-hasPermi="['agriDictionary:agriPesticideDictionary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['agriDictionary:agriPesticideDictionary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agriPesticideDictionaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键，自增长" align="center" prop="pesticideId" />-->
      <el-table-column label="农药名称" align="center" prop="pesticideName" />
<!--      <el-table-column label="检测标准" align="center" prop="standard" />-->
      <el-table-column label="蔬菜农药是否禁用" align="center" prop="vegPetPermit">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.veg_pet_permit" :value="scope.row.vegPetPermit"/>
        </template>
      </el-table-column>
      <el-table-column label="水果农药是否禁用" align="center" prop="fruPetPermit">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fru_pet_permit" :value="scope.row.fruPetPermit"/>
        </template>
      </el-table-column>
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
            v-hasPermi="['agriDictionary:agriPesticideDictionary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['agriDictionary:agriPesticideDictionary:remove']"
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

    <!-- 添加或修改农药字典对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农药名称" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入农药名称" />
        </el-form-item>
<!--        <el-form-item label="检测标准" prop="standard">-->
<!--          <el-input v-model="form.standard" placeholder="请输入检测标准" />-->
<!--        </el-form-item>-->
        <el-form-item label="蔬菜农药是否禁用" prop="vegPetPermit">
          <el-select v-model="form.vegPetPermit" placeholder="请选择蔬菜农药是否禁用">
            <el-option
              v-for="dict in dict.type.veg_pet_permit"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="水果农药是否禁用" prop="fruPetPermit">
          <el-select v-model="form.fruPetPermit" placeholder="请选择水果农药是否禁用">
            <el-option
              v-for="dict in dict.type.fru_pet_permit"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listAgriPesticideDictionary, getAgriPesticideDictionary, delAgriPesticideDictionary, addAgriPesticideDictionary, updateAgriPesticideDictionary } from "@/api/agriDictionary/agriPesticideDictionary";

export default {
  name: "AgriPesticideDictionary",
  dicts: ['fru_pet_permit', 'veg_pet_permit'],
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
      // 农药字典表格数据
      agriPesticideDictionaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        standard: null,
        vegPetPermit: null,
        fruPetPermit: null,
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
    /** 查询农药字典列表 */
    getList() {
      this.loading = true;
      listAgriPesticideDictionary(this.queryParams).then(response => {
        this.agriPesticideDictionaryList = response.rows;
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
        pesticideId: null,
        pesticideName: null,
        standard: null,
        vegPetPermit: null,
        fruPetPermit: null,
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
      this.ids = selection.map(item => item.pesticideId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加农药字典";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pesticideId = row.pesticideId || this.ids
      getAgriPesticideDictionary(pesticideId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农药字典";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pesticideId != null) {
            updateAgriPesticideDictionary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAgriPesticideDictionary(this.form).then(response => {
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
      const pesticideIds = row.pesticideId || this.ids;
      this.$modal.confirm('是否确认删除农药字典编号为"' + pesticideIds + '"的数据项？').then(function() {
        return delAgriPesticideDictionary(pesticideIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('agriDictionary/agriPesticideDictionary/export', {
        ...this.queryParams
      }, `agriPesticideDictionary_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
