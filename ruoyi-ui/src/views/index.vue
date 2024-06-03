<template>
  <div >
    <div>
      <el-row :gutter="2">
        <el-col :span="8">
          <!--用户信息-->
          <div @click="mystest">{{Test}}</div>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>个人信息</span>
            </div>
            <div>
              <div class="text-center">
                <userAvatar />
              </div>
              <ul class="list-group list-group-striped">
                <li class="list-group-item">
                  <svg-icon icon-class="user" />用户名称
                  <div class="pull-right">{{ user.userName }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="phone" />手机号码
                  <div class="pull-right">{{ user.phonenumber }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="email" />用户邮箱
                  <div class="pull-right">{{ user.email }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="tree" />所属部门
                  <div class="pull-right" v-if="user.dept">{{ user.dept.deptName }} / {{ postGroup }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="peoples" />所属角色
                  <div class="pull-right">{{ roleGroup }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="date" />创建日期
                  <div class="pull-right">{{ user.createTime }}</div>
                </li>
              </ul>
            </div>
          </el-card>
        </el-col>
        <!--介绍和入口-->
        <el-col :span="16">
          <el-card shadow= 'hover' class="introduceCard"><div style="white-space: pre-wrap;">{{Introduce}}</div></el-card>
          <el-card shadow= 'hover' class="GuideCard">
            <el-row class="GuideRow" type="flex">
              <el-col :span="2" ><div class="Label"><div class="Label_div">快<br>速<br>入<br>口</div></div></el-col>
              <el-col :span="22" gutter="1">
                <el-button v-for="item in countData" :key="item.name" class="EntranceButton" @click.native="IntroduceClickEvent(item.name)">
                  {{item.name}}
                </el-button>
              </el-col>


            </el-row>

          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-card class="charts" >
          <!--          <h1> 一些图表</h1>-->
          <div class= "num graph" >
            <el-card style="width: 34%;height: 300px;marginRight: 1%">
              <div style="width: 80%;height: 265px;" ref="pieEcharts"></div>
            </el-card>
            <el-card style="width: 34%;height: 300px;"><div class="Calendar_div"><el-calendar v-model="value" class="calendar"></el-calendar></div></el-card>
          </div>
        </el-card>
      </el-row>
    </div>

    <div class="tables" shadow= 'hover' border style="width: 100%">
      <el-card shadow= 'hover' class="tableInfo">
        <div class="tableTitle">速览·例行监测</div>
        <el-table v-loading="loading" :data="outDlDetectRecordsList">
          <el-table-column label="被检单位" align="center" width="500" prop="samplingLocation" />
          <el-table-column label="蔬菜抽样数(个)"  align="center" prop="vegSamplingCount" />
          <el-table-column label="蔬菜合格数(个)"  align="center" prop="vegQualifiedCount" />
          <el-table-column label="蔬菜合格率(%)"  align="center" prop="vegPassRate" />
          <el-table-column label="   " width="70" align="center" prop="" />
          <el-table-column label="水果抽样数(个)"  align="center" prop="fruSamplingCount" />
          <el-table-column label="水果合格数(个)" align="center" prop="fruQualifiedCount" />
          <el-table-column label="水果合格率(%)"  align="center" prop="fruPassRate" />
          <el-table-column label="   " width="70" align="center" prop="" />
          <el-table-column label="总抽样数(个)"  align="center" prop="allSamplingCount" />
          <el-table-column label="总合格数(个)"  align="center" prop="allQualifiedCount" />
          <el-table-column label="总合格率(%)"  align="center" prop="allPassRate" />
        </el-table>

      </el-card>
      <el-card shadow= 'hover' class="tableInfo">
        <div class="tableTitle">速览·定性监测</div>
        <el-table v-loading="loading" :data="outDxDetectRecordsList">
          <el-table-column type="index" label="序号" width="55" align="center" />
          <el-table-column label="检测单位名称" align="center" prop="detectLocation" />
          <el-table-column label="第一个月" align="center" width="300px">
            <el-table-column label="抽样数" align="center" prop="month1SamplingCount"></el-table-column>
            <el-table-column label="合格数" align="center" prop="month1QualifiedCount"></el-table-column>
            <el-table-column label="合格率" align="center" prop="month1PassRate"></el-table-column>
          </el-table-column>

          <el-table-column label="第二个月" align="center" width="300px">
            <el-table-column label="抽样数" align="center" prop="month2SamplingCount"></el-table-column>
            <el-table-column label="合格数" align="center" prop="month2QualifiedCount"></el-table-column>
            <el-table-column label="合格率" align="center" prop="month2PassRate"></el-table-column>
          </el-table-column>

          <el-table-column label="第三个月" align="center" width="300px">
            <el-table-column label="抽样数" align="center" prop="month3SamplingCount"></el-table-column>
            <el-table-column label="合格数" align="center" prop="month3QualifiedCount"></el-table-column>
            <el-table-column label="合格率" align="center" prop="month3PassRate"></el-table-column>
          </el-table-column>

          <el-table-column label="该季度总计" align="center" width="300px">
            <el-table-column label="总抽样数" align="center" prop="allSamplingCount" />
            <el-table-column label="总合格数" align="center" prop="allQualifiedCount" />
            <el-table-column label="总合格率" align="center" prop="allPassRate" />、
          </el-table-column>


        </el-table>

      </el-card>



    </div>

  </div>

</template>


<script>
import Url from"../assets/images/pay.png"
import { ref } from 'vue'
import * as echarts from 'echarts';//表格

import { listPost, getPost, delPost, addPost, updatePost } from "@/api/system/post";
import router from "@/router";
import store from "@/store";

import userAvatar from "./system/user/profile/userAvatar";
import userInfo from "./system/user/profile/userInfo";
import { getUserProfile } from "@/api/system/user";

//定量监测API
import { listOutDlDetectRecords} from "@/api/out/outDlDetectRecords";
//定性监测API
import { listOutDxDetectRecords} from "@/api/out/outDxDetectRecords";
import {listOutSampleStageType} from "@/api/out/outSampleStageType";


export default {
  name: "Index",
  components: { userAvatar, userInfo},
  data() {
    return {
      // 版本号
      version: "3.8.7",
      Test:"",
      Introduce:"     农产品质量安全风险监测，是指为了掌握农产品质量安全状况和开展农产品质量安全风险评估，系统和持续地对影响农产品质量安全的有害因素进行检验、分析和评价的活动，包括农产品质量安全例行监测、普查和专项监测等内容。",
      month:"七月",
      fastDetectData:[],/*快速监测数据，通过函数获取，下同*/
      routineDetectData: [],/*快速监测数据*/
      countData:[
        {
          name: '定量监测',
          value: 1200,
          icon: 'success',
          color: '#2ec7c9',
        },
        {
          name: '定性监测',
          value: 1200,
          icon: 'star-on',
          color: '#ffb980'
        },
        {
          name: '定量检测明细',
          value: 1200,
          icon: 's-goods',
          color: '#5ab1ef'
        },
        {
          name: '各类蔬菜水果合格率',
          value: 1200,
          icon: 'success',
          color: '#2ec7c9'
        },
        {
          name: '各抽样环节合格率',
          value: 1200,
          icon: 'star-on',
          color: '#ffb980'
        },
        {
          name: '无判定标准检出',
          value: 1200,
          icon: 'star-on',
          color: '#ffb980'
        },
        {
          name: '参照国家或国际标准合格率',
          value: 1200,
          icon: 'star-on',
          color: '#ffb980'
        },
      ],

      //个人信息展示卡
      userCardClass:{},
      AvatarUrl: Url,
      options: {//头像
        img: store.getters.avatar,  //裁剪图片的地址
        autoCrop: true,             // 是否默认生成截图框
        autoCropWidth: 200,         // 默认生成截图框宽度
        autoCropHeight: 200,        // 默认生成截图框高度
        fixedBox: true,             // 固定截图框大小 不允许改变
        outputType:"png",           // 默认生成截图为PNG格式
        filename: 'avatar'          // 文件名称
      },
      user: {},
      roleGroup: {},
      postGroup: {},
      activeTab: "userinfo",

      // 定量监测结果汇总表格数据
      outDlDetectRecordsList: [],

      // 定性监测结果汇总表格数据
      outDxDetectRecordsList: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        detectLocation: null,
        samplingDate: null,
        samplingYear: null,
        samplingMonth: null,
        samplingCount: null,
        qualifiedCount: null,
        passRate: null,
        createdDate: null,
        year: '2022', // 设置默认值为 2024
        season:'3'//设置默认值为3
      },
      pieData:{},

    }
  },
  created() {
    this.getList();
    this.getUser();
    // this.Test="MyTest()";
    this.getFastDataList();//获取数据
    this.getRoutineDataList();//
  },
  methods:{
    /** 查询定性监测结果汇总列表 */
    getList(){
      this.loading = true;
      // //定量监测
      // listOutDlDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
      //   this.outDlDetectRecordsList = response.rows;
      //   this.outDlDetectRecordsList=this.outDlDetectRecordsList.slice(1,10);
      //   this.total = response.total;
      // });
      // //定性监测
      // listOutDxDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
      //   this.outDxDetectRecordsList = response.rows;
      // });
      //获取被抽检环节数据
      listOutSampleStageType(this.queryParams).then(response => {
        this.pieData = response.rows;
        this.initPieEcharts();
      });
      this.loading = false;
    },
    //饼图
    initPieEcharts () {
      let p = new Promise((resolve) => {
        resolve()
      })
      let data={};

      //创建一个数组
      let dataArray = []
      for (let i = 0; i < this.pieData.length; i++) {
        let a = this.pieData[i];
        if(a.unitNum!=0 && a.samplingStageType!="合计"){
          //创建一个对象
          let vote = {}
          vote.value=a.unitNum;
          vote.name=a.samplingStageType;
          dataArray.push(vote)
        }
      }

      //然后异步执行echarts的初始化函数
      p.then(() => {
        let myChart = echarts.init(this.$refs.pieEcharts);
        let option= {
          title: {
            show: true,
            text: "被抽检环节数量统计",
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '0%',
            right: 'right'
          },
          series: [
            {
              name: '订单信息',
              type: 'pie',
              radius: ['20%', '65%'],
              avoidLabelOverlap: false,
              label: {
                show:true,
                position: 'left'
              },
              labelLine: {
                show: false,
              },
              data: dataArray
            }
          ]
        }
        myChart.setOption(option);
      })
    },
    IntroduceClickEvent(key){
      console.log(key);
      switch (key){
        case("定量监测"):
          this.$router.push('/detectInf/outDlDetectRecords');
          break;
        case("定性监测"):
          this.$router.push('/detectInf/outDxDetectRecords');
          break;
        case("定量检测导入明细"):
          this.$router.push('/detectInf/detectionDetails');
          break;
        case("各类蔬菜水果合格率"):
          this.$router.push('/resultsum/outFruVegQualification');
          break;
        case("各抽样环节合格率"):
          this.$router.push('/resultsum/outSampleQuality');
          break;
        case("无判定标准检出"):
          this.$router.push('/resultsum/outUndeterminedStandDet');
          break;
        case("参照国家或国际标准合格率"):
          this.$router.push('/resultsum/outStandCompliance');
          break;
      }
      // this.$router.push('/register');
    },
    getFastDataList(){
      this.fastDetectData=getFastData();
    },
    getRoutineDataList(){
      this.routineDetectData=getRoutineData();
    },

    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },

    mystest(){
      this.Test=test();
    }
  },
}
</script>


<style scoped lang="scss">
.introduceCard {
  div{
    background-color: #dacfb3;
    font-size: 24px;
    color: #4c5862;
  }
}
.userCard{

}
.EntranceButton{
  font-weight: 900;
  font-size: large;
  color: #464058;
  background-color: #dff1e8;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 10px;
  .el-button--primary{
    font-size: large;
  }
}
.num{
  display: flex;
  flex-wrap: wrap;
}
.graph{
  margin: 0px 0 0 0;
}
.GuideCard{
  background-color: #a5b6cb;
  border-radius: 12px;
  margin: 5px;
}
.GuideRow{

  align-items: center;
  justify-items: center;
  flex-wrap: wrap;
  margin: 10px;
  /*height: auto;*/
}
/*  .FastEntranceCard{*/

/*  */
/*  .el-card__body {*/
/*    padding: 0px;*/
/*  }*/
/*}*/
.Label{
  align-items: center;
  align-content: center;
  padding: 0px;

  border-radius: 10px;
  font-weight: bolder;
  font-size: 16px;
  color: #464058;

  height: 120px;
  width: 100%;
  background: #a5b6cb;

}
.Label_div {
  display: flex;
  width:50px;
  height:120px;
  align-items: center;
  justify-content: center;
  br{
    horiz-align: center;
    vertical-align: center;
    color: white;
  }

  border-radius: 10px;
  background-color: #dff1e8;
}

.tables{
  margin: 20px 0 0 0;
  width: auto;
}
.tableInfo{/*字体*/
  margin: 20px 0 0 0;
}
.tableTitle{
  font-weight: 900;
  font-size: 24px;
  color: #203f79;
}
.important-font{
  /*大写，加粗*/
  font-weight: 900;
  font-size: 16px;
}
.secondary-font{
  font-weight:500;
  width: 120px;
  font-size: 16px;
  color: #202d53;
}

.login-info{
  height: 40px;
  text-align: left;
  color: #909399;
}

::v-deep .el-calendar-table  {
  .el-calendar-table td.is-selected {
    background-color: #3b7d91;
  }
  .el-calendar-table td .el-calendar-day:hover {
    background-color: #6fc26f !important;
  }
  .current.is-today {
    background: #9cd37c;
    color: #fff;
  }
  .el-calendar-day{
    font-size: small;
    width: 20px;
    height: 25px;
  }
}
</style>

