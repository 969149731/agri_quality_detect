<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="蔬果类型" prop="vegFruType">
        <el-select v-model="queryParams.vegFruType" placeholder="请选择蔬果类型" clearable>
          <el-option
            v-for="dict in dict.type.veg_fru_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <!-- 字典下拉框的 -->
<!--      <el-form-item label="细分种类" prop="detailType">-->
<!--        <el-select v-model="queryParams.detailType" placeholder="请选择细分种类" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.detail_type"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->


      <el-form-item label="细分种类" prop="detailType">
        <el-input
          v-model="queryParams.detailType"
          placeholder="请输入细分种类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="蔬果名称" prop="vegFruName">
        <el-input
          v-model="queryParams.vegFruName"
          placeholder="请输入蔬果名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="高风险样品" prop="highRiskSample" label-width="30">
        <el-select v-model="queryParams.highRiskSample" placeholder="请选择是否为高风险品种样品，如果为高风险，在属性中填入高风险，若为空，就说明不是高风险品种样品" clearable>
          <el-option
            v-for="dict in dict.type.high_risk_sample"
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
          v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:add']"
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
          v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:edit']"
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
          v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agriVegetableFruitDictionaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键，自增长" align="center" prop="vegetableFruitId" />-->
      <el-table-column label="蔬果类型" align="center" prop="vegFruType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.veg_fru_type" :value="scope.row.vegFruType"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="细分种类" align="center" prop="detailType">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.detail_type" :value="scope.row.detailType"/>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="细分种类" align="center" prop="detailType"/>

      <el-table-column label="蔬果具体名称" align="center" prop="vegFruName" />
      <el-table-column label="高风险样品" align="center" prop="highRiskSample">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.high_risk_sample" :value="scope.row.highRiskSample"/>
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
            v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['agriDictionary:agriVegetableFruitDictionary:remove']"
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

    <!-- 添加或修改蔬果表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="蔬果类型" prop="vegFruType">
          <el-select v-model="form.vegFruType" placeholder="请选择蔬果类型">
            <el-option
              v-for="dict in dict.type.veg_fru_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>



<!--        <el-form-item label="细分种类" prop="detailType">-->
<!--          <el-select v-model="form.detailType" placeholder="请选择细分种类">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.detail_type"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="dict.value"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

        <el-form-item label="细分种类" prop="detailType">
          <el-input v-model="form.detailType" placeholder="请输入细分种类" />
        </el-form-item>

        <el-form-item label="蔬果名称" prop="vegFruName">
          <el-input v-model="form.vegFruName" placeholder="请输入蔬果具体名称" />
        </el-form-item>
        <el-form-item label="高风险样品" prop="highRiskSample">
          <el-select v-model="form.highRiskSample" placeholder="请选择是否为高风险品种样品，如果为高风险，在属性中填入高风险，若为空，就说明不是高风险品种样品">
            <el-option
              v-for="dict in dict.type.high_risk_sample"
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
import { listAgriVegetableFruitDictionary, getAgriVegetableFruitDictionary, delAgriVegetableFruitDictionary, addAgriVegetableFruitDictionary, updateAgriVegetableFruitDictionary } from "@/api/agriDictionary/agriVegetableFruitDictionary";

export default {
  name: "AgriVegetableFruitDictionary",
  dicts: ['detail_type', 'high_risk_sample', 'veg_fru_type'],
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
      // 蔬果表表格数据
      agriVegetableFruitDictionaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vegFruType: null,
        detailType: null,
        vegFruName: null,
        highRiskSample: null,
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
    /** 查询蔬果表列表 */
    getList() {
      this.loading = true;
      listAgriVegetableFruitDictionary(this.queryParams).then(response => {
        this.agriVegetableFruitDictionaryList = response.rows;
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
        vegetableFruitId: null,
        vegFruType: null,
        detailType: null,
        vegFruName: null,
        highRiskSample: null,
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
      this.ids = selection.map(item => item.vegetableFruitId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬果表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vegetableFruitId = row.vegetableFruitId || this.ids
      getAgriVegetableFruitDictionary(vegetableFruitId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬果表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vegetableFruitId != null) {
            updateAgriVegetableFruitDictionary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAgriVegetableFruitDictionary(this.form).then(response => {
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
      const vegetableFruitIds = row.vegetableFruitId || this.ids;
      this.$modal.confirm('是否确认删除蔬果表编号为"' + vegetableFruitIds + '"的数据项？').then(function() {
        return delAgriVegetableFruitDictionary(vegetableFruitIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('agriDictionary/agriVegetableFruitDictionary/export', {
        ...this.queryParams
      }, `agriVegetableFruitDictionary_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
