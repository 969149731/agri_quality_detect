<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="农药名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入农药名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录创建时间" prop="createdDate">
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

    <el-table
      v-if="!peopleTagNumLoading"
      ref="refreshTable"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      :data="StageList"
      style="width: 100%;"
      align='center'
      id="table1"
    >
      <el-table-column label="农药名称" align="center">
        <el-table-column prop="IncludeLable"></el-table-column>
        <el-table-column  align="center" prop="StageName">
        </el-table-column>
      </el-table-column>

      <el-table-column
        v-for="item in pesticideNameList"
        align="center"
        :label="item.pesticideName"
        :key="item.StageId"
        prop="value"
      >
        <template slot-scope="scope">
          <div>{{ item[scope.row['StageId']] }}</div>
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
import { listOutVegBanPesDetRecords2,listOutVegBanPesDetRecords, getOutVegBanPesDetRecords, delOutVegBanPesDetRecords, addOutVegBanPesDetRecords, updateOutVegBanPesDetRecords } from "@/api/out/outVegBanPesDetRecords";
import * as XLSX from "xlsx";
import * as XLSXS from "xlsx-style";
import FileSaver from 'file-saver'

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
      },
      StageList: [
        {
          IncludeLable:'检出次数',
          StageName: '检出次数',
          StageId: 'totalDet'

        },
        {
          IncludeLable:'超标次数',
          StageName: '超标次数',
          StageId: 'totalEx'
        },
        {
          IncludeLable:"其中",
          StageName: '生产基地检出次数',
          StageId: 'productBase'
        },
        {
          StageName: '生产基地超标次数',
          StageId: 'productBaseEx'
        },
        {
          StageName: '各类市场检出',
          StageId: 'market'
        },
        {
          StageName: '各类市场超标',
          StageId: 'marketEx'
        },
        {
          StageName: '运输车检出',
          StageId: 'vehicle'
        },
        {
          StageName: '运输车超标',
          StageId: 'vehicleEx'
        },
      ],
      pesticideNameList: [],
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
      listOutVegBanPesDetRecords2(this.queryParams).then(response => {//二维表使用的列表获取
        this.pesticideNameList = response.rows;
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
    },
    /*表头行的合并*/
    headerStyle({ row, column, rowIndex, columnIndex }) {
      const comStyle = {
        backgroundColor: "#3D589B",
        color: "#fff",
        fontSize: "500",
      };
      if(rowIndex===0){
        row[0].rowspan=2;
      }
      if(rowIndex===1) {
        if (columnIndex === 0 || columnIndex === 1) { // 将表头第一列隐藏
          return {
            display: "none",
            ...comStyle,
          };
        }
      }
      return comStyle;
    },
    /*表头列的合并*/
    spanMethod({ row, column, rowIndex, columnIndex }) {
      if(rowIndex=== 0 || rowIndex=== 1){
        if(columnIndex ===1){
          return {rowspan: 1, colspan: 0}
        }
        if(columnIndex ===0){
          return {rowspan: 1, colspan: 2}
        }
      }

      if (rowIndex=== 2)
      {//其中的那一行
        if (columnIndex === 0) {
          return {rowspan: 6, colspan: 1} // 隐藏表头下面第一行的第一列
        }
      }
      if(rowIndex> 2){//”其中“包含的行
        if (columnIndex === 0) {
          return {rowspan: 1, colspan: 0} // 隐藏表头下面第一行的第一列
        }
        if (columnIndex === 1) {
          return {rowspan: 1, colspan: 1} // 将表头下面第一行的第一列和第二列合并
        }
      }

    },
  }
};
</script>
