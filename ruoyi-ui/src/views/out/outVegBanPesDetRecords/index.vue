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
      <el-form-item label="甲胺磷" prop="methamidophos">
        <el-input
          v-model="queryParams.methamidophos"
          placeholder="请输入甲胺磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="乙酰甲胺磷" prop="acephate">
        <el-input
          v-model="queryParams.acephate"
          placeholder="请输入乙酰甲胺磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="甲拌磷" prop="phorate">
        <el-input
          v-model="queryParams.phorate"
          placeholder="请输入甲拌磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氧乐果" prop="dimethoate">
        <el-input
          v-model="queryParams.dimethoate"
          placeholder="请输入氧乐果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="毒死蜱" prop="chlorpyrifos">
        <el-input
          v-model="queryParams.chlorpyrifos"
          placeholder="请输入毒死蜱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="特丁硫磷" prop="fenthion">
        <el-input
          v-model="queryParams.fenthion"
          placeholder="请输入特丁硫磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三唑磷" prop="triazophos">
        <el-input
          v-model="queryParams.triazophos"
          placeholder="请输入三唑磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水胺硫磷" prop="isocarbophos">
        <el-input
          v-model="queryParams.isocarbophos"
          placeholder="请输入水胺硫磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="治螟磷" prop="trichlorfon">
        <el-input
          v-model="queryParams.trichlorfon"
          placeholder="请输入治螟磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="乐果" prop="dichlorvos">
        <el-input
          v-model="queryParams.dichlorvos"
          placeholder="请输入乐果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="甲基异柳磷" prop="methylParathion">
        <el-input
          v-model="queryParams.methylParathion"
          placeholder="请输入甲基异柳磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氟虫腈" prop="flucythrinate">
        <el-input
          v-model="queryParams.flucythrinate"
          placeholder="请输入氟虫腈"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="克百威" prop="carbaryl">
        <el-input
          v-model="queryParams.carbaryl"
          placeholder="请输入克百威"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检出次数" prop="deteNum">
        <el-input
          v-model="queryParams.deteNum"
          placeholder="请输入检出次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="超标次数" prop="exDetNum">
        <el-input
          v-model="queryParams.exDetNum"
          placeholder="请输入超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录生产基地的检查次数" prop="productionInspectCount">
        <el-input
          v-model="queryParams.productionInspectCount"
          placeholder="请输入记录生产基地的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录生产基地的超标次数" prop="productionExceedCount">
        <el-input
          v-model="queryParams.productionExceedCount"
          placeholder="请输入记录生产基地的超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录批发市场的检查次数" prop="wholesaleInspectCount">
        <el-input
          v-model="queryParams.wholesaleInspectCount"
          placeholder="请输入记录批发市场的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录批发市场的超标次数" prop="wholesaleExceedCount">
        <el-input
          v-model="queryParams.wholesaleExceedCount"
          placeholder="请输入记录批发市场的超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录运输车的检查次数" prop="vehicleInspectCount">
        <el-input
          v-model="queryParams.vehicleInspectCount"
          placeholder="请输入记录运输车的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录运输车的超标次数" prop="vehicleExceedCount">
        <el-input
          v-model="queryParams.vehicleExceedCount"
          placeholder="请输入记录运输车的超标次数"
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
          v-hasPermi="['out:outVegBanPesDetRecords:add']"
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
          v-hasPermi="['out:outVegBanPesDetRecords:edit']"
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
          v-hasPermi="['out:outVegBanPesDetRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outVegBanPesDetRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outVegBanPesDetRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识每条检测记录" align="center" prop="vegBanPesDetRecordsId" />
      <el-table-column label="农药名称" align="center" prop="pesticideName" />
      <el-table-column label="甲胺磷" align="center" prop="methamidophos" />
      <el-table-column label="乙酰甲胺磷" align="center" prop="acephate" />
      <el-table-column label="甲拌磷" align="center" prop="phorate" />
      <el-table-column label="氧乐果" align="center" prop="dimethoate" />
      <el-table-column label="毒死蜱" align="center" prop="chlorpyrifos" />
      <el-table-column label="特丁硫磷" align="center" prop="fenthion" />
      <el-table-column label="三唑磷" align="center" prop="triazophos" />
      <el-table-column label="水胺硫磷" align="center" prop="isocarbophos" />
      <el-table-column label="治螟磷" align="center" prop="trichlorfon" />
      <el-table-column label="乐果" align="center" prop="dichlorvos" />
      <el-table-column label="甲基异柳磷" align="center" prop="methylParathion" />
      <el-table-column label="氟虫腈" align="center" prop="flucythrinate" />
      <el-table-column label="克百威" align="center" prop="carbaryl" />
      <el-table-column label="检出次数" align="center" prop="deteNum" />
      <el-table-column label="超标次数" align="center" prop="exDetNum" />
      <el-table-column label="记录生产基地的检查次数" align="center" prop="productionInspectCount" />
      <el-table-column label="记录生产基地的超标次数" align="center" prop="productionExceedCount" />
      <el-table-column label="记录批发市场的检查次数" align="center" prop="wholesaleInspectCount" />
      <el-table-column label="记录批发市场的超标次数" align="center" prop="wholesaleExceedCount" />
      <el-table-column label="记录运输车的检查次数" align="center" prop="vehicleInspectCount" />
      <el-table-column label="记录运输车的超标次数" align="center" prop="vehicleExceedCount" />
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
            v-hasPermi="['out:outVegBanPesDetRecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outVegBanPesDetRecords:remove']"
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

    <!-- 添加或修改蔬菜禁用农药检出及超标情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农药名称" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入农药名称" />
        </el-form-item>
        <el-form-item label="甲胺磷" prop="methamidophos">
          <el-input v-model="form.methamidophos" placeholder="请输入甲胺磷" />
        </el-form-item>
        <el-form-item label="乙酰甲胺磷" prop="acephate">
          <el-input v-model="form.acephate" placeholder="请输入乙酰甲胺磷" />
        </el-form-item>
        <el-form-item label="甲拌磷" prop="phorate">
          <el-input v-model="form.phorate" placeholder="请输入甲拌磷" />
        </el-form-item>
        <el-form-item label="氧乐果" prop="dimethoate">
          <el-input v-model="form.dimethoate" placeholder="请输入氧乐果" />
        </el-form-item>
        <el-form-item label="毒死蜱" prop="chlorpyrifos">
          <el-input v-model="form.chlorpyrifos" placeholder="请输入毒死蜱" />
        </el-form-item>
        <el-form-item label="特丁硫磷" prop="fenthion">
          <el-input v-model="form.fenthion" placeholder="请输入特丁硫磷" />
        </el-form-item>
        <el-form-item label="三唑磷" prop="triazophos">
          <el-input v-model="form.triazophos" placeholder="请输入三唑磷" />
        </el-form-item>
        <el-form-item label="水胺硫磷" prop="isocarbophos">
          <el-input v-model="form.isocarbophos" placeholder="请输入水胺硫磷" />
        </el-form-item>
        <el-form-item label="治螟磷" prop="trichlorfon">
          <el-input v-model="form.trichlorfon" placeholder="请输入治螟磷" />
        </el-form-item>
        <el-form-item label="乐果" prop="dichlorvos">
          <el-input v-model="form.dichlorvos" placeholder="请输入乐果" />
        </el-form-item>
        <el-form-item label="甲基异柳磷" prop="methylParathion">
          <el-input v-model="form.methylParathion" placeholder="请输入甲基异柳磷" />
        </el-form-item>
        <el-form-item label="氟虫腈" prop="flucythrinate">
          <el-input v-model="form.flucythrinate" placeholder="请输入氟虫腈" />
        </el-form-item>
        <el-form-item label="克百威" prop="carbaryl">
          <el-input v-model="form.carbaryl" placeholder="请输入克百威" />
        </el-form-item>
        <el-form-item label="检出次数" prop="deteNum">
          <el-input v-model="form.deteNum" placeholder="请输入检出次数" />
        </el-form-item>
        <el-form-item label="超标次数" prop="exDetNum">
          <el-input v-model="form.exDetNum" placeholder="请输入超标次数" />
        </el-form-item>
        <el-form-item label="记录生产基地的检查次数" prop="productionInspectCount">
          <el-input v-model="form.productionInspectCount" placeholder="请输入记录生产基地的检查次数" />
        </el-form-item>
        <el-form-item label="记录生产基地的超标次数" prop="productionExceedCount">
          <el-input v-model="form.productionExceedCount" placeholder="请输入记录生产基地的超标次数" />
        </el-form-item>
        <el-form-item label="记录批发市场的检查次数" prop="wholesaleInspectCount">
          <el-input v-model="form.wholesaleInspectCount" placeholder="请输入记录批发市场的检查次数" />
        </el-form-item>
        <el-form-item label="记录批发市场的超标次数" prop="wholesaleExceedCount">
          <el-input v-model="form.wholesaleExceedCount" placeholder="请输入记录批发市场的超标次数" />
        </el-form-item>
        <el-form-item label="记录运输车的检查次数" prop="vehicleInspectCount">
          <el-input v-model="form.vehicleInspectCount" placeholder="请输入记录运输车的检查次数" />
        </el-form-item>
        <el-form-item label="记录运输车的超标次数" prop="vehicleExceedCount">
          <el-input v-model="form.vehicleExceedCount" placeholder="请输入记录运输车的超标次数" />
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
import { listOutVegBanPesDetRecords, getOutVegBanPesDetRecords, delOutVegBanPesDetRecords, addOutVegBanPesDetRecords, updateOutVegBanPesDetRecords } from "@/api/out/outVegBanPesDetRecords";

export default {
  name: "OutVegBanPesDetRecords",
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
      // 蔬菜禁用农药检出及超标情况表格数据
      outVegBanPesDetRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        methamidophos: null,
        acephate: null,
        phorate: null,
        dimethoate: null,
        chlorpyrifos: null,
        fenthion: null,
        triazophos: null,
        isocarbophos: null,
        trichlorfon: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
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
    /** 查询蔬菜禁用农药检出及超标情况列表 */
    getList() {
      this.loading = true;
      listOutVegBanPesDetRecords(this.queryParams).then(response => {
        this.outVegBanPesDetRecordsList = response.rows;
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
        vegBanPesDetRecordsId: null,
        pesticideName: null,
        methamidophos: null,
        acephate: null,
        phorate: null,
        dimethoate: null,
        chlorpyrifos: null,
        fenthion: null,
        triazophos: null,
        isocarbophos: null,
        trichlorfon: null,
        dichlorvos: null,
        methylParathion: null,
        flucythrinate: null,
        carbaryl: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
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
      this.ids = selection.map(item => item.vegBanPesDetRecordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬菜禁用农药检出及超标情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vegBanPesDetRecordsId = row.vegBanPesDetRecordsId || this.ids
      getOutVegBanPesDetRecords(vegBanPesDetRecordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬菜禁用农药检出及超标情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vegBanPesDetRecordsId != null) {
            updateOutVegBanPesDetRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutVegBanPesDetRecords(this.form).then(response => {
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
      const vegBanPesDetRecordsIds = row.vegBanPesDetRecordsId || this.ids;
      this.$modal.confirm('是否确认删除蔬菜禁用农药检出及超标情况编号为"' + vegBanPesDetRecordsIds + '"的数据项？').then(function() {
        return delOutVegBanPesDetRecords(vegBanPesDetRecordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outVegBanPesDetRecords/export', {
        ...this.queryParams
      }, `outVegBanPesDetRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
