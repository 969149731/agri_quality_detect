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
          v-hasPermi="['out:outFruVegQualification:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outFruVegQualificationList" id="table1">
      <el-table-column label="蔬菜水果种类" prop="vegFruType" align="center"/>
      <el-table-column label="抽样数" prop="samplingNumber" align="center"/>
      <el-table-column label="合格数" prop="passNumber" align="center"/>
      <el-table-column label="合格率" prop="passRate" align="center"/>
      <el-table-column label="超标样品" prop="exceedingSamples" align="center"/>
      <el-table-column label="超标农药" prop="exceedingPesticides" align="center"/>
    </el-table>

  </div>
</template>

<script>
import { listOutFruVegQualification,samplingAddressProvince,findBySamplingProvinceCode,findBySamplingCityCode} from "@/api/out/outFruVegQualification";
export default {
  name: "OutFruVegQualification",
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
      // 各类蔬菜水果合格率情况表格数据
      outFruVegQualificationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vegFruType: null,
        samplingNumber: null,
        passNumber: null,
        passRate: null,
        exceedingSamples: null,
        exceedingPesticides: null,
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
    /** 查询各类蔬菜水果合格率情况列表 */
    getList() {
      this.loading = true;
      this.outFruVegQualificationList =[];
      listOutFruVegQualification(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.outFruVegQualificationList = response.rows;
        this.total = response.total;
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
        fruVegQualificationId: null,
        vegFruType: null,
        samplingNumber: null,
        passNumber: null,
        passRate: null,
        exceedingSamples: null,
        exceedingPesticides: null,
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
      this.download('out/outFruVegQualification/export', {
        ...this.queryParams
      }, `outFruVegQualificationDetRecords_${new Date().getTime()}.xlsx`)
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
    /*表头行的合并*/
    headerStyle({ row, column, rowIndex, columnIndex }) {
      const comStyle = {
        backgroundColor: "#428fd7",
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
<style scoped lang="scss">

//.el-table .cell {
//  white-space: pre-wrap;   /*这是重点。文本换行*/
//}

::v-deep .el-table .cell {
  white-space: pre-line;
}

</style>
