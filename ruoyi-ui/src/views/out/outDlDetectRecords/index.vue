<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="抽样地点" prop="samplingLocation">-->
<!--        <el-input-->
<!--          v-model="queryParams.samplingLocation"-->
<!--          placeholder="请输入抽样地点"-->
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
      <!--      <el-form-item label="抽样年份" prop="samplingYear">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingYear"-->
      <!--          placeholder="请输入抽样年份"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="抽样月份" prop="samplingMonth">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.samplingMonth"-->
      <!--          placeholder="请输入抽样月份"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="蔬菜抽样数" prop="vegSamplingCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegSamplingCount"-->
      <!--          placeholder="请输入蔬菜抽样数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="蔬菜合格数" prop="vegQualifiedCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegQualifiedCount"-->
      <!--          placeholder="请输入蔬菜合格数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="蔬菜合格率" prop="vegPassRate">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.vegPassRate"-->
      <!--          placeholder="请输入蔬菜合格率"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果抽样数" prop="fruSamplingCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruSamplingCount"-->
      <!--          placeholder="请输入水果抽样数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果合格数" prop="fruQualifiedCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruQualifiedCount"-->
      <!--          placeholder="请输入水果合格数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="水果合格率" prop="fruPassRate">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fruPassRate"-->
      <!--          placeholder="请输入水果合格率"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="总抽样数" prop="allSamplingCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.allSamplingCount"-->
      <!--          placeholder="请输入总抽样数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="总合格数" prop="allQualifiedCount">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.allQualifiedCount"-->
      <!--          placeholder="请输入总合格数"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="总合格率，百分比" prop="allPassRate">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.allPassRate"-->
      <!--          placeholder="请输入总合格率，百分比"-->
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

  <!--      <el-form-item label="检测地点" prop="detectLocation">-->
  <!--        &lt;!&ndash;        <el-input&ndash;&gt;-->
  <!--        &lt;!&ndash;          v-model="queryParams.detectLocation"&ndash;&gt;-->
  <!--        &lt;!&ndash;          placeholder="请输入检测单位"&ndash;&gt;-->
  <!--        &lt;!&ndash;          clearable&ndash;&gt;-->
  <!--        &lt;!&ndash;          @keyup.enter.native="handleQuery"&ndash;&gt;-->
  <!--        &lt;!&ndash;        />&ndash;&gt;-->
  <!--        <el-select v-model="queryParams.detectLocation" placeholder="城市" clearable>-->
  <!--          <el-option-->
  <!--            v-for="dict in dict.type.city"-->
  <!--            :key="dict.value"-->
  <!--            :label="dict.label"-->
  <!--            :value="dict.value"-->
  <!--          />-->
  <!--        </el-select>-->
  <!--      </el-form-item>-->


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

    <!--    <el-row :gutter="10" class="mb8"  justify="end" type="flex">-->
    <el-row :gutter="10" class="mb8" >

      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['out:outDlDetectRecords:add']"-->
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
      <!--          v-hasPermi="['out:outDlDetectRecords:edit']"-->
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
      <!--          v-hasPermi="['out:outDlDetectRecords:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->


      <el-col :span="2.1">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outDlDetectRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--          <el-table v-loading="loading" :data="outDlDetectRecordsList" @selection-change="handleSelectionChange">-->
    <!--            <el-table-column label="抽样地点" align="center" prop="samplingLocation" />-->
    <!--            <el-table-column label="蔬菜抽样数(个)" width="70" align="center" prop="vegSamplingCount" />-->
    <!--            <el-table-column label="蔬菜合格数(个)" width="70" align="center" prop="vegPassCount" />-->
    <!--            <el-table-column label="蔬菜合格率(%)" width="70" align="center" prop="vegPassRate" />-->
    <!--            <el-table-column label="   " width="70" align="center" prop="" />-->
    <!--            <el-table-column label="水果抽样数(个)" width="70" align="center" prop="fruSamplingCount" />-->
    <!--            <el-table-column label="水果合格数(个)" width="70" align="center" prop="fruPassCount" />-->
    <!--            <el-table-column label="水果合格率(%)" width="70" align="center" prop="fruPassRate" />-->
    <!--            <el-table-column label="   " width="70" align="center" prop="" />-->
    <!--            <el-table-column label="茶叶抽样数(个)" width="70" align="center" prop="teaSamplingCount" />-->
    <!--            <el-table-column label="茶叶合格数(个)" width="70" align="center" prop="teaPassCount" />-->
    <!--            <el-table-column label="茶叶合格率(%)" width="70" align="center" prop="teaPassRate" />-->
    <!--            <el-table-column label="   " width="70" align="center" prop="" />-->
    <!--            <el-table-column label="总抽样数(个)" width="70" align="center" prop="allSamplingCount" />-->
    <!--            <el-table-column label="总合格数(个)" width="70" align="center" prop="allPassCount" />-->
    <!--            <el-table-column label="总合格率(%)" width="70" align="center" prop="allPassRate" />-->
    <!--          </el-table>-->

<!--    <el-row>-->
<!--      <el-col :span="6">-->
<!--        <div class="tree-container">-->
<!--          <el-tree :data="tree" :props="defaultProps" @node-click="handleNodeClick" >-->
<!--              <span slot-scope="{ node, data }">-->
<!--                {{ data.samplingLocation }}  - 总数：{{data.allSamplingCount}}-->
<!--              </span>-->
<!--          </el-tree>-->
<!--        </div>-->
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--        <el-table v-loading="loading" :data="outDlDetectRecordsList">-->
<!--          <el-table-column label="抽样地点" align="center" prop="samplingLocation" />-->
<!--          <el-table-column label="蔬菜抽样数(个)" width="70" align="center" prop="vegSamplingCount" />-->
<!--          <el-table-column label="蔬菜合格数(个)" width="70" align="center" prop="vegPassCount" />-->
<!--          <el-table-column label="蔬菜合格率(%)" width="70" align="center" prop="vegPassRate" />-->
<!--          <el-table-column label="   " width="70" align="center" prop="" />-->
<!--          <el-table-column label="水果抽样数(个)" width="70" align="center" prop="fruSamplingCount" />-->
<!--          <el-table-column label="水果合格数(个)" width="70" align="center" prop="fruPassCount" />-->
<!--          <el-table-column label="水果合格率(%)" width="70" align="center" prop="fruPassRate" />-->
<!--          <el-table-column label="   " width="70" align="center" prop="" />-->
<!--          <el-table-column label="茶叶抽样数(个)" width="70" align="center" prop="teaSamplingCount" />-->
<!--          <el-table-column label="茶叶合格数(个)" width="70" align="center" prop="teaPassCount" />-->
<!--          <el-table-column label="茶叶合格率(%)" width="70" align="center" prop="teaPassRate" />-->
<!--          <el-table-column label="   " width="70" align="center" prop="" />-->
<!--          <el-table-column label="总抽样数(个)" width="70" align="center" prop="allSamplingCount" />-->
<!--          <el-table-column label="总合格数(个)" width="70" align="center" prop="allPassCount" />-->
<!--          <el-table-column label="总合格率(%)" width="70" align="center" prop="allPassRate" />-->
<!--        </el-table>-->
<!--      </el-col>-->
<!--    </el-row>-->


    <el-table
      :data="tree"
    style="width: 100%; margin-bottom: 20px;"
    v-loading="loading"
    row-key="id"
    border
    lazy
    :load="load"
    :tree-props="{children: 'dlDetRecordSampleRes', hasChildren: 'hasChildren'}">

    <el-table-column
      label="抽样地点"
      prop="samplingLocation"
      align="center"
      width="200">
    </el-table-column>

    <el-table-column
      label="蔬菜抽样数(个)"
      prop="vegSamplingCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="蔬菜合格数(个)"
      prop="vegPassCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="蔬菜合格率(%)"
      prop="vegPassRate"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="水果抽样数(个)"
      prop="fruSamplingCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="水果合格数(个)"
      prop="fruPassCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="水果合格率(%)"
      prop="fruPassRate"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="茶叶抽样数(个)"
      prop="teaSamplingCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="茶叶合格数(个)"
      prop="teaPassCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="茶叶合格率(%)"
      prop="teaPassRate"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="总抽样数(个)"
      prop="allSamplingCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="总合格数(个)"
      prop="allPassCount"
      align="center"
      width="70">
    </el-table-column>

    <el-table-column
      label="总合格率(%)"
      prop="allPassRate"
      align="center"
      width="70">
    </el-table-column>
    </el-table>



<!--    <el-table v-loading="loading" :data="outDlDetectRecordsList" @selection-change="handleSelectionChange">-->
<!--&lt;!&ndash;      <el-table-column type="selection" width="55" align="center" />&ndash;&gt;-->
<!--      &lt;!&ndash;      <el-table-column label="唯一标识符，自增" align="center" prop="recordDlId" />&ndash;&gt;-->
<!--      <el-table-column label="抽样地点" align="center" prop="samplingLocation" />-->
<!--      &lt;!&ndash;      <el-table-column label="抽样日期" align="center" prop="samplingDate" width="180">&ndash;&gt;-->
<!--      &lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--      &lt;!&ndash;          <span>{{ parseTime(scope.row.samplingDate, '{y}-{m}-{d}') }}</span>&ndash;&gt;-->
<!--      &lt;!&ndash;        </template>&ndash;&gt;-->
<!--      &lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--      &lt;!&ndash;      <el-table-column label="抽样年份" align="center" prop="samplingYear" />&ndash;&gt;-->
<!--      &lt;!&ndash;      <el-table-column label="抽样月份" align="center" prop="samplingMonth" />&ndash;&gt;-->
<!--      <el-table-column label="蔬菜抽样数(个)" width="70" align="center" prop="vegSamplingCount" />-->
<!--      <el-table-column label="蔬菜合格数(个)" width="70" align="center" prop="vegPassCount" />-->
<!--      <el-table-column label="蔬菜合格率(%)" width="70" align="center" prop="vegPassRate" />-->
<!--      <el-table-column label="   " width="70" align="center" prop="" />-->
<!--      <el-table-column label="水果抽样数(个)" width="70" align="center" prop="fruSamplingCount" />-->
<!--      <el-table-column label="水果合格数(个)" width="70" align="center" prop="fruPassCount" />-->
<!--      <el-table-column label="水果合格率(%)" width="70" align="center" prop="fruPassRate" />-->
<!--      <el-table-column label="   " width="70" align="center" prop="" />-->
<!--      <el-table-column label="茶叶抽样数(个)" width="70" align="center" prop="teaSamplingCount" />-->
<!--      <el-table-column label="茶叶合格数(个)" width="70" align="center" prop="teaPassCount" />-->
<!--      <el-table-column label="茶叶合格率(%)" width="70" align="center" prop="teaPassRate" />-->
<!--      <el-table-column label="   " width="70" align="center" prop="" />-->
<!--      <el-table-column label="总抽样数(个)" width="70" align="center" prop="allSamplingCount" />-->
<!--      <el-table-column label="总合格数(个)" width="70" align="center" prop="allPassCount" />-->
<!--      <el-table-column label="总合格率(%)" width="70" align="center" prop="allPassRate" />-->
<!--      &lt;!&ndash;      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">&ndash;&gt;-->
<!--      &lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--      &lt;!&ndash;          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>&ndash;&gt;-->
<!--      &lt;!&ndash;        </template>&ndash;&gt;-->
<!--      &lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--      &lt;!&ndash;      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">&ndash;&gt;-->
<!--      &lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--      &lt;!&ndash;          <el-button&ndash;&gt;-->
<!--      &lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--      &lt;!&ndash;            type="text"&ndash;&gt;-->
<!--      &lt;!&ndash;            icon="el-icon-edit"&ndash;&gt;-->
<!--      &lt;!&ndash;            @click="handleUpdate(scope.row)"&ndash;&gt;-->
<!--      &lt;!&ndash;            v-hasPermi="['out:outDlDetectRecords:edit']"&ndash;&gt;-->
<!--      &lt;!&ndash;          >修改</el-button>&ndash;&gt;-->
<!--      &lt;!&ndash;          <el-button&ndash;&gt;-->
<!--      &lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--      &lt;!&ndash;            type="text"&ndash;&gt;-->
<!--      &lt;!&ndash;            icon="el-icon-delete"&ndash;&gt;-->
<!--      &lt;!&ndash;            @click="handleDelete(scope.row)"&ndash;&gt;-->
<!--      &lt;!&ndash;            v-hasPermi="['out:outDlDetectRecords:remove']"&ndash;&gt;-->
<!--      &lt;!&ndash;          >删除</el-button>&ndash;&gt;-->
<!--      &lt;!&ndash;        </template>&ndash;&gt;-->
<!--      &lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--    </el-table>-->

<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

    <!-- 添加或修改定量监测结果汇总对话框 -->
    <!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
    <!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
    <!--        <el-form-item label="抽样地点" prop="samplingLocation">-->
    <!--          <el-input v-model="form.samplingLocation" placeholder="请输入抽样地点" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="抽样日期" prop="samplingDate">-->
    <!--          <el-date-picker clearable-->
    <!--            v-model="form.samplingDate"-->
    <!--            type="date"-->
    <!--            value-format="yyyy-MM-dd"-->
    <!--            placeholder="请选择抽样日期">-->
    <!--          </el-date-picker>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="抽样年份" prop="samplingYear">-->
    <!--          <el-input v-model="form.samplingYear" placeholder="请输入抽样年份" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="抽样月份" prop="samplingMonth">-->
    <!--          <el-input v-model="form.samplingMonth" placeholder="请输入抽样月份" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="蔬菜抽样数" prop="vegSamplingCount">-->
    <!--          <el-input v-model="form.vegSamplingCount" placeholder="请输入蔬菜抽样数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="蔬菜合格数" prop="vegQualifiedCount">-->
    <!--          <el-input v-model="form.vegQualifiedCount" placeholder="请输入蔬菜合格数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="蔬菜合格率" prop="vegPassRate">-->
    <!--          <el-input v-model="form.vegPassRate" placeholder="请输入蔬菜合格率" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="水果抽样数" prop="fruSamplingCount">-->
    <!--          <el-input v-model="form.fruSamplingCount" placeholder="请输入水果抽样数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="水果合格数" prop="fruQualifiedCount">-->
    <!--          <el-input v-model="form.fruQualifiedCount" placeholder="请输入水果合格数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="水果合格率" prop="fruPassRate">-->
    <!--          <el-input v-model="form.fruPassRate" placeholder="请输入水果合格率" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="总抽样数" prop="allSamplingCount">-->
    <!--          <el-input v-model="form.allSamplingCount" placeholder="请输入总抽样数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="总合格数" prop="allQualifiedCount">-->
    <!--          <el-input v-model="form.allQualifiedCount" placeholder="请输入总合格数" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="总合格率，百分比" prop="allPassRate">-->
    <!--          <el-input v-model="form.allPassRate" placeholder="请输入总合格率，百分比" />-->
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
import { listOutDlDetectRecords, getOutDlDetectRecords, delOutDlDetectRecords, addOutDlDetectRecords, updateOutDlDetectRecords,
  samplingAddressProvince,
  findBySamplingProvinceCode,
  findBySamplingCityCode,
} from "@/api/out/outDlDetectRecords";

export default {
  name: "OutDlDetectRecords",
  dicts: ['city','veg_fru_type'],
  data() {
    return {
      samplingAddressProvince: [],//省份集合
      samplingAddressCity: [],//城市集合
      samplingAddressTown: [],//区域集合
      samplingProvinceCode: '',//获取选中时的省份编号
      samplingCityCode: '',//获取选中时的城市编号
      samplingTownCode: '',//获取选中时区域的编号


      // 遮罩层
      // loading: true,
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
      // 定量监测结果汇总表格数据
      outDlDetectRecordsList: [],
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
        samplingLocation: null,
        samplingDate: null,
        samplingYear: null,
        samplingMonth: null,
        vegSamplingCount: null,
        vegQualifiedCount: null,
        vegPassRate: null,
        fruSamplingCount: null,
        fruQualifiedCount: null,
        fruPassRate: null,

        teaSamplingCount:null,
        teaPassCount:null,
        teaPassRate:null,

        allSamplingCount: null,
        allQualifiedCount: null,
        allPassRate: null,
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

      // // 菜单树
      // tree: [],
      // defaultProps: {
      //   children: 'dlDetRecordSampleRes',
      //   label: 'samplingLocation'
      // }
      // 用于展示树形结构的数据源
      tree: [],
      loading: false, // 控制加载状态
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


    // /** 查询定量监测结果汇总列表 */
    // getList() {
    //   this.loading = true;
    //   // listOutDlDetectRecords(this.queryParams).then(response => {
    //   listOutDlDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
    //     this.outDlDetectRecordsList = response.rows;
    //     console.log(this.outDlDetectRecordsList)
    //     this.total = response.total;
    //     this.SamplingLocation = response.SamplingLocation
    //     this.loading = false;
    //   });
    // },

    // /** 查询定量监测结果汇总列表(两列) */
    // getList() {
    //   this.loading = true;
    //   this.outDlDetectRecordsList = []
    //   listOutDlDetectRecords(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
    //     response.rows.forEach(item =>{
    //       item.dlDetRecordSampleRes.forEach(item =>{
    //         item.samplingLocation = item.samplingLocation
    //       })
    //     })
    //     this.tree = response.rows
    //     this.loading = false;
    //   });
    // },

    // 获取定量监测结果列表数据
    getList() {
      this.loading = true;
      listOutDlDetectRecords(this.addDateRange(this.queryParams, this.dateRange))
        .then(response => {
          response.rows.forEach((item,index) => {
            item.id = index + 1;
            console.log(item.id);  // 查看 id
            item.hasChildren = item.dlDetRecordSampleRes && item.dlDetRecordSampleRes.length > 0;
            // console.log(item.hasChildren);  // 查看 hasChildren 的值
            // 为子节点添加唯一标识符
            if (item.hasChildren) {
              item.dlDetRecordSampleRes.forEach((child, childIndex) => {
                child.id = `${item.id}-${childIndex + 1}`;
                console.log(child.id);  // 查看 id 的值
              });
            }
          });
          this.tree = response.rows;
        })
        .finally(() => {
          this.loading = false;
        });
    },

    // 懒加载功能，用于动态加载子节点数据
    load(tree, treeNode, resolve) {
      // 模拟异步加载子节点
      if (tree.dlDetRecordSampleRes) {
        setTimeout(() => {
          resolve(tree.dlDetRecordSampleRes);
        }, 1000);
      } else {
        resolve([]);
      }
    },


    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    // //菜单选择
    // handleNodeClick(val){
    //   if(val.dlDetRecordSampleRes){
    //     this.outDlDetectRecordsList = val.dlDetRecordSampleRes
    //   }
    // },


    // 表单重置
    reset() {
      this.form = {
        recordDlId: null,
        samplingLocation: null,
        samplingDate: null,
        samplingYear: null,
        samplingMonth: null,
        vegSamplingCount: null,
        vegQualifiedCount: null,
        vegPassRate: null,
        fruSamplingCount: null,
        fruQualifiedCount: null,
        fruPassRate: null,
        allSamplingCount: null,
        allQualifiedCount: null,
        allPassRate: null,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordDlId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    // handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加定量监测结果汇总";
    // },
    /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const recordDlId = row.recordDlId || this.ids
    //   getOutDlDetectRecords(recordDlId).then(response => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改定量监测结果汇总";
    //   });
    // },
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.recordDlId != null) {
    //         updateOutDlDetectRecords(this.form).then(response => {
    //           this.$modal.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addOutDlDetectRecords(this.form).then(response => {
    //           this.$modal.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    // handleDelete(row) {
    //   const recordDlIds = row.recordDlId || this.ids;
    //   this.$modal.confirm('是否确认删除定量监测结果汇总编号为"' + recordDlIds + '"的数据项？').then(function() {
    //     return delOutDlDetectRecords(recordDlIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outDlDetectRecords/export', {
        ...this.queryParams
      }, `outDlDetectRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
