<template>
  <div >
    <div>
      <el-row :gutter="20">
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
        <el-col :span="16">
          <!--介绍和入口-->
          <el-card shadow= 'hover' class="introduceCard"><div style="white-space: pre-wrap;">{{Introduce}}</div></el-card>
          <el-card shadow= 'hover' class="GuideCard" >

            <el-row class="GuideRow" type="flex" :gutter="0">
              <el-col :span="2" align="center" ><div class="Label"><div>快<br>速<br>入<br>口</div></div></el-col>
              <div>
                <el-button v-for="item in countData" :key="item.name" class="EntranceButton" @click.native="IntroduceClickEvent(item.name)">
                  {{item.name}}
                </el-button>
              </div>

            </el-row>

          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-card class="charts" >
          <!--          <h1> 一些图表</h1>-->
          <div class= "num graph" >
            <el-card style="width: 34%;height: 265px;marginRight: 1%">
              <div style="width: 80%;height: 265px;" ref="pieEcharts">{{initPieEcharts()}}</div>
            </el-card>
            <el-card style="width:30%;height: 265px"><div style="height: 100px"><el-calendar v-model="value"></el-calendar></div></el-card>
          </div>
        </el-card>
      </el-row>
    </div>

    <div class="tables" shadow= 'hover' border style="width: 100%">
      <el-card shadow= 'hover' class="tableInfo">
        <div class="tableTitle">速览·例行监测</div>
        <el-table stripe :data="routineDetectData" >
          <el-table-column prop="id" label="序号" width="80" align="center"/>
          <el-table-column prop="unit" label="被检单位" width="80" align="center"/>

          <el-table-column prop="veg.Num" label="蔬菜抽样数" width="100" align="center"/>
          <el-table-column  label="按国家标准判定" width="180" align="center">
            <el-table-column prop="veg.qualifiedNum" label="合格数" width="100" align="center"/>
            <el-table-column prop="veg.qualifiedRate" label="合格率" width="100" align="center"/>
          </el-table-column>
          <el-table-column prop="fruit.Num" label="水果抽样数" width="100" align="center"/>
          <el-table-column  label="按国家标准判定" width="180" align="center">
            <el-table-column prop="fruit.qualifiedNum" label="合格数" width="100" align="center"/>
            <el-table-column prop="fruit.qualifiedRate" label="合格率" width="100" align="center"/>
          </el-table-column>
          <el-table-column prop="total.Num" label="总数" width="100" align="center"/>
          <el-table-column label="按国家标准判定" width="180" align="center">
            <el-table-column prop="total.qualifiedNum" label="合格数" width="100" align="center"/>
            <el-table-column prop="total.qualifiedRate" label="合格率" width="100" align="center"/>
          </el-table-column>
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

//定性监测API
import { listOutDxDetectRecords} from "@/api/out/outDxDetectRecords";
import resetPwd from "@/views/system/user/profile/resetPwd";
//引入api
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
          name: '快速检测',
          value: 1200,
          icon: 'success',
          color: '#2ec7c9',

        },
        {
          name: '例行检测',
          value: 1200,
          icon: 'star-on',
          color: '#ffb980'
        },
        {
          name: '色谱法定量监测',
          value: 1200,
          icon: 's-goods',
          color: '#5ab1ef'
        },
        {
          name: '蔬菜水果合格率',
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

    }
  },
  created() {
    this.getList();
    this.getUser();
    this.Test="MyTest()";
    this.getFastDataList();//获取数据
    this.getRoutineDataList();//
  },
  methods:{
    /** 查询定性监测结果汇总列表 */
    getList(){
      this.loading = true;
      // listOutDxDetectRecords(this.queryParams).then(response => {
      listOutDxDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.outDxDetectRecordsList = response.rows;
        this.loading = false;
      });
    },
    //饼图
    initPieEcharts () {
      let p = new Promise((resolve) => {
        resolve()
      })
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
              data: [
                { value: 199, name: '生产基地' },
                { value: 1, name: '批发市场' },
                { value: 51, name: '运输车' },
              ]
            }
          ]
        }
        myChart.setOption(option);
      })
    },
    IntroduceClickEvent(key){
      console.log(getFastDAta())
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

}
.GuideRow{

  align-items: center;
  justify-items: center;
  flex-wrap: wrap;
  /*height: auto;*/
}
/*  .FastEntranceCard{*/

/*  */
/*  .el-card__body {*/
/*    padding: 0px;*/
/*  }*/
/*}*/
.Label{
  display: flex;
  align-items: center;
  align-content: center;
  padding: 0px;

  border-radius: 10px;
  font-weight: bolder;
  font-size: 16px;
  color: #464058;

  height: 120px;
  width: 950px;
  background: #a5b6cb;
  div{
    border-radius: 10px;
    height: 130;
    width: 50px;
    background-color: #dff1e8;
    margin: 0px 0px 0px 10px;
  }
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
</style>

