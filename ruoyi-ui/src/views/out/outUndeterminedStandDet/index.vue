<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">


      <el-form-item label="抽样环节" prop="samplingStageType">
        <el-input
          v-model="queryParams.samplingStageType"
          placeholder="请输入抽样环节"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="企业名称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名称/农户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="企业属性" prop="enterpriseAttribute">
        <el-input
          v-model="queryParams.enterpriseAttribute"
          placeholder="请输入企业属性"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="信用代码" prop="enterpriseCreditIdCode">
        <el-input
          v-model="queryParams.enterpriseCreditIdCode"
          placeholder="请输入企业信用代码/身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="样品类别" prop="vegFruType">
        <el-select v-model="queryParams.vegFruType" placeholder="请选择样品类别" clearable>
          <el-option
            v-for="dict in dict.type.veg_fru_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>


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
      <el-form-item label="检测类型" prop="samplingType">
        <el-select v-model="queryParams.samplingType" placeholder="请选择检测类型" clearable>
          <el-option
            v-for="dict in dict.type.sampling_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="抽样地点" prop="samplingLocation">
        <el-input
          v-model="queryParams.samplingLocation"
          placeholder="请输入抽样地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="检出农药品种" prop="detPesticideName">-->
<!--        <el-input-->
<!--          v-model="queryParams.detPesticideName"-->
<!--          placeholder="请输入检出农药品种"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="检出农药值" prop="detPesticideValue">-->
<!--        <el-input-->
<!--          v-model="queryParams.detPesticideValue"-->
<!--          placeholder="请输入检出农药值"-->
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

      <el-form-item label="抽样日期">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="农药名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入农药名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抽样地点">
        <template>
          <div>
            <el-select v-model="queryParams.samplingProvince" placeholder="省份" value-key="code" @change="changeSamplingProvince">
              <el-option
                v-for="item in samplingAddressProvince"
                :key="item.code"
                :label="item.name"
                :value="item"
              ></el-option>
            </el-select>

            <el-select v-model="queryParams.samplingCity" placeholder="城市" value-key="code"  @change="changeSamplingCity">
              <el-option
                v-for="item  in samplingAddressCity"
                :key="item.code"
                :label="item.name"
                :value="item"
              ></el-option>
            </el-select>
            <el-select v-model="queryParams.samplingTown" placeholder="区域" value-key="code" @change="changeSamplingTown">
              <el-option
                v-for="item   in samplingAddressTown"
                :key="item.code"
                :label="item.name"
                :value="item"
              ></el-option>
            </el-select>
          </div>
        </template>
      </el-form-item>



      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['out:outUndeterminedStandDet:add']"-->
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
<!--          v-hasPermi="['out:outUndeterminedStandDet:edit']"-->
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
<!--          v-hasPermi="['out:outUndeterminedStandDet:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outUndeterminedStandDet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outUndeterminedStandDetList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="唯一标识符，自增" align="center" prop="undeterminedStandardDetectionId" />-->
      <el-table-column label="检测单位" align="center" prop="detectUnit" />
      <el-table-column label="样品编号" align="center" prop="sampleCode" />
      <el-table-column label="样品名称" align="center" prop="vegFruName" />
      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />

      <el-table-column label="检出农药品种 | 检出值" align="center" prop="result" width="350">
        <template slot-scope="props" >
          <el-table  :data="props.row.detPesticideNameAndValue">
            <el-table-column  label="检出农药品种" align="center" prop="pesticideName"  width="150">
            </el-table-column>
            <el-table-column  label="检出值 mg/kg" align="center" prop="pesticideDetValue" width="150">
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>

<!--      <el-table-column label="备注" align="center" prop="remarks" />-->
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
<!--            v-hasPermi="['out:outUndeterminedStandDet:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['out:outUndeterminedStandDet:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

    <!-- 添加或修改无判定标准检出值详细对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="检测单位" prop="detectUnit">-->
<!--          <el-input v-model="form.detectUnit" placeholder="请输入检测单位" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="样品编号" prop="sampleCode">-->
<!--          <el-input v-model="form.sampleCode" placeholder="请输入样品编号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="样品名称" prop="vegFruName">-->
<!--          <el-input v-model="form.vegFruName" placeholder="请输入样品名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="抽样地点" prop="samplingLocation">-->
<!--          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="检出农药品种" prop="detPesticideName">-->
<!--          <el-input v-model="form.detPesticideName" placeholder="请输入检出农药品种" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="检出农药值" prop="detPesticideValue">-->
<!--          <el-input v-model="form.detPesticideValue" placeholder="请输入检出农药值" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注" prop="remarks">-->
<!--          <el-input v-model="form.remarks" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="记录创建的时间" prop="createdDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择记录创建的时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listOutUndeterminedStandDet, getOutUndeterminedStandDet, delOutUndeterminedStandDet, addOutUndeterminedStandDet, updateOutUndeterminedStandDet,
  samplingAddressProvince,
  findBySamplingProvinceCode,
  findBySamplingCityCode,
} from "@/api/out/outUndeterminedStandDet";
import {listOutExceedSampleDetail} from "@/api/out/outExceedSampleDetail";

export default {
  name: "OutUndeterminedStandDet",
  dicts: ['veg_fru_type','sampling_type'],

  data() {
    return {
      samplingAddressProvince: [],//省份集合
      samplingAddressCity: [],//城市集合
      samplingAddressTown: [],//区域集合
      samplingProvinceCode: '',//获取选中时的省份编号
      samplingCityCode: '',//获取选中时的城市编号
      samplingTownCode: '',//获取选中时区域的编号


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
      // 无判定标准检出值详细表格数据
      outUndeterminedStandDetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1500,
        detectUnit: null,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        detPesticideName: null,
        detPesticideValue: null,
        remarks: null,
        createdDate: null,
        pesticideName:null,

        //对应到实体类的名字
        samplingLocationProvince:null,
        samplingLocationCity:null,
        samplingLocationCounty:null,


        //装对象的（v-model下拉框对象）
        samplingProvince: {code:"450000",name:"广西壮族自治区"},
        samplingCity:null,
        samplingTown:null,

        enterpriseName:null,
        enterpriseAttribute:null,
        enterpriseCreditIdCode:null,
        samplingStageType:null,

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.init();
    this.getList();
  },
  methods: {
      init()
      {
        this.queryParams.samplingLocationCounty='';
        this.queryParams.samplingLocation=null;

        this.queryParams.samplingLocationCity='';
        this.queryParams.samplingCity=null;
        samplingAddressProvince().then((res) => {
          this.samplingAddressProvince = res;
          // console.log(res)
          //初始化省份数据后，根据默认省份代码加载城市
          if (this.queryParams.samplingProvince.code) {
            let foundObject = this.samplingAddressProvince.find(obj => (obj.code===this.queryParams.samplingProvince.code));//找到对应省份对象
            this.changeSamplingProvince(foundObject);
            this.queryParams.samplingLocationProvince=foundObject.name;
          }
        });
      },

      changeSamplingProvince(val) {
        findBySamplingProvinceCode(val.code).then((res) => {
          this.samplingAddressCity = res;
          // 清空之前选中的城市和区域信息
          this.queryParams.samplingCity = '';
          this.queryParams.samplingTown = '';
          //下级表单清空
          this.queryParams.samplingLocationCity=null;
          this.queryParams.samplingTown=null;
        });
        //表单数据填充
        this.queryParams.samplingLocationProvince=val.name;
      },
      changeSamplingCity(val) {
        findBySamplingCityCode(val.code).then((res) => {
          this.samplingAddressTown = res;
          // 清空之前选中的区域信息
          this.queryParams.samplingTown = null;
          //下级表单清空
          this.queryParams.samplingLocationCounty=null;
        });
        //表单数据填充
        this.queryParams.samplingLocationCity=val.name;
      },
      changeSamplingTown(val){
        //表单数据填充
        this.queryParams.samplingLocationCounty=val.name;
      },


      /** 查询无判定标准检出值详细列表 */
    getList() {
      this.loading = true;
      listOutUndeterminedStandDet(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.outUndeterminedStandDetList = response.rows;
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
        undeterminedStandardDetectionId: null,
        detectUnit: null,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        detPesticideName: null,
        detPesticideValue: null,
        remarks: null,
        createdDate: null,
        pesticideName:null
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.init();
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.undeterminedStandardDetectionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加无判定标准检出值详细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const undeterminedStandardDetectionId = row.undeterminedStandardDetectionId || this.ids
      getOutUndeterminedStandDet(undeterminedStandardDetectionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改无判定标准检出值详细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.undeterminedStandardDetectionId != null) {
            updateOutUndeterminedStandDet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutUndeterminedStandDet(this.form).then(response => {
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
      const undeterminedStandardDetectionIds = row.undeterminedStandardDetectionId || this.ids;
      this.$modal.confirm('是否确认删除无判定标准检出值详细编号为"' + undeterminedStandardDetectionIds + '"的数据项？').then(function() {
        return delOutUndeterminedStandDet(undeterminedStandardDetectionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outUndeterminedStandDet/export', {
        ...this.queryParams
      }, `outUndeterminedStandDet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
