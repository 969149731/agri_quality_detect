<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

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
          v-hasPermi="['out:outStandCompliance:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      v-if="!peopleTagNumLoading"
      ref="refreshTable"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      :data="StandardList"
      style="width: 100%;"
      align='center'
      id="table1"
    >
      <el-table-column label="项目" prop="StandardLable" width="110px" align="center">
      </el-table-column>
      <el-table-column label="农药超标数" align="left">
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
      </el-table-column>
      <el-table-column label="抽检数量" prop="sampleNum" width="100px" align="center">
      </el-table-column>
      <el-table-column label="合格数" prop="passNum" width="100px" align="center">
      </el-table-column>
      <el-table-column label="合格率" prop="passRate" width="100px" align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listOutStandCompliance,samplingAddressProvince,findBySamplingProvinceCode,findBySamplingCityCode} from "@/api/out/outStandCompliance";
import FileSaver from 'file-saver';
import { reactive } from 'vue'
export default {
  name: "OutStandCompliance",
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
      // 参照国际组织或国家标准合格率情况表格数据
      outStandComplianceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        standard: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        sourceArea: null,

        samplingQuantity: null,
        qualifiedNumber: null,
        qualificationRate: null,
        createdDate: null,
        //对应到实体类的名字
        samplingLocationProvince:null,
        samplingLocationCity:null,
        samplingLocationCounty:null,

        //装对象的（v-model下拉框对象）
        samplingProvince: {code:"450000",name:"广西壮族自治区"},
        samplingCity:null,
        samplingTown:null,
      },
      // 日期范围
      dateRange: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      StandardList: [
        {
          StandardLable: '参照我国标准',
          StageId: 'CN',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,

        },
        {
          StandardLable: '参照CAC标准',
          StageId: 'CAC',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,
        },
        {
          StandardLable: '参照美国标准',
          StageId: 'US',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,
        },
        {
          StandardLable: '参照欧盟标准',
          StageId: 'EU',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,
        },
        {
          StandardLable: '参照日本标准',
          StageId: 'JPN',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,
        },
        {
          StandardLable: '参照韩国标准',
          StageId: 'KR',
          sampleNum:0,
          passNum:0,
          passRate:0,
          totalEx:0,
        },
      ],
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
    /** 查询参照国际组织或国家标准合格率情况列表 */
    getList() {
      this.loading = true;
      this.pesticideNameList=[];
      listOutStandCompliance(this.addDateRange(this.queryParams, this.dateRange)).then(response => {//二维表使用的列表获取
        this.pesticideNameList = response.rows;
        this.total = response.total;
        let length=this.pesticideNameList.length
        //抽样数
        this.StandardList[0].sampleNum=this.pesticideNameList[length-2].CN;
        this.StandardList[1].sampleNum=this.pesticideNameList[length-2].CAC;
        this.StandardList[2].sampleNum=this.pesticideNameList[length-2].US;
        this.StandardList[3].sampleNum=this.pesticideNameList[length-2].EU;
        this.StandardList[4].sampleNum=this.pesticideNameList[length-2].JPN;
        this.StandardList[5].sampleNum=this.pesticideNameList[length-2].KR;
        //合格数
        this.StandardList[0].passNum=this.pesticideNameList[length-1].CN;
        this.StandardList[1].passNum=this.pesticideNameList[length-1].CAC;
        this.StandardList[2].passNum=this.pesticideNameList[length-1].US;
        this.StandardList[3].passNum=this.pesticideNameList[length-1].EU;
        this.StandardList[4].passNum=this.pesticideNameList[length-1].JPN;
        this.StandardList[5].passNum=this.pesticideNameList[length-1].KR;
        for(let standard of this.StandardList){//计算Rate
          if(standard.sampleNum===0){
            standard.passRate=0;
            standard.passRate=standard.passRate.toFixed(2)//保留两位小数，注意会将类型改为字符串类型
          }else {
            standard.passRate=standard.passNum/standard.sampleNum*100
            standard.passRate=standard.passRate.toFixed(2)//保留两位小数，注意会将类型改为字符串类型
          }
        }
        this.pesticideNameList.splice(this.pesticideNameList.length-2, 2);//将多出的两列除去
        this.loading = false;

        if(this.returnFeedBack &&response.msg!=null && response.msg!=""){
          this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "返回信息", { dangerouslyUseHTMLString: true });
          this.returnFeedBack=false;
        }
      });
    },
    // 表单重置
    reset() {
      this.form = {
        interStandId: null,
        standard: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        sourceArea: null,


        samplingQuantity: null,
        qualifiedNumber: null,
        qualificationRate: null,
        createdDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.pesticideNameList=[];//目前已知要在此清空才会更改内容
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.init();
      this.dateRange=[];
      this.resetForm("queryForm");
      this.handleQuery();
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outStandCompliance/export', {
        ...this.queryParams
      }, `outStandComplianceRecords_${new Date().getTime()}.xlsx`)
    },
  }
};
</script>
