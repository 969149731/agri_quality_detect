<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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


    <el-table
      v-if="!peopleTagNumLoading"
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
      <el-table-column label="合计" prop="StandardLable" width="100px" align="center">
        <el-table-column label="抽样数" prop="totalSamplingCount"/>
        <el-table-column label="合格数" prop="totalQualifiedCount"/>
        <el-table-column label="合格率(%)" prop="totalQualificationRate"/>
      </el-table-column>
    </el-table>

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
import * as XLSX from "xlsx";
import * as XLSXS from "xlsx-style";
import FileSaver from 'file-saver'
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
      let workSheet = XLSX.utils.table_to_sheet(document.querySelector("#table1"));
      let bookNew = XLSX.utils.book_new();
      let header=[];

      // 在这里添加样式代码
      for (const key in workSheet) {
        if (workSheet[key] instanceof Object) {
          workSheet[key].s = {
            alignment: {
              vertical: 'center',
              horizontal: 'center',
              indent: 0,
              wrapText: true
            },
            font: {
              name: '宋体',
              sz: 10,
              color: { rgb: '#FF000000' },
              bold: false,
              italic: false,
              underline: false
            },
            border: {
              top: { style: 'thin' },
              bottom: { style: 'thin' },
              left: { style: 'thin' },
              right: { style: 'thin' }
            }
          }
        }
      }

      //默认表头合并
      header.push({ s: { r: 0, c: 0 }, e: { r: 1, c: 1 } })
      header.push({ s: { r: 2, c: 0 }, e: { r: 2, c: 1 } })
      header.push({ s: { r: 3, c: 0 }, e: { r: 3, c: 1 } })
      header.push({ s: { r: 4, c: 0 }, e: { r: 9, c: 0 } })

      console.log("打印长度",this.pesticideNameList.length)
      //农药表表头合并
      for(let i=2;i<this.pesticideNameList.length+2;i++){
        header.push({s:{r:0,c:i},e:{r:1,c:i}});
      }
      let name = '各抽样环节合格率表' + '.xlsx'
      workSheet['!merges'] = header;
      XLSX.utils.book_append_sheet(bookNew, workSheet, name+"簿") // 工作簿名称

      var wopts = {
        bookType: "xlsx", // 要生成的文件类型
        bookSST: false, // 是否生成Shared String Table，官方解释是，如果开启生成速度会下降，但在低版本IOS设备上有更好的兼容性
        type: "binary",
      };
      let wbout = XLSXS.write(bookNew, {
        bookType: 'xlsx',
        bookSST: false,
        type: 'binary',
      })
      // XLSXS.writeFile(bookNew, '水果禁用表', wopts);
      FileSaver.saveAs(
        new Blob([s2ab(wbout)], {
          type: 'application/octet-stream'
        }),
        name // 保存的文件名
      )
      // 工具方法
      function s2ab(s) {
        var buf = new ArrayBuffer(s.length)
        var view = new Uint8Array(buf)
        for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xff
        return buf
      }
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
      if(rowIndex=== 0 || rowIndex=== 7 ||rowIndex=== 8 || rowIndex=== 9){
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
