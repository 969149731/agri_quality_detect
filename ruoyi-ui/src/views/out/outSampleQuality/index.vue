<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">



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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
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


    <el-table
      v-loading="loading"
      ref="refreshTable"
      :data="outSampleQualityList"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      style="width: 100%;"
      align='center'
      id="table1"
    >
      <el-table-column label="样品来源"  align="center">
        <el-table-column  prop="stageIncludeType" width="100px" align="center"/>
        <el-table-column  prop="samplingStageType" width="100px" align="center"/>
      </el-table-column>

      <el-table-column label="蔬菜"  align="center">
        <el-table-column label="抽样数" prop="vegSamplingCount"/>
        <el-table-column label="合格数" prop="vegQualifiedCount"/>
        <el-table-column label="合格率(%)" prop="vegQualificationRate"/>
      </el-table-column>
      <el-table-column label="水果" prop="StandardLable" width="100px" align="center">
        <el-table-column label="抽样数" prop="fruSamplingCount"/>
        <el-table-column label="合格数" prop="fruQualifiedCount"/>
        <el-table-column label="合格率(%)" prop="fruQualificationRate"/>
      </el-table-column>
      <el-table-column label="茶叶" prop="StandardLable" width="100px" align="center">
        <el-table-column label="抽样数" prop="teaSamplingCount"/>
        <el-table-column label="合格数" prop="teaQualifiedCount"/>
        <el-table-column label="合格率(%)" prop="teaQualificationRate"/>
      </el-table-column>
      <el-table-column label="合计" prop="StandardLable" width="100px" align="center">
        <el-table-column label="抽样数" prop="totalSamplingCount"/>
        <el-table-column label="合格数" prop="totalQualifiedCount"/>
        <el-table-column label="合格率(%)" prop="totalQualificationRate"/>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import { listOutSampleQuality,samplingAddressProvince,findBySamplingProvinceCode,findBySamplingCityCode} from "@/api/out/outSampleQuality";
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
        createdDate: null,

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

      },
      // 日期范围
      dateRange: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      pesticideNameList: [],
      //地区级联
      samplingAddressProvince: [],//省份集合
      samplingAddressCity: [],//城市集合
      samplingAddressTown: [],//区域集合
      returnFeedBack:true,
    };
  },
  created() {
    this.init();
    this.getList();
  },
  methods: {
    /** 查询各抽样环节合格率情况列表 */
    getList() {
      this.loading = true;
      this.outSampleQualityList = [];
      listOutSampleQuality(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.outSampleQualityList = response.rows;
        this.total = response.total;
        this.loading = false;
        if(this.returnFeedBack && response.msg!=null && response.msg!=""){
          // this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "返回信息", { dangerouslyUseHTMLString: true });
          this.returnFeedBack=false;
        }
      });
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
      this.dateRange=[];
      this.resetForm("queryForm");
      this.handleQuery();
      this.init();
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outSampleQuality/export', {
        ...this.queryParams
      }, `outSampleQualityRecords_${new Date().getTime()}.xlsx`)
    },
    //地区级联数据初始化
    init()
    {
      this.queryParams.samplingLocationCounty='';
      this.queryParams.samplingLocation=null;

      this.queryParams.samplingLocationCity='';
      this.queryParams.samplingCity=null;
      samplingAddressProvince().then((res) => {
        this.samplingAddressProvince = res;
        console.log(res)
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
        this.queryParams.samplingLocationCounty=null;
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
    /*表头行的合并*/
    headerStyle({row, column, rowIndex, columnIndex }) {
      // console.log(row, column, rowIndex, columnIndex);
      const comStyle = {
        backgroundColor: "#428fd7",
        color: "#fff",
        fontSize: "500",
      };
      if(rowIndex===1){
        row[0].rowspan=2;
      }
      return {...comStyle}

    },
    /*表头列的合并*/
    spanMethod({ row, column, rowIndex, columnIndex }) {
      if(rowIndex=== 0 || rowIndex>= 7 ){
        if(columnIndex ===1){
          return {rowspan: 1, colspan: 0}
        }
        if(columnIndex ===0){
          return {rowspan: 1, colspan: 2}
        }
      }

      if (rowIndex=== 1)
      {//其中的那一行
        if (columnIndex === 0) {
          return {rowspan: 6, colspan: 1} // 隐藏表头下面第一行的第一列
        }
      }
      if(rowIndex> 1){//”其中“包含的行
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
