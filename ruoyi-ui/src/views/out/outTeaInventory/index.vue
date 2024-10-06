<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="蔬菜类序号，用于标识蔬菜类别" prop="vegetableSeqNo">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegetableSeqNo"-->
      <!--          placeholder="请输入蔬菜类序号，用于标识蔬菜类别"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="蔬菜类详细名" prop="vegetableDetailName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegetableDetailName"-->
      <!--          placeholder="请输入蔬菜类详细名"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="蔬菜类数量" prop="vegetableQuantity">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegetableQuantity"-->
      <!--          placeholder="请输入蔬菜类数量"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果类序号，用于标识水果类别" prop="fruitSeqNo">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruitSeqNo"-->
      <!--          placeholder="请输入水果类序号，用于标识水果类别"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果类详细名" prop="fruitDetailName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruitDetailName"-->
      <!--          placeholder="请输入水果类详细名"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果类数量" prop="fruitQuantity">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruitQuantity"-->
      <!--          placeholder="请输入水果类数量"-->
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outTeaInventory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <div >
      <el-row>
        <el-col :span="6">
          <div class="tree-container">
            <el-tree :data="tree" :props="defaultProps" @node-click="handleNodeClick">
              <span slot-scope="{ node, data }">
                {{ data.detailType }}  - 总数：{{ data.sampleCount ? data.sampleCount : data.sampleDetailCount }}
              </span>
            </el-tree>
          </div>
        </el-col>
        <el-col :span="12">
          <el-table v-loading="loading" :data="outTeaInventoryList">
            <el-table-column type="index" label="序号" width="200" align="center"/>
            <el-table-column label="茶叶种类" align="center" width="200" prop="vegFruName"/>
            <el-table-column label="数量" align="center" width="200" prop="sampleDetailCount"/>
          </el-table>
        </el-col>
      </el-row>
    </div>


    <!-- 添加或修改茶叶种类及数量对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="蔬菜类序号，用于标识蔬菜类别" prop="vegetableSeqNo">
          <el-input v-model="form.vegetableSeqNo" placeholder="请输入蔬菜类序号，用于标识蔬菜类别" />
        </el-form-item>
        <el-form-item label="蔬菜类详细名" prop="vegetableDetailName">
          <el-input v-model="form.vegetableDetailName" placeholder="请输入蔬菜类详细名" />
        </el-form-item>
        <el-form-item label="蔬菜类数量" prop="vegetableQuantity">
          <el-input v-model="form.vegetableQuantity" placeholder="请输入蔬菜类数量" />
        </el-form-item>
        <el-form-item label="水果类序号，用于标识水果类别" prop="fruitSeqNo">
          <el-input v-model="form.fruitSeqNo" placeholder="请输入水果类序号，用于标识水果类别" />
        </el-form-item>
        <el-form-item label="水果类详细名" prop="fruitDetailName">
          <el-input v-model="form.fruitDetailName" placeholder="请输入水果类详细名" />
        </el-form-item>
        <el-form-item label="水果类数量" prop="fruitQuantity">
          <el-input v-model="form.fruitQuantity" placeholder="请输入水果类数量" />
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
import { listOutTeaInventory, getOutTeaInventory, delOutTeaInventory, addOutTeaInventory, updateOutTeaInventory,
  samplingAddressProvince,
  findBySamplingProvinceCode,
  findBySamplingCityCode, } from "@/api/out/outTeaInventory";
import {listOutFruInventory} from "@/api/out/outFruInventory";

export default {
  name: "OutTeaInventory",
  dicts: ['sampling_type'],
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
      // 茶叶种类及数量表格数据
      outTeaInventoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vegetableSeqNo: null,
        vegetableDetailName: null,
        vegetableQuantity: null,
        fruitSeqNo: null,
        fruitDetailName: null,
        fruitQuantity: null,
        createdDate: null,


        //对应到实体类的名字
        samplingLocationProvince:null,
        samplingLocationCity:null,
        samplingLocationCounty:null,


        //装对象的（v-model下拉框对象）
        samplingProvince: {code:"450000",name:"广西壮族自治区"},
        samplingCity:null,
        samplingTown:null,

        samplingStageType:null,
        enterpriseName:null,
        enterpriseAttribute:null,
        enterpriseCreditIdCode:null,


      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 菜单树
      tree: [],
      defaultProps: {
        children: 'detailList',
        label: 'detailType'
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


    /** 查询茶叶种类及数量列表 */
    getList() {
      this.loading = true;
      this.outTeaInventoryList = []
      listOutTeaInventory(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        response.rows.forEach(item =>{
          item.detailList.forEach(item =>{
            item.detailType = item.vegFruName
          })
        })
        this.tree = response.rows
        this.loading = false;
      });
    },
    // 菜单选择
    handleNodeClick(val){
      if(val.detailList){
        this.outTeaInventoryList = val.detailList
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        teaInventoryId: null,
        vegetableSeqNo: null,
        vegetableDetailName: null,
        vegetableQuantity: null,
        fruitSeqNo: null,
        fruitDetailName: null,
        fruitQuantity: null,
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.init();
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加茶叶种类及数量";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teaInventoryId = row.teaInventoryId || this.ids
      getOutTeaInventory(teaInventoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改茶叶种类及数量";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teaInventoryId != null) {
            updateOutTeaInventory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutTeaInventory(this.form).then(response => {
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
      const teaInventoryIds = row.teaInventoryId || this.ids;
      this.$modal.confirm('是否确认删除茶叶种类及数量编号为"' + teaInventoryIds + '"的数据项？').then(function() {
        return delOutTeaInventory(teaInventoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outTeaInventory/export', {
        ...this.queryParams
      }, `outTeaInventory_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.tree-container {
  max-height: 700px !important;
  overflow-y: auto;
}
</style>
