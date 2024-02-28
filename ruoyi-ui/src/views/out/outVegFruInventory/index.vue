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

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['out:outVegFruInventory:export']"-->
<!--        >导出-->
<!--        </el-button>-->
<!--      </el-col>-->

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportTableToExcel"
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



    <!-- 假设的隐藏表格，用于导出 -->
<!--    <div style="display: none;">-->
<!--      <table id="export-table1">-->
<!--        <thead>-->
<!--        <tr>-->
<!--          <th>序号</th>-->
<!--          <th>种类</th>-->
<!--          <th>数量</th>-->
<!--          <th>类型</th>-->
<!--        </tr>-->
<!--        </thead>-->
<!--        <tbody>-->
<!--        &lt;!&ndash; 填充数据 &ndash;&gt;-->
<!--        <tr v-for="(item, index) in filteredVegFruInventoryListFilteVeg" :key="'veg-' + index">-->
<!--          <td>{{ index + 1 }}</td>-->
<!--          <td>{{ item.name }}</td>-->
<!--          <td>{{ item.quantity }}</td>-->
<!--          <td>蔬菜</td>-->
<!--        </tr>-->

<!--        <tr v-for="(item, index) in filteredVegFruInventoryListFilteFru" :key="'fru-' + index">-->
<!--          <td>{{ index + 1 }}</td>-->
<!--          <td>{{ item.name }}</td>-->
<!--          <td>{{ item.quantity }}</td>-->
<!--          <td>水果</td>-->
<!--        </tr>-->
<!--        </tbody>-->
<!--      </table>-->
<!--    </div>-->



    <div style="display: none;" id="export-table">
      <div>
        <table>
          <thead>
          <tr>
            <th>蔬菜水果种类及数量</th>
            <th>   </th>
            <th>   </th>
          </tr>
          <tr>
            <th>   </th>
            <th>   </th>
            <th>   </th>
          </tr>
          <tr>
            <th>   </th>
            <th>蔬菜类 </th>
            <th>   </th>
          </tr>
          <tr>
            <th>序号</th>
            <th>蔬菜种类</th>
            <th>数量</th>
          </tr>
          </thead>
          <tbody>
          <!-- 填充蔬菜数据 -->
          <tr v-for="(item, index) in filteredVegFruInventoryListFilteVeg" :key="'veg-' + index">
            <td>{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.quantity }}</td>
          </tr>
          </tbody>
        </table>
      </div>

      <div>
        <table >
          <thead>
          <tr>
            <th>   </th>
            <th>   </th>
            <th>   </th>
          </tr>
          <tr>
            <th>   </th>
            <th>   </th>
            <th>   </th>
          </tr>
          <tr>
            <th>   </th>
            <th>水果类</th>
            <th>   </th>
          </tr>
          <tr>
            <th>序号</th>
            <th>水果种类</th>
            <th>数量</th>
          </tr>
          </thead>
          <tbody>
          <!-- 填充水果数据 -->
          <tr v-for="(item, index) in filteredVegFruInventoryListFilteFru" :key="'fru-' + index">
            <td>{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.quantity }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>










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
import * as XLSX from 'xlsx';

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
    },
    exportTableToExcel() {
      // 使用SheetJS读取隐藏的HTML表格
      let table = document.getElementById('export-table');
      let workbook = XLSX.utils.table_to_book(table);

      // // 假设我们要设置第一个Sheet的所有列宽
      let firstSheetName = workbook.SheetNames[0];
      let firstSheet = workbook.Sheets[firstSheetName];
      //
      // // 设置列宽，这里以字符宽度为单位
      // // 例如，设置所有列的宽度为20字符宽
      // const desiredWidth = 13;
      // firstSheet['!cols'] = Array(10).fill({wch:desiredWidth}); // 假设有10列


      // 设置B列的宽度，这里以字符宽度为单位
      const desiredWidthB = 14; // 假设我们想要将B列的宽度设置为20个字符宽
      // 确保!cols属性存在
      if(!firstSheet['!cols']) firstSheet['!cols'] = [];
      // 设置B列的宽度 0是A列 1是b列 2是c列
      firstSheet['!cols'][1] = {wch: desiredWidthB};
      firstSheet['!cols'][0] = {wch: 5};
      firstSheet['!cols'][2] = {wch: 5};

      // 设置A1单元格的样式
      if (!firstSheet['A1'].s) firstSheet['A1'].s = {};

      // 尝试设置字体加粗和颜色   这边没效果 不知道为什么
      firstSheet['A1'].s.font = {
        bold: true, // 加粗
        color: { rgb: "FF0000" } // 字体颜色为红色
      };

      // 使用SheetJS导出工作簿到Excel文件
      XLSX.writeFile(workbook, 'exported_data.xlsx');
    }


  }
};
</script>
