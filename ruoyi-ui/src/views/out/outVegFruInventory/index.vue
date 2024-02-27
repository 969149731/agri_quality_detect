<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="蔬菜类序号，用于标识蔬菜类别" prop="vegetableSeqNo">-->
<!--        <el-input-->
<!--          v-model="queryParams.vegetableSeqNo"-->
<!--          placeholder="请输入蔬菜类序号，用于标识蔬菜类别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="蔬菜类详细名" prop="vegetableDetailName">-->
<!--        <el-input-->
<!--          v-model="queryParams.vegetableDetailName"-->
<!--          placeholder="请输入蔬菜类详细名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="蔬菜类数量" prop="vegetableQuantity">-->
<!--        <el-input-->
<!--          v-model="queryParams.vegetableQuantity"-->
<!--          placeholder="请输入蔬菜类数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="水果类序号，用于标识水果类别" prop="fruitSeqNo">-->
<!--        <el-input-->
<!--          v-model="queryParams.fruitSeqNo"-->
<!--          placeholder="请输入水果类序号，用于标识水果类别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="水果类详细名" prop="fruitDetailName">-->
<!--        <el-input-->
<!--          v-model="queryParams.fruitDetailName"-->
<!--          placeholder="请输入水果类详细名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="水果类数量" prop="fruitQuantity">-->
<!--        <el-input-->
<!--          v-model="queryParams.fruitQuantity"-->
<!--          placeholder="请输入水果类数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
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
          v-hasPermi="['out:outVegFruInventory:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['out:outVegFruInventory:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['out:outVegFruInventory:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outVegFruInventory:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    <el-table v-loading="loading" :data="filteredVegFruInventoryListFilteFru" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center"/>-->
<!--      <el-table-column label="种类" align="center" prop="name"/>-->
<!--      <el-table-column label="蔬果类型" align="center" prop="type"/>-->
<!--      <el-table-column label="数量" align="center" prop="quantity"/>-->
<!--    </el-table>-->

    <el-row>
      <el-col :span="12">

        <el-table v-loading="loading" :data="filteredVegFruInventoryListFilteVeg"
                  @selection-change="handleSelectionChange">
<!--          <el-table-column type="selection" width="55" align="center"/>-->
          <el-table-column type="index" label="序号" width="200" align="center"/> <!-- 添加序号列 -->
          <el-table-column label="水果种类" align="center" width="200" prop="name"/>
<!--          <el-table-column label="蔬果类型" align="center" prop="type"/>-->
          <el-table-column label="数量" align="center" width="200" prop="quantity"/>
        </el-table>
      </el-col>

      <el-col :span="12">
        <el-table v-loading="loading" :data="filteredVegFruInventoryListFilteFru" @selection-change="handleSelectionChange">
<!--          <el-table-column type="selection" width="55" align="center"/>-->
          <el-table-column type="index" label="序号" width="200" align="center"/> <!-- 添加序号列 -->
          <el-table-column label="蔬菜种类" align="center" width="200" prop="name"/>
<!--          <el-table-column label="蔬果类型" align="center" prop="type"/>-->
          <el-table-column label="数量" align="center" width="200" prop="quantity"/>
        </el-table>
      </el-col>

    </el-row>


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
import {
  listOutVegFruInventory,
  getOutVegFruInventory,
  delOutVegFruInventory,
  addOutVegFruInventory,
  updateOutVegFruInventory
} from "@/api/out/outVegFruInventory";

export default {
  name: "OutVegFruInventory",
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
      // 蔬菜水果种类及数量表格数据
      outVegFruInventoryList: [],

      allList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vegetableSeqNo: null,
        vegetableDetailName: null,
        vegetableQuantity: null,
        fruitSeqNo: null,
        fruitDetailName: null,
        fruitQuantity: null,
        createdDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  computed: {
    filteredVegFruInventoryListFilteFru() {
      return this.outVegFruInventoryList.filter(item => item.type === '蔬菜');
    },
    filteredVegFruInventoryListFilteVeg() {
      return this.outVegFruInventoryList.filter(item => item.type === '水果');
    }

  },


  created() {
    this.getList();
  },
  methods: {
    /** 查询蔬菜水果种类及数量列表 */
    getList() {
      this.loading = true;
      listOutVegFruInventory(this.queryParams).then(response => {
        this.outVegFruInventoryList = response.rows;
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
        vegFruInventoryId: null,
        vegetableSeqNo: null,
        vegetableDetailName: null,
        vegetableQuantity: null,
        fruitSeqNo: null,
        fruitDetailName: null,
        fruitQuantity: null,
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
      this.ids = selection.map(item => item.vegFruInventoryId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬菜水果种类及数量";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vegFruInventoryId = row.vegFruInventoryId || this.ids
      getOutVegFruInventory(vegFruInventoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬菜水果种类及数量";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vegFruInventoryId != null) {
            updateOutVegFruInventory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutVegFruInventory(this.form).then(response => {
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
      const vegFruInventoryIds = row.vegFruInventoryId || this.ids;
      this.$modal.confirm('是否确认删除蔬菜水果种类及数量编号为"' + vegFruInventoryIds + '"的数据项？').then(function () {
        return delOutVegFruInventory(vegFruInventoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outVegFruInventory/export', {
        ...this.queryParams
      }, `outVegFruInventory_${new Date().getTime()}.xlsx`)
    }


  }
};
</script>
