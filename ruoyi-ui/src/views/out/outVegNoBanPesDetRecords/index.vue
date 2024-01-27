<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="农药名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入农药名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联苯菊酯" prop="bifenthrin">
        <el-input
          v-model="queryParams.bifenthrin"
          placeholder="请输入联苯菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氯氟氰菊酯" prop="chlorfluazuron">
        <el-input
          v-model="queryParams.chlorfluazuron"
          placeholder="请输入氯氟氰菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氟氯氰菊酯" prop="flucycloxuron">
        <el-input
          v-model="queryParams.flucycloxuron"
          placeholder="请输入氟氯氰菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氯氰菊酯" prop="lambdaCyhalothrin">
        <el-input
          v-model="queryParams.lambdaCyhalothrin"
          placeholder="请输入氯氰菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="甲氰菊酯" prop="fenvalerate">
        <el-input
          v-model="queryParams.fenvalerate"
          placeholder="请输入甲氰菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="溴氰菊酯" prop="deltamethrin">
        <el-input
          v-model="queryParams.deltamethrin"
          placeholder="请输入溴氰菊酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三唑酮" prop="triazolone">
        <el-input
          v-model="queryParams.triazolone"
          placeholder="请输入三唑酮"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="百菌清" prop="carbendazim">
        <el-input
          v-model="queryParams.carbendazim"
          placeholder="请输入百菌清"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="腐霉利" prop="metalaxyl">
        <el-input
          v-model="queryParams.metalaxyl"
          placeholder="请输入腐霉利"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="丙溴磷" prop="profenofos">
        <el-input
          v-model="queryParams.profenofos"
          placeholder="请输入丙溴磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="多菌灵" prop="carbaryl">
        <el-input
          v-model="queryParams.carbaryl"
          placeholder="请输入多菌灵"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="啶虫脒" prop="imidacloprid">
        <el-input
          v-model="queryParams.imidacloprid"
          placeholder="请输入啶虫脒"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="苯醚甲环唑" prop="myclobutanil">
        <el-input
          v-model="queryParams.myclobutanil"
          placeholder="请输入苯醚甲环唑"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阿维菌素" prop="abamectin">
        <el-input
          v-model="queryParams.abamectin"
          placeholder="请输入阿维菌素"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="甲氨基阿维菌素苯甲酸盐" prop="emamectinBenzoate">
        <el-input
          v-model="queryParams.emamectinBenzoate"
          placeholder="请输入甲氨基阿维菌素苯甲酸盐"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氟啶脲" prop="fludioxonil">
        <el-input
          v-model="queryParams.fludioxonil"
          placeholder="请输入氟啶脲"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="灭幼脲" prop="diflubenzuron">
        <el-input
          v-model="queryParams.diflubenzuron"
          placeholder="请输入灭幼脲"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="灭蝇胺" prop="cyromazine">
        <el-input
          v-model="queryParams.cyromazine"
          placeholder="请输入灭蝇胺"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="噻虫嗪" prop="thiamethoxam">
        <el-input
          v-model="queryParams.thiamethoxam"
          placeholder="请输入噻虫嗪"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="甲霜灵" prop="mancozeb">
        <el-input
          v-model="queryParams.mancozeb"
          placeholder="请输入甲霜灵"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="霜霉威" prop="metalaxylM">
        <el-input
          v-model="queryParams.metalaxylM"
          placeholder="请输入霜霉威"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吡唑醚菌酯" prop="pyraclostrobin">
        <el-input
          v-model="queryParams.pyraclostrobin"
          placeholder="请输入吡唑醚菌酯"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="氯吡脲" prop="chlorantraniliprole">
        <el-input
          v-model="queryParams.chlorantraniliprole"
          placeholder="请输入氯吡脲"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="嘧霉胺" prop="azoxystrobin">
        <el-input
          v-model="queryParams.azoxystrobin"
          placeholder="请输入嘧霉胺"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吡虫啉" prop="imidaclothiz">
        <el-input
          v-model="queryParams.imidaclothiz"
          placeholder="请输入吡虫啉"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="呋虫胺" prop="flonicamid">
        <el-input
          v-model="queryParams.flonicamid"
          placeholder="请输入呋虫胺"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="虱螨脲" prop="lufenuron">
        <el-input
          v-model="queryParams.lufenuron"
          placeholder="请输入虱螨脲"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="倍硫磷" prop="phosalone">
        <el-input
          v-model="queryParams.phosalone"
          placeholder="请输入倍硫磷"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抑霉唑" prop="difenoconazole">
        <el-input
          v-model="queryParams.difenoconazole"
          placeholder="请输入抑霉唑"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检出次数" prop="deteNum">
        <el-input
          v-model="queryParams.deteNum"
          placeholder="请输入检出次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="超标次数" prop="exDetNum">
        <el-input
          v-model="queryParams.exDetNum"
          placeholder="请输入超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录生产基地的检查次数" prop="productionInspectCount">
        <el-input
          v-model="queryParams.productionInspectCount"
          placeholder="请输入记录生产基地的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录生产基地的超标次数" prop="productionExceedCount">
        <el-input
          v-model="queryParams.productionExceedCount"
          placeholder="请输入记录生产基地的超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录批发市场的检查次数" prop="wholesaleInspectCount">
        <el-input
          v-model="queryParams.wholesaleInspectCount"
          placeholder="请输入记录批发市场的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录批发市场的超标次数" prop="wholesaleExceedCount">
        <el-input
          v-model="queryParams.wholesaleExceedCount"
          placeholder="请输入记录批发市场的超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录运输车的检查次数" prop="vehicleInspectCount">
        <el-input
          v-model="queryParams.vehicleInspectCount"
          placeholder="请输入记录运输车的检查次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录运输车的超标次数" prop="vehicleExceedCount">
        <el-input
          v-model="queryParams.vehicleExceedCount"
          placeholder="请输入记录运输车的超标次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['out:outVegNoBanPesDetRecords:add']"
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
          v-hasPermi="['out:outVegNoBanPesDetRecords:edit']"
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
          v-hasPermi="['out:outVegNoBanPesDetRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['out:outVegNoBanPesDetRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="outVegNoBanPesDetRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识每条检测记录" align="center" prop="vegNoBanPesDetRecordsId" />
      <el-table-column label="农药名称" align="center" prop="pesticideName" />
      <el-table-column label="联苯菊酯" align="center" prop="bifenthrin" />
      <el-table-column label="氯氟氰菊酯" align="center" prop="chlorfluazuron" />
      <el-table-column label="氟氯氰菊酯" align="center" prop="flucycloxuron" />
      <el-table-column label="氯氰菊酯" align="center" prop="lambdaCyhalothrin" />
      <el-table-column label="甲氰菊酯" align="center" prop="fenvalerate" />
      <el-table-column label="溴氰菊酯" align="center" prop="deltamethrin" />
      <el-table-column label="三唑酮" align="center" prop="triazolone" />
      <el-table-column label="百菌清" align="center" prop="carbendazim" />
      <el-table-column label="腐霉利" align="center" prop="metalaxyl" />
      <el-table-column label="丙溴磷" align="center" prop="profenofos" />
      <el-table-column label="多菌灵" align="center" prop="carbaryl" />
      <el-table-column label="啶虫脒" align="center" prop="imidacloprid" />
      <el-table-column label="苯醚甲环唑" align="center" prop="myclobutanil" />
      <el-table-column label="阿维菌素" align="center" prop="abamectin" />
      <el-table-column label="甲氨基阿维菌素苯甲酸盐" align="center" prop="emamectinBenzoate" />
      <el-table-column label="氟啶脲" align="center" prop="fludioxonil" />
      <el-table-column label="灭幼脲" align="center" prop="diflubenzuron" />
      <el-table-column label="灭蝇胺" align="center" prop="cyromazine" />
      <el-table-column label="噻虫嗪" align="center" prop="thiamethoxam" />
      <el-table-column label="甲霜灵" align="center" prop="mancozeb" />
      <el-table-column label="霜霉威" align="center" prop="metalaxylM" />
      <el-table-column label="吡唑醚菌酯" align="center" prop="pyraclostrobin" />
      <el-table-column label="氯吡脲" align="center" prop="chlorantraniliprole" />
      <el-table-column label="嘧霉胺" align="center" prop="azoxystrobin" />
      <el-table-column label="吡虫啉" align="center" prop="imidaclothiz" />
      <el-table-column label="呋虫胺" align="center" prop="flonicamid" />
      <el-table-column label="虱螨脲" align="center" prop="lufenuron" />
      <el-table-column label="倍硫磷" align="center" prop="phosalone" />
      <el-table-column label="抑霉唑" align="center" prop="difenoconazole" />
      <el-table-column label="检出次数" align="center" prop="deteNum" />
      <el-table-column label="超标次数" align="center" prop="exDetNum" />
      <el-table-column label="记录生产基地的检查次数" align="center" prop="productionInspectCount" />
      <el-table-column label="记录生产基地的超标次数" align="center" prop="productionExceedCount" />
      <el-table-column label="记录批发市场的检查次数" align="center" prop="wholesaleInspectCount" />
      <el-table-column label="记录批发市场的超标次数" align="center" prop="wholesaleExceedCount" />
      <el-table-column label="记录运输车的检查次数" align="center" prop="vehicleInspectCount" />
      <el-table-column label="记录运输车的超标次数" align="center" prop="vehicleExceedCount" />
      <el-table-column label="记录创建的时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['out:outVegNoBanPesDetRecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outVegNoBanPesDetRecords:remove']"
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

    <!-- 添加或修改蔬菜上非禁止使用农药检出及超标情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农药名称" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入农药名称" />
        </el-form-item>
        <el-form-item label="联苯菊酯" prop="bifenthrin">
          <el-input v-model="form.bifenthrin" placeholder="请输入联苯菊酯" />
        </el-form-item>
        <el-form-item label="氯氟氰菊酯" prop="chlorfluazuron">
          <el-input v-model="form.chlorfluazuron" placeholder="请输入氯氟氰菊酯" />
        </el-form-item>
        <el-form-item label="氟氯氰菊酯" prop="flucycloxuron">
          <el-input v-model="form.flucycloxuron" placeholder="请输入氟氯氰菊酯" />
        </el-form-item>
        <el-form-item label="氯氰菊酯" prop="lambdaCyhalothrin">
          <el-input v-model="form.lambdaCyhalothrin" placeholder="请输入氯氰菊酯" />
        </el-form-item>
        <el-form-item label="甲氰菊酯" prop="fenvalerate">
          <el-input v-model="form.fenvalerate" placeholder="请输入甲氰菊酯" />
        </el-form-item>
        <el-form-item label="溴氰菊酯" prop="deltamethrin">
          <el-input v-model="form.deltamethrin" placeholder="请输入溴氰菊酯" />
        </el-form-item>
        <el-form-item label="三唑酮" prop="triazolone">
          <el-input v-model="form.triazolone" placeholder="请输入三唑酮" />
        </el-form-item>
        <el-form-item label="百菌清" prop="carbendazim">
          <el-input v-model="form.carbendazim" placeholder="请输入百菌清" />
        </el-form-item>
        <el-form-item label="腐霉利" prop="metalaxyl">
          <el-input v-model="form.metalaxyl" placeholder="请输入腐霉利" />
        </el-form-item>
        <el-form-item label="丙溴磷" prop="profenofos">
          <el-input v-model="form.profenofos" placeholder="请输入丙溴磷" />
        </el-form-item>
        <el-form-item label="多菌灵" prop="carbaryl">
          <el-input v-model="form.carbaryl" placeholder="请输入多菌灵" />
        </el-form-item>
        <el-form-item label="啶虫脒" prop="imidacloprid">
          <el-input v-model="form.imidacloprid" placeholder="请输入啶虫脒" />
        </el-form-item>
        <el-form-item label="苯醚甲环唑" prop="myclobutanil">
          <el-input v-model="form.myclobutanil" placeholder="请输入苯醚甲环唑" />
        </el-form-item>
        <el-form-item label="阿维菌素" prop="abamectin">
          <el-input v-model="form.abamectin" placeholder="请输入阿维菌素" />
        </el-form-item>
        <el-form-item label="甲氨基阿维菌素苯甲酸盐" prop="emamectinBenzoate">
          <el-input v-model="form.emamectinBenzoate" placeholder="请输入甲氨基阿维菌素苯甲酸盐" />
        </el-form-item>
        <el-form-item label="氟啶脲" prop="fludioxonil">
          <el-input v-model="form.fludioxonil" placeholder="请输入氟啶脲" />
        </el-form-item>
        <el-form-item label="灭幼脲" prop="diflubenzuron">
          <el-input v-model="form.diflubenzuron" placeholder="请输入灭幼脲" />
        </el-form-item>
        <el-form-item label="灭蝇胺" prop="cyromazine">
          <el-input v-model="form.cyromazine" placeholder="请输入灭蝇胺" />
        </el-form-item>
        <el-form-item label="噻虫嗪" prop="thiamethoxam">
          <el-input v-model="form.thiamethoxam" placeholder="请输入噻虫嗪" />
        </el-form-item>
        <el-form-item label="甲霜灵" prop="mancozeb">
          <el-input v-model="form.mancozeb" placeholder="请输入甲霜灵" />
        </el-form-item>
        <el-form-item label="霜霉威" prop="metalaxylM">
          <el-input v-model="form.metalaxylM" placeholder="请输入霜霉威" />
        </el-form-item>
        <el-form-item label="吡唑醚菌酯" prop="pyraclostrobin">
          <el-input v-model="form.pyraclostrobin" placeholder="请输入吡唑醚菌酯" />
        </el-form-item>
        <el-form-item label="氯吡脲" prop="chlorantraniliprole">
          <el-input v-model="form.chlorantraniliprole" placeholder="请输入氯吡脲" />
        </el-form-item>
        <el-form-item label="嘧霉胺" prop="azoxystrobin">
          <el-input v-model="form.azoxystrobin" placeholder="请输入嘧霉胺" />
        </el-form-item>
        <el-form-item label="吡虫啉" prop="imidaclothiz">
          <el-input v-model="form.imidaclothiz" placeholder="请输入吡虫啉" />
        </el-form-item>
        <el-form-item label="呋虫胺" prop="flonicamid">
          <el-input v-model="form.flonicamid" placeholder="请输入呋虫胺" />
        </el-form-item>
        <el-form-item label="虱螨脲" prop="lufenuron">
          <el-input v-model="form.lufenuron" placeholder="请输入虱螨脲" />
        </el-form-item>
        <el-form-item label="倍硫磷" prop="phosalone">
          <el-input v-model="form.phosalone" placeholder="请输入倍硫磷" />
        </el-form-item>
        <el-form-item label="抑霉唑" prop="difenoconazole">
          <el-input v-model="form.difenoconazole" placeholder="请输入抑霉唑" />
        </el-form-item>
        <el-form-item label="检出次数" prop="deteNum">
          <el-input v-model="form.deteNum" placeholder="请输入检出次数" />
        </el-form-item>
        <el-form-item label="超标次数" prop="exDetNum">
          <el-input v-model="form.exDetNum" placeholder="请输入超标次数" />
        </el-form-item>
        <el-form-item label="记录生产基地的检查次数" prop="productionInspectCount">
          <el-input v-model="form.productionInspectCount" placeholder="请输入记录生产基地的检查次数" />
        </el-form-item>
        <el-form-item label="记录生产基地的超标次数" prop="productionExceedCount">
          <el-input v-model="form.productionExceedCount" placeholder="请输入记录生产基地的超标次数" />
        </el-form-item>
        <el-form-item label="记录批发市场的检查次数" prop="wholesaleInspectCount">
          <el-input v-model="form.wholesaleInspectCount" placeholder="请输入记录批发市场的检查次数" />
        </el-form-item>
        <el-form-item label="记录批发市场的超标次数" prop="wholesaleExceedCount">
          <el-input v-model="form.wholesaleExceedCount" placeholder="请输入记录批发市场的超标次数" />
        </el-form-item>
        <el-form-item label="记录运输车的检查次数" prop="vehicleInspectCount">
          <el-input v-model="form.vehicleInspectCount" placeholder="请输入记录运输车的检查次数" />
        </el-form-item>
        <el-form-item label="记录运输车的超标次数" prop="vehicleExceedCount">
          <el-input v-model="form.vehicleExceedCount" placeholder="请输入记录运输车的超标次数" />
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
import { listOutVegNoBanPesDetRecords, getOutVegNoBanPesDetRecords, delOutVegNoBanPesDetRecords, addOutVegNoBanPesDetRecords, updateOutVegNoBanPesDetRecords } from "@/api/out/outVegNoBanPesDetRecords";

export default {
  name: "OutVegNoBanPesDetRecords",
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
      // 蔬菜上非禁止使用农药检出及超标情况表格数据
      outVegNoBanPesDetRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        bifenthrin: null,
        chlorfluazuron: null,
        flucycloxuron: null,
        lambdaCyhalothrin: null,
        fenvalerate: null,
        deltamethrin: null,
        triazolone: null,
        carbendazim: null,
        metalaxyl: null,
        profenofos: null,
        carbaryl: null,
        imidacloprid: null,
        myclobutanil: null,
        abamectin: null,
        emamectinBenzoate: null,
        fludioxonil: null,
        diflubenzuron: null,
        cyromazine: null,
        thiamethoxam: null,
        mancozeb: null,
        metalaxylM: null,
        pyraclostrobin: null,
        chlorantraniliprole: null,
        azoxystrobin: null,
        imidaclothiz: null,
        flonicamid: null,
        lufenuron: null,
        phosalone: null,
        difenoconazole: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
        createdDate: null
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
    /** 查询蔬菜上非禁止使用农药检出及超标情况列表 */
    getList() {
      this.loading = true;
      listOutVegNoBanPesDetRecords(this.queryParams).then(response => {
        this.outVegNoBanPesDetRecordsList = response.rows;
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
        vegNoBanPesDetRecordsId: null,
        pesticideName: null,
        bifenthrin: null,
        chlorfluazuron: null,
        flucycloxuron: null,
        lambdaCyhalothrin: null,
        fenvalerate: null,
        deltamethrin: null,
        triazolone: null,
        carbendazim: null,
        metalaxyl: null,
        profenofos: null,
        carbaryl: null,
        imidacloprid: null,
        myclobutanil: null,
        abamectin: null,
        emamectinBenzoate: null,
        fludioxonil: null,
        diflubenzuron: null,
        cyromazine: null,
        thiamethoxam: null,
        mancozeb: null,
        metalaxylM: null,
        pyraclostrobin: null,
        chlorantraniliprole: null,
        azoxystrobin: null,
        imidaclothiz: null,
        flonicamid: null,
        lufenuron: null,
        phosalone: null,
        difenoconazole: null,
        deteNum: null,
        exDetNum: null,
        productionInspectCount: null,
        productionExceedCount: null,
        wholesaleInspectCount: null,
        wholesaleExceedCount: null,
        vehicleInspectCount: null,
        vehicleExceedCount: null,
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
      this.ids = selection.map(item => item.vegNoBanPesDetRecordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加蔬菜上非禁止使用农药检出及超标情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vegNoBanPesDetRecordsId = row.vegNoBanPesDetRecordsId || this.ids
      getOutVegNoBanPesDetRecords(vegNoBanPesDetRecordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改蔬菜上非禁止使用农药检出及超标情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vegNoBanPesDetRecordsId != null) {
            updateOutVegNoBanPesDetRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutVegNoBanPesDetRecords(this.form).then(response => {
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
      const vegNoBanPesDetRecordsIds = row.vegNoBanPesDetRecordsId || this.ids;
      this.$modal.confirm('是否确认删除蔬菜上非禁止使用农药检出及超标情况编号为"' + vegNoBanPesDetRecordsIds + '"的数据项？').then(function() {
        return delOutVegNoBanPesDetRecords(vegNoBanPesDetRecordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outVegNoBanPesDetRecords/export', {
        ...this.queryParams
      }, `outVegNoBanPesDetRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
