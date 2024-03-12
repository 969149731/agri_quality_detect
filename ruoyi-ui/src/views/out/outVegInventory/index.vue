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
<!--          v-hasPermi="['out:outVegInventory:add']"-->
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
<!--          v-hasPermi="['out:outVegInventory:edit']"-->
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
<!--          v-hasPermi="['out:outVegInventory:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outVegInventory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    <el-table v-loading="loading" :data="outVegInventoryList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="唯一标识符" align="center" prop="vegFruInventoryId" />-->
<!--      <el-table-column label="蔬菜类序号，用于标识蔬菜类别" align="center" prop="vegetableSeqNo" />-->
<!--      <el-table-column label="蔬菜类详细名" align="center" prop="vegetableDetailName" />-->
<!--      <el-table-column label="蔬菜类数量" align="center" prop="vegetableQuantity" />-->
<!--      <el-table-column label="水果类序号，用于标识水果类别" align="center" prop="fruitSeqNo" />-->
<!--      <el-table-column label="水果类详细名" align="center" prop="fruitDetailName" />-->
<!--      <el-table-column label="水果类数量" align="center" prop="fruitQuantity" />-->
<!--      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['out:outVegInventory:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['out:outVegInventory:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->



        <el-table v-loading="loading" :data="filteredVegFruInventoryListFilteFru" @selection-change="handleSelectionChange">
          <!--          <el-table-column type="selection" width="55" align="center"/>-->
          <el-table-column type="index" label="序号" width="200" align="center"/> <!-- 添加序号列 -->
          <el-table-column label="蔬菜种类" align="center" width="200" prop="name"/>
          <!--          <el-table-column label="蔬果类型" align="center" prop="type"/>-->
          <el-table-column label="数量" align="center" width="200" prop="quantity"/>
        </el-table>




<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

    <!-- 添加或修改蔬菜种类及数量对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="蔬菜类序号，用于标识蔬菜类别" prop="vegetableSeqNo">
          <el-input v-model="form.vegetableSeqNo" placeholder="请输入蔬菜类序号，用于标识蔬菜类别" />
        </el-form-item>
        <el-form-item label="蔬菜类详细名" prop="vegetableDetailName">
          <el-input v-model="form.vegetableDetailName" placeholder="请输入蔬菜类详细名" />
        </el-form-item>
        <el-form-item label="蔬菜类数量" prop="vegetableQuantity">
          <el-input v-model="form.vegetableQuantity" placeholder="请输入蔬菜类数量" />
        </el-form-item>
        <el-form-item label="水果类序号，用于标识水果类别" prop="fruitSeqNo">
          <el-input v-model="form.fruitSeqNo" placeholder="请输入水果类序号，用于标识水果类别" />
        </el-form-item>
        <el-form-item label="水果类详细名" prop="fruitDetailName">
          <el-input v-model="form.fruitDetailName" placeholder="请输入水果类详细名" />
        </el-form-item>
        <el-form-item label="水果类数量" prop="fruitQuantity">
          <el-input v-model="form.fruitQuantity" placeholder="请输入水果类数量" />
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
import { listOutVegInventory, getOutVegInventory, delOutVegInventory, addOutVegInventory, updateOutVegInventory } from "@/api/out/outVegInventory";

export default {
  name: "OutVegInventory",
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
      // 蔬菜种类及数量表格数据
      outVegInventoryList: [],
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
      rules: {
      }
    };
  },

  computed: {
    filteredVegFruInventoryListFilteFru() {
      return this.outVegInventoryList.filter(item => item.type === '蔬菜');
    }
  },



  created() {
    this.getList();
  },
  methods: {
    /** 查询蔬菜种类及数量列表 */
    getList() {
      this.loading = true;
      listOutVegInventory(this.queryParams).then(response => {
        this.outVegInventoryList = response.rows;
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬菜种类及数量";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vegFruInventoryId = row.vegFruInventoryId || this.ids
      getOutVegInventory(vegFruInventoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬菜种类及数量";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vegFruInventoryId != null) {
            updateOutVegInventory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutVegInventory(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除蔬菜种类及数量编号为"' + vegFruInventoryIds + '"的数据项？').then(function() {
        return delOutVegInventory(vegFruInventoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outVegInventory/export', {
        ...this.queryParams
      }, `outVegInventory_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
