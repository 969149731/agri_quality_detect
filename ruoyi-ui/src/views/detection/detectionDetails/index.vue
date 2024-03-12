<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="72px">
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
      <el-form-item label="抽样地点" prop="samplingLocation">
        <el-input
          v-model="queryParams.samplingLocation"
          placeholder="请输入抽样地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测单位" prop="detectLocation">
        <el-input
          v-model="queryParams.detectLocation"
          placeholder="请输入检测单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="抽样日期" prop="samplingDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.samplingDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择抽样日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="国家标准" prop="chinaStandard">
        <el-select v-model="queryParams.chinaStandard" placeholder="请选择国家标准" clearable>
          <el-option
            v-for="dict in dict.type.pass_or_not"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="CAC标准" prop="cacStandard">-->
<!--        <el-select v-model="queryParams.cacStandard" placeholder="请选择CAC标准" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.pass_or_not"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="日本标准" prop="japanStandard">-->
<!--        <el-select v-model="queryParams.japanStandard" placeholder="请选择日本标准" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.pass_or_not"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="欧盟标准" prop="euStandard">-->
<!--        <el-select v-model="queryParams.euStandard" placeholder="请选择欧盟标准" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.pass_or_not"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="美国标准" prop="usStandard">-->
<!--        <el-select v-model="queryParams.usStandard" placeholder="请选择美国标准" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.pass_or_not"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="韩国标准" prop="koreaStandard">-->
<!--        <el-select v-model="queryParams.koreaStandard" placeholder="请选择韩国标准" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.pass_or_not"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
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
          v-hasPermi="['detection:detectionDetails:add']"
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
          v-hasPermi="['detection:detectionDetails:edit']"
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
          v-hasPermi="['detection:detectionDetails:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"

        >导入</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['detection:detectionDetails:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detectionDetailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="citySampleTestDetailsId" />-->
      <el-table-column label="样品编号" align="center" prop="sampleCode" />
      <el-table-column label="样品名称" align="center" prop="vegFruName" />
      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />
      <el-table-column label="检测单位" align="center" prop="detectLocation" />
      <el-table-column label="抽样数量（kg/个数）" align="center" prop="samplingQuantity" />
      <el-table-column label="抽样基数" align="center" prop="samplingBase" />
      <el-table-column label="抽样环节" align="center" prop="samplingStageType" />
      <el-table-column label="抽样日期" align="center" prop="samplingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.samplingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="检测结果" align="center" prop="result" width="300px">
        <template slot-scope="props" >
          <el-table  :data="props.row.agriPesticideDetResultList">
            <el-table-column  label="农药名" align="center" prop="pesticideName">
            </el-table-column>
            <el-table-column  label="检测值 mg/kg" align="center" prop="pesticideDetValue">
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>

      <el-table-column label="国家标准" align="center" prop="chinaStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.chinaStandard"/>
        </template>
      </el-table-column>
      <el-table-column label="CAC标准" align="center" prop="cacStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.cacStandard"/>
        </template>
      </el-table-column>
      <el-table-column label="日本标准" align="center" prop="japanStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.japanStandard"/>
        </template>
      </el-table-column>
      <el-table-column label="欧盟标准" align="center" prop="euStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.euStandard"/>
        </template>
      </el-table-column>
      <el-table-column label="美国标准" align="center" prop="usStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.usStandard"/>
        </template>
      </el-table-column>
      <el-table-column label="韩国标准" align="center" prop="koreaStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.koreaStandard"/>
        </template>
      </el-table-column>



      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['detection:detectionDetails:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['detection:detectionDetails:remove']"
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

    <!-- 添加或修改各市样品检测结果详细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="样品编号" prop="sampleCode">
          <el-input v-model="form.sampleCode" placeholder="请输入样品编号" />
        </el-form-item>
        <el-form-item label="样品名称" prop="vegFruName">
          <el-input v-model="form.vegFruName" placeholder="请输入样品名称" />
        </el-form-item>
        <el-form-item label="抽样地点" prop="samplingLocation">
          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点" />
        </el-form-item>
        <el-form-item label="检测单位" prop="detectLocation">
          <el-input v-model="form.detectLocation" placeholder="请输入检测单位" />
        </el-form-item>
        <el-form-item label="抽样数量" prop="samplingQuantity">
          <el-input v-model="form.samplingQuantity" placeholder="请输入抽样数量（kg/个数）" />
        </el-form-item>
        <el-form-item label="抽样基数" prop="samplingBase">
          <el-input v-model="form.samplingBase" placeholder="请输入抽样基数" />
        </el-form-item>
        <el-form-item label="抽样环节" prop="samplingBase">
          <el-input v-model="form.samplingStageType" placeholder="请输入抽样环节" />
        </el-form-item>
        <el-form-item label="抽样日期" prop="samplingDate">
          <el-date-picker clearable
            v-model="form.samplingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择抽样日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="国家标准" prop="chinaStandard">
          <el-select v-model="form.chinaStandard" placeholder="请选择国家标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="CAC标准" prop="cacStandard">
          <el-select v-model="form.cacStandard" placeholder="请选择CAC标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日本标准" prop="japanStandard">
          <el-select v-model="form.japanStandard" placeholder="请选择日本标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="欧盟标准" prop="euStandard">
          <el-select v-model="form.euStandard" placeholder="请选择欧盟标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="美国标准" prop="usStandard">
          <el-select v-model="form.usStandard" placeholder="请选择美国标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="韩国标准" prop="koreaStandard">
          <el-select v-model="form.koreaStandard" placeholder="请选择韩国标准">
            <el-option
              v-for="dict in dict.type.pass_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>



        <el-divider content-position="center">农药检测结果信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddagriPesticideDetResult">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteagriPesticideDetResult">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="agriPesticideDetResultList" :row-class-name="rowagriPesticideDetResultIndex" @selection-change="handleagriPesticideDetResultSelectionChange" ref="agriPesticideDetResult">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="农药名" prop="pesticideName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.pesticideName" placeholder="请输入农药名" />
            </template>
          </el-table-column>
          <el-table-column label="检测值 mg/kg" prop="pesticideDetValue" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.pesticideDetValue" placeholder="请输入检测值 mg/kg" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 数据导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDetectionDetails, getDetectionDetails, delDetectionDetails, addDetectionDetails, updateDetectionDetails } from "@/api/detection/detectionDetails";
import { getToken } from "@/utils/auth";
import {listUser} from "@/api/system/user";

export default {
  name: "DetectionDetails",
  dicts: ['pass_or_not'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedagriPesticideDetResult: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 各市样品检测结果详细表格数据
      detectionDetailsList: [],
      // 农药检测结果表格数据
      agriPesticideDetResultList: [],
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
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        detectLocation: null,
        samplingStageType: null,
        samplingDate: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
      },
        // 数据导入参数
      upload: {
        // 是否显示弹出层（数据导入）
        open: false,
        // 弹出层标题（数据导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/detection/detectionDetails/importData"
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
    /** 查询各市样品检测结果详细列表 */
    getList() {
      this.loading = true;
      listDetectionDetails(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.detectionDetailsList = response.rows;
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
        citySampleTestDetailsId: null,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        detectLocation: null,
        samplingQuantity: null,
        samplingBase: null,
        samplingStageType: null,
        samplingDate: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        createdAt: null
      };
      this.agriPesticideDetResultList = [];
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
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.citySampleTestDetailsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加各市样品检测结果详细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const citySampleTestDetailsId = row.citySampleTestDetailsId || this.ids
      getDetectionDetails(citySampleTestDetailsId).then(response => {
        this.form = response.data;
        this.agriPesticideDetResultList = response.data.agriPesticideDetResultList;
        this.open = true;
        this.title = "修改各市样品检测结果详细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.agriPesticideDetResultList = this.agriPesticideDetResultList;
          if (this.form.citySampleTestDetailsId != null) {
            updateDetectionDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetectionDetails(this.form).then(response => {
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
      const citySampleTestDetailsIds = row.citySampleTestDetailsId || this.ids;
      this.$modal.confirm('是否确认删除各市样品检测结果详细编号为"' + citySampleTestDetailsIds + '"的数据项？').then(function() {
        return delDetectionDetails(citySampleTestDetailsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "数据导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('detection/detectionDetails/importTemplate', {
      }, `template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
	/** 农药检测结果序号 */
    rowagriPesticideDetResultIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 农药检测结果添加按钮操作 */
    handleAddagriPesticideDetResult() {
      let obj = {};
      obj.pesticideName = "";
      obj.pesticideDetValue = "";
      this.agriPesticideDetResultList.push(obj);
    },
    /** 农药检测结果删除按钮操作 */
    handleDeleteagriPesticideDetResult() {
      if (this.checkedagriPesticideDetResult.length == 0) {
        this.$modal.msgError("请先选择要删除的农药检测结果数据");
      } else {
        const agriPesticideDetResultList = this.agriPesticideDetResultList;
        const checkedagriPesticideDetResult = this.checkedagriPesticideDetResult;
        this.agriPesticideDetResultList = agriPesticideDetResultList.filter(function(item) {
          return checkedagriPesticideDetResult.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleagriPesticideDetResultSelectionChange(selection) {
      this.checkedagriPesticideDetResult = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('detection/detectionDetails/export', {
        ...this.queryParams
      }, `detectionDetails_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
