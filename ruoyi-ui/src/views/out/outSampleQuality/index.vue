<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="无公害产品基地的描述或标识" prop="pollutionFreeBase">
        <el-input
          v-model="queryParams.pollutionFreeBase"
          placeholder="请输入无公害产品基地的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地标产品基地的描述或标识" prop="landmarkProductBase">
        <el-input
          v-model="queryParams.landmarkProductBase"
          placeholder="请输入地标产品基地的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绿色产品基地的描述或标识" prop="greenProductBase">
        <el-input
          v-model="queryParams.greenProductBase"
          placeholder="请输入绿色产品基地的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有机产品基地的描述或标识" prop="organicProductBase">
        <el-input
          v-model="queryParams.organicProductBase"
          placeholder="请输入有机产品基地的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="散户的描述或标识" prop="individualHousehold">
        <el-input
          v-model="queryParams.individualHousehold"
          placeholder="请输入散户的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他基地的描述或标识" prop="otherBase">
        <el-input
          v-model="queryParams.otherBase"
          placeholder="请输入其他基地的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批发市场的描述或标识" prop="wholesaleMarket">
        <el-input
          v-model="queryParams.wholesaleMarket"
          placeholder="请输入批发市场的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运输车的描述或标识" prop="transportVehicle">
        <el-input
          v-model="queryParams.transportVehicle"
          placeholder="请输入运输车的描述或标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="allCount">
        <el-input
          v-model="queryParams.allCount"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蔬菜的抽样数" prop="vegSamplingCount">
        <el-input
          v-model="queryParams.vegSamplingCount"
          placeholder="请输入蔬菜的抽样数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蔬菜的合格数" prop="vegQualifiedCount">
        <el-input
          v-model="queryParams.vegQualifiedCount"
          placeholder="请输入蔬菜的合格数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蔬菜的合格率，以百分比表示" prop="vegQualificationRate">
        <el-input
          v-model="queryParams.vegQualificationRate"
          placeholder="请输入蔬菜的合格率，以百分比表示"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水果的抽样数" prop="fruSamplingCount">
        <el-input
          v-model="queryParams.fruSamplingCount"
          placeholder="请输入水果的抽样数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水果的合格数" prop="fruQualifiedCount">
        <el-input
          v-model="queryParams.fruQualifiedCount"
          placeholder="请输入水果的合格数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水果的合格率，以百分比表示" prop="fruQualificationRate">
        <el-input
          v-model="queryParams.fruQualificationRate"
          placeholder="请输入水果的合格率，以百分比表示"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总抽样数" prop="totalSamplingCount">
        <el-input
          v-model="queryParams.totalSamplingCount"
          placeholder="请输入总抽样数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总合格数" prop="totalQualifiedCount">
        <el-input
          v-model="queryParams.totalQualifiedCount"
          placeholder="请输入总合格数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总合格率，以百分比表示" prop="totalQualificationRate">
        <el-input
          v-model="queryParams.totalQualificationRate"
          placeholder="请输入总合格率，以百分比表示"
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
          v-hasPermi="['out:outSampleQuality:add']"
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
          v-hasPermi="['out:outSampleQuality:edit']"
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
          v-hasPermi="['out:outSampleQuality:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outSampleQuality:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outSampleQualityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识符" align="center" prop="sampleQualityId" />
      <el-table-column label="样品来源，
抽样环节" align="center" prop="samplingStageType" />
      <el-table-column label="无公害产品基地的描述或标识" align="center" prop="pollutionFreeBase" />
      <el-table-column label="地标产品基地的描述或标识" align="center" prop="landmarkProductBase" />
      <el-table-column label="绿色产品基地的描述或标识" align="center" prop="greenProductBase" />
      <el-table-column label="有机产品基地的描述或标识" align="center" prop="organicProductBase" />
      <el-table-column label="散户的描述或标识" align="center" prop="individualHousehold" />
      <el-table-column label="其他基地的描述或标识" align="center" prop="otherBase" />
      <el-table-column label="批发市场的描述或标识" align="center" prop="wholesaleMarket" />
      <el-table-column label="运输车的描述或标识" align="center" prop="transportVehicle" />
      <el-table-column label="合计" align="center" prop="allCount" />
      <el-table-column label="蔬菜的抽样数" align="center" prop="vegSamplingCount" />
      <el-table-column label="蔬菜的合格数" align="center" prop="vegQualifiedCount" />
      <el-table-column label="蔬菜的合格率，以百分比表示" align="center" prop="vegQualificationRate" />
      <el-table-column label="水果的抽样数" align="center" prop="fruSamplingCount" />
      <el-table-column label="水果的合格数" align="center" prop="fruQualifiedCount" />
      <el-table-column label="水果的合格率，以百分比表示" align="center" prop="fruQualificationRate" />
      <el-table-column label="总抽样数" align="center" prop="totalSamplingCount" />
      <el-table-column label="总合格数" align="center" prop="totalQualifiedCount" />
      <el-table-column label="总合格率，以百分比表示" align="center" prop="totalQualificationRate" />
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
            v-hasPermi="['out:outSampleQuality:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outSampleQuality:remove']"
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

    <!-- 添加或修改各抽样环节合格率情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="无公害产品基地的描述或标识" prop="pollutionFreeBase">
          <el-input v-model="form.pollutionFreeBase" placeholder="请输入无公害产品基地的描述或标识" />
        </el-form-item>
        <el-form-item label="地标产品基地的描述或标识" prop="landmarkProductBase">
          <el-input v-model="form.landmarkProductBase" placeholder="请输入地标产品基地的描述或标识" />
        </el-form-item>
        <el-form-item label="绿色产品基地的描述或标识" prop="greenProductBase">
          <el-input v-model="form.greenProductBase" placeholder="请输入绿色产品基地的描述或标识" />
        </el-form-item>
        <el-form-item label="有机产品基地的描述或标识" prop="organicProductBase">
          <el-input v-model="form.organicProductBase" placeholder="请输入有机产品基地的描述或标识" />
        </el-form-item>
        <el-form-item label="散户的描述或标识" prop="individualHousehold">
          <el-input v-model="form.individualHousehold" placeholder="请输入散户的描述或标识" />
        </el-form-item>
        <el-form-item label="其他基地的描述或标识" prop="otherBase">
          <el-input v-model="form.otherBase" placeholder="请输入其他基地的描述或标识" />
        </el-form-item>
        <el-form-item label="批发市场的描述或标识" prop="wholesaleMarket">
          <el-input v-model="form.wholesaleMarket" placeholder="请输入批发市场的描述或标识" />
        </el-form-item>
        <el-form-item label="运输车的描述或标识" prop="transportVehicle">
          <el-input v-model="form.transportVehicle" placeholder="请输入运输车的描述或标识" />
        </el-form-item>
        <el-form-item label="合计" prop="allCount">
          <el-input v-model="form.allCount" placeholder="请输入合计" />
        </el-form-item>
        <el-form-item label="蔬菜的抽样数" prop="vegSamplingCount">
          <el-input v-model="form.vegSamplingCount" placeholder="请输入蔬菜的抽样数" />
        </el-form-item>
        <el-form-item label="蔬菜的合格数" prop="vegQualifiedCount">
          <el-input v-model="form.vegQualifiedCount" placeholder="请输入蔬菜的合格数" />
        </el-form-item>
        <el-form-item label="蔬菜的合格率，以百分比表示" prop="vegQualificationRate">
          <el-input v-model="form.vegQualificationRate" placeholder="请输入蔬菜的合格率，以百分比表示" />
        </el-form-item>
        <el-form-item label="水果的抽样数" prop="fruSamplingCount">
          <el-input v-model="form.fruSamplingCount" placeholder="请输入水果的抽样数" />
        </el-form-item>
        <el-form-item label="水果的合格数" prop="fruQualifiedCount">
          <el-input v-model="form.fruQualifiedCount" placeholder="请输入水果的合格数" />
        </el-form-item>
        <el-form-item label="水果的合格率，以百分比表示" prop="fruQualificationRate">
          <el-input v-model="form.fruQualificationRate" placeholder="请输入水果的合格率，以百分比表示" />
        </el-form-item>
        <el-form-item label="总抽样数" prop="totalSamplingCount">
          <el-input v-model="form.totalSamplingCount" placeholder="请输入总抽样数" />
        </el-form-item>
        <el-form-item label="总合格数" prop="totalQualifiedCount">
          <el-input v-model="form.totalQualifiedCount" placeholder="请输入总合格数" />
        </el-form-item>
        <el-form-item label="总合格率，以百分比表示" prop="totalQualificationRate">
          <el-input v-model="form.totalQualificationRate" placeholder="请输入总合格率，以百分比表示" />
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
import { listOutSampleQuality, getOutSampleQuality, delOutSampleQuality, addOutSampleQuality, updateOutSampleQuality } from "@/api/out/outSampleQuality";

export default {
  name: "OutSampleQuality",
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
      // 各抽样环节合格率情况表格数据
      outSampleQualityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        samplingStageType: null,
        pollutionFreeBase: null,
        landmarkProductBase: null,
        greenProductBase: null,
        organicProductBase: null,
        individualHousehold: null,
        otherBase: null,
        wholesaleMarket: null,
        transportVehicle: null,
        allCount: null,
        vegSamplingCount: null,
        vegQualifiedCount: null,
        vegQualificationRate: null,
        fruSamplingCount: null,
        fruQualifiedCount: null,
        fruQualificationRate: null,
        totalSamplingCount: null,
        totalQualifiedCount: null,
        totalQualificationRate: null,
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
    /** 查询各抽样环节合格率情况列表 */
    getList() {
      this.loading = true;
      listOutSampleQuality(this.queryParams).then(response => {
        this.outSampleQualityList = response.rows;
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
        sampleQualityId: null,
        samplingStageType: null,
        pollutionFreeBase: null,
        landmarkProductBase: null,
        greenProductBase: null,
        organicProductBase: null,
        individualHousehold: null,
        otherBase: null,
        wholesaleMarket: null,
        transportVehicle: null,
        allCount: null,
        vegSamplingCount: null,
        vegQualifiedCount: null,
        vegQualificationRate: null,
        fruSamplingCount: null,
        fruQualifiedCount: null,
        fruQualificationRate: null,
        totalSamplingCount: null,
        totalQualifiedCount: null,
        totalQualificationRate: null,
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
      this.ids = selection.map(item => item.sampleQualityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加各抽样环节合格率情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleQualityId = row.sampleQualityId || this.ids
      getOutSampleQuality(sampleQualityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改各抽样环节合格率情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleQualityId != null) {
            updateOutSampleQuality(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutSampleQuality(this.form).then(response => {
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
      const sampleQualityIds = row.sampleQualityId || this.ids;
      this.$modal.confirm('是否确认删除各抽样环节合格率情况编号为"' + sampleQualityIds + '"的数据项？').then(function() {
        return delOutSampleQuality(sampleQualityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outSampleQuality/export', {
        ...this.queryParams
      }, `outSampleQuality_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
