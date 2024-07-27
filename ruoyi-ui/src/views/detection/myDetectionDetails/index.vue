<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="样品编号" prop="sampleCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.sampleCode"-->
      <!--          placeholder="请输入样品编号"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="逻辑外键，样品名称，关联蔬果表" prop="vegFruName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegFruName"-->
      <!--          placeholder="请输入逻辑外键，样品名称，关联蔬果表"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样地点" prop="samplingLocation">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingLocation"-->
      <!--          placeholder="请输入抽样地点"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样地点-省份" prop="samplingLocationProvince">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingLocationProvince"-->
      <!--          placeholder="请输入抽样地点-省份"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样地点-市" prop="samplingLocationCity">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingLocationCity"-->
      <!--          placeholder="请输入抽样地点-市"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样地点-县" prop="samplingLocationCounty">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingLocationCounty"-->
      <!--          placeholder="请输入抽样地点-县"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="检测单位" prop="detectLocation">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.detectLocation"-->
      <!--          placeholder="请输入检测单位"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样数量" prop="samplingQuantity">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingQuantity"-->
      <!--          placeholder="请输入抽样数量"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样基数" prop="samplingBase">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingBase"-->
      <!--          placeholder="请输入抽样基数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样日期" prop="samplingDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.samplingDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择抽样日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="企业名称/农户" prop="enterpriseName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enterpriseName"-->
      <!--          placeholder="请输入企业名称/农户"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="企业属性" prop="enterpriseAttribute">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enterpriseAttribute"-->
      <!--          placeholder="请输入企业属性"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="企业信用代码/身份证号" prop="enterpriseCreditIdCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.enterpriseCreditIdCode"-->
      <!--          placeholder="请输入企业信用代码/身份证号"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="溯源省" prop="tracingProvince">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.tracingProvince"-->
      <!--          placeholder="请输入溯源省"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="溯源市" prop="tracingCity">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.tracingCity"-->
      <!--          placeholder="请输入溯源市"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="溯源县" prop="tracingCounty">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.tracingCounty"-->
      <!--          placeholder="请输入溯源县"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="溯源场地" prop="tracingArea">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.tracingArea"-->
      <!--          placeholder="请输入溯源场地"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="国家标准" prop="chinaStandard">-->
      <!--        <el-select v-model="queryParams.chinaStandard" placeholder="请选择国家标准" clearable>-->
      <!--          <el-option-->
      <!--            v-for="dict in dict.type.pass_or_not"-->
      <!--            :key="dict.value"-->
      <!--            :label="dict.label"-->
      <!--            :value="dict.value"-->
      <!--          />-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
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
      <!--      <el-form-item label="上传数据用户名" prop="userName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userName"-->
      <!--          placeholder="请输入上传数据用户名"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->


      <el-form-item label="上传数据批次编号" prop="batchId" label-width="100">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入上传数据批次编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据导入日期" prop="createdAt" label-width="100">
        <el-date-picker clearable
                        v-model="queryParams.createdAt"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择数据导入日期">
        </el-date-picker>
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
<!--          v-hasPermi="['detection:myDetectionDetails:add']"-->
<!--        >新增-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['detection:myDetectionDetails:edit']"-->
<!--        >修改-->
<!--        </el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['detection:myDetectionDetails:remove']"
        >删除
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['detection:myDetectionDetails:export']"-->
<!--        >导出-->
<!--        </el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="myDetectionDetailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
<!--      <el-table-column label="主键，自增长" align="center" prop="citySampleTestDetailsId"/>-->
      <el-table-column label="上传数据批次编号" align="center" prop="batchId"  width="156"/>
<!--      <el-table-column label="上传数据用户" align="center" prop="userName"/>-->
      <el-table-column label="数据导入时间" align="center" prop="createdAt" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="样品编号" align="center" prop="sampleCode"/>
      <el-table-column label="样品名称" align="center" prop="vegFruName"/>
      <el-table-column label="抽样地点" align="center" prop="samplingLocation"/>
      <el-table-column label="抽样地点-省份" align="center" prop="samplingLocationProvince"/>
      <el-table-column label="抽样地点-市" align="center" prop="samplingLocationCity"/>
      <el-table-column label="抽样地点-县" align="center" prop="samplingLocationCounty"/>
<!--      <el-table-column label="检测单位" align="center" prop="detectLocation"/>-->
<!--      <el-table-column label="抽样数量" align="center" prop="samplingQuantity"/>-->
<!--      <el-table-column label="抽样基数" align="center" prop="samplingBase"/>-->
      <el-table-column label="抽样环节" align="center" prop="samplingStageType"/>
      <el-table-column label="抽样日期" align="center" prop="samplingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.samplingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="企业名称/农户" align="center" prop="enterpriseName"/>
      <el-table-column label="企业属性" align="center" prop="enterpriseAttribute"/>
      <el-table-column label="企业信用代码/身份证号" align="center" prop="enterpriseCreditIdCode"/>
      <el-table-column label="溯源省" align="center" prop="tracingProvince"/>
      <el-table-column label="溯源市" align="center" prop="tracingCity"/>
      <el-table-column label="溯源县" align="center" prop="tracingCounty"/>
      <el-table-column label="溯源场地" align="center" prop="tracingArea"/>
      <el-table-column label="国家标准" align="center" prop="chinaStandard">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.chinaStandard"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="检测结果" align="center" prop="result" width="300px">-->
<!--        <template slot-scope="props">-->
<!--          <el-table :data="props.row.agriPesticideDetResultList">-->
<!--            <el-table-column label="农药名" align="center" prop="pesticideName">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="检测值 mg/kg" align="center" prop="pesticideDetValue">-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <!--      <el-table-column label="CAC标准" align="center" prop="cacStandard">-->
      <!--        <template slot-scope="scope">-->
      <!--          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.cacStandard"/>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="日本标准" align="center" prop="japanStandard">-->
      <!--        <template slot-scope="scope">-->
      <!--          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.japanStandard"/>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="欧盟标准" align="center" prop="euStandard">-->
      <!--        <template slot-scope="scope">-->
      <!--          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.euStandard"/>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="美国标准" align="center" prop="usStandard">-->
      <!--        <template slot-scope="scope">-->
      <!--          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.usStandard"/>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="韩国标准" align="center" prop="koreaStandard">-->
      <!--        <template slot-scope="scope">-->
      <!--          <dict-tag :options="dict.type.pass_or_not" :value="scope.row.koreaStandard"/>-->
      <!--        </template>-->
      <!--      </el-table-column>-->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['detection:myDetectionDetails:edit']"-->
<!--          >修改-->
<!--          </el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['detection:myDetectionDetails:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改我的导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="样品编号" prop="sampleCode">
          <el-input v-model="form.sampleCode" placeholder="请输入样品编号"/>
        </el-form-item>
        <el-form-item label="逻辑外键，样品名称，关联蔬果表" prop="vegFruName">
          <el-input v-model="form.vegFruName" placeholder="请输入逻辑外键，样品名称，关联蔬果表"/>
        </el-form-item>
        <el-form-item label="抽样地点" prop="samplingLocation">
          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点"/>
        </el-form-item>
        <el-form-item label="抽样地点-省份" prop="samplingLocationProvince">
          <el-input v-model="form.samplingLocationProvince" placeholder="请输入抽样地点-省份"/>
        </el-form-item>
        <el-form-item label="抽样地点-市" prop="samplingLocationCity">
          <el-input v-model="form.samplingLocationCity" placeholder="请输入抽样地点-市"/>
        </el-form-item>
        <el-form-item label="抽样地点-县" prop="samplingLocationCounty">
          <el-input v-model="form.samplingLocationCounty" placeholder="请输入抽样地点-县"/>
        </el-form-item>
        <el-form-item label="检测单位" prop="detectLocation">
          <el-input v-model="form.detectLocation" placeholder="请输入检测单位"/>
        </el-form-item>
        <el-form-item label="抽样数量" prop="samplingQuantity">
          <el-input v-model="form.samplingQuantity" placeholder="请输入抽样数量"/>
        </el-form-item>
        <el-form-item label="抽样基数" prop="samplingBase">
          <el-input v-model="form.samplingBase" placeholder="请输入抽样基数"/>
        </el-form-item>
        <el-form-item label="抽样日期" prop="samplingDate">
          <el-date-picker clearable
                          v-model="form.samplingDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择抽样日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="企业名称/农户" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业名称/农户"/>
        </el-form-item>
        <el-form-item label="企业属性" prop="enterpriseAttribute">
          <el-input v-model="form.enterpriseAttribute" placeholder="请输入企业属性"/>
        </el-form-item>
        <el-form-item label="企业信用代码/身份证号" prop="enterpriseCreditIdCode">
          <el-input v-model="form.enterpriseCreditIdCode" placeholder="请输入企业信用代码/身份证号"/>
        </el-form-item>
        <el-form-item label="溯源省" prop="tracingProvince">
          <el-input v-model="form.tracingProvince" placeholder="请输入溯源省"/>
        </el-form-item>
        <el-form-item label="溯源市" prop="tracingCity">
          <el-input v-model="form.tracingCity" placeholder="请输入溯源市"/>
        </el-form-item>
        <el-form-item label="溯源县" prop="tracingCounty">
          <el-input v-model="form.tracingCounty" placeholder="请输入溯源县"/>
        </el-form-item>
        <el-form-item label="溯源场地" prop="tracingArea">
          <el-input v-model="form.tracingArea" placeholder="请输入溯源场地"/>
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
        <el-form-item label="上传数据用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入上传数据用户名"/>
        </el-form-item>
        <el-form-item label="记录创建时间" prop="createdAt">
          <el-date-picker clearable
                          v-model="form.createdAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择记录创建时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">农药检测结果信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddagriPesticideDetResult">添加
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteagriPesticideDetResult">
              删除
            </el-button>
          </el-col>
        </el-row>
        <el-table :data="agriPesticideDetResultList" :row-class-name="rowagriPesticideDetResultIndex"
                  @selection-change="handleagriPesticideDetResultSelectionChange" ref="agriPesticideDetResult">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="农药名" prop="pesticideName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.pesticideName" placeholder="请输入农药名"/>
            </template>
          </el-table-column>
          <el-table-column label="检测值" prop="pesticideDetValue" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.pesticideDetValue" placeholder="请输入检测值"/>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMyDetectionDetails,
  getMyDetectionDetails,
  delMyDetectionDetails,
  addMyDetectionDetails,
  updateMyDetectionDetails
} from "@/api/detection/myDetectionDetails";

export default {
  name: "MyDetectionDetails",
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
      // 我的导入表格数据
      myDetectionDetailsList: [],
      // 农药检测结果表格数据
      agriPesticideDetResultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1000,
        sampleCode: null,
        vegFruName: null,
        samplingLocation: null,
        samplingLocationProvince: null,
        samplingLocationCity: null,
        samplingLocationCounty: null,
        detectLocation: null,
        samplingQuantity: null,
        samplingBase: null,
        samplingStageType: null,
        samplingDate: null,
        enterpriseName: null,
        enterpriseAttribute: null,
        enterpriseCreditIdCode: null,
        tracingProvince: null,
        tracingCity: null,
        tracingCounty: null,
        tracingArea: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        userName: null,
        createdAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询我的导入列表 */
    getList() {
      this.loading = true;
      listMyDetectionDetails(this.queryParams).then(response => {
        this.myDetectionDetailsList = response.rows;
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
        samplingLocationProvince: null,
        samplingLocationCity: null,
        samplingLocationCounty: null,
        detectLocation: null,
        samplingQuantity: null,
        samplingBase: null,
        samplingStageType: null,
        samplingDate: null,
        enterpriseName: null,
        enterpriseAttribute: null,
        enterpriseCreditIdCode: null,
        tracingProvince: null,
        tracingCity: null,
        tracingCounty: null,
        tracingArea: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        userName: null,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.citySampleTestDetailsId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加我的导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const citySampleTestDetailsId = row.citySampleTestDetailsId || this.ids
      getMyDetectionDetails(citySampleTestDetailsId).then(response => {
        this.form = response.data;
        this.agriPesticideDetResultList = response.data.agriPesticideDetResultList;
        this.open = true;
        this.title = "修改我的导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.agriPesticideDetResultList = this.agriPesticideDetResultList;
          if (this.form.citySampleTestDetailsId != null) {
            updateMyDetectionDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyDetectionDetails(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除我的导入编号为"' + citySampleTestDetailsIds + '"的数据项？').then(function () {
        return delMyDetectionDetails(citySampleTestDetailsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 农药检测结果序号 */
    rowagriPesticideDetResultIndex({row, rowIndex}) {
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
        this.agriPesticideDetResultList = agriPesticideDetResultList.filter(function (item) {
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
      this.download('detection/myDetectionDetails/export', {
        ...this.queryParams
      }, `myDetectionDetails_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
