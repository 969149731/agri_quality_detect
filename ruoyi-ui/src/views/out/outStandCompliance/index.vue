<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参照的标准" prop="standard">
        <el-input
          v-model="queryParams.standard"
          placeholder="请输入参照的标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国家标准" prop="chinaStandard">
        <el-input
          v-model="queryParams.chinaStandard"
          placeholder="请输入国家标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="CAC标准" prop="cacStandard">
        <el-input
          v-model="queryParams.cacStandard"
          placeholder="请输入CAC标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日本标准" prop="japanStandard">
        <el-input
          v-model="queryParams.japanStandard"
          placeholder="请输入日本标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="欧盟标准" prop="euStandard">
        <el-input
          v-model="queryParams.euStandard"
          placeholder="请输入欧盟标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="美国标准" prop="usStandard">
        <el-input
          v-model="queryParams.usStandard"
          placeholder="请输入美国标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="韩国标准" prop="koreaStandard">
        <el-input
          v-model="queryParams.koreaStandard"
          placeholder="请输入韩国标准"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据源于哪个市区县" prop="sourceArea">
        <el-input
          v-model="queryParams.sourceArea"
          placeholder="请输入数据源于哪个市区县"
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
          v-hasPermi="['out:outStandCompliance:add']"
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
          v-hasPermi="['out:outStandCompliance:edit']"
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
          v-hasPermi="['out:outStandCompliance:remove']"
        >删除</el-button>
      </el-col>
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
      v-if="!peopleTagNumLoading"
      ref="refreshTable"
      :header-cell-style="headerStyle"
      :span-method="spanMethod"
      :data="StandardList"
      style="width: 100%;"
      align='center'
      id="table1"
    >
      <el-table-column label="项目" prop="StandardLable" width="100px" align="center">
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


    </el-table>

    <el-table v-loading="loading" :data="outStandComplianceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识符" align="center" prop="interStandId" />
      <el-table-column label="参照的标准" align="center" prop="standard" />
      <el-table-column label="国家标准" align="center" prop="chinaStandard" />
      <el-table-column label="CAC标准" align="center" prop="cacStandard" />
      <el-table-column label="日本标准" align="center" prop="japanStandard" />
      <el-table-column label="欧盟标准" align="center" prop="euStandard" />
      <el-table-column label="美国标准" align="center" prop="usStandard" />
      <el-table-column label="韩国标准" align="center" prop="koreaStandard" />
      <el-table-column label="数据源于哪个市区县" align="center" prop="sourceArea" />
      <el-table-column label="甲胺磷超标数" align="center" prop="methamidophos" />
      <el-table-column label="乙酰甲胺磷超标数" align="center" prop="acephate" />
      <el-table-column label="甲拌磷超标数" align="center" prop="Phorate" />
      <el-table-column label="氧乐果超标数" align="center" prop="oxamyl" />
      <el-table-column label="毒死蜱超标数" align="center" prop="chlorpyrifos" />
      <el-table-column label="特丁硫磷超标数" align="center" prop="fenitrothion" />
      <el-table-column label="三唑磷超标数" align="center" prop="triazophos" />
      <el-table-column label="水胺硫磷超标数" align="center" prop="fenthion" />
      <el-table-column label="治螟磷超标数" align="center" prop="isocarbophos" />
      <el-table-column label="乐果超标数" align="center" prop="leGuo" />
      <el-table-column label="甲基异柳磷超标数" align="center" prop="isofenphosMethyl" />
      <el-table-column label="氟虫腈超标数" align="center" prop="flucythrinate" />
      <el-table-column label="克百威超标数" align="center" prop="kebaiwei" />
      <el-table-column label="联苯菊酯超标数" align="center" prop="Bifenthrin" />
      <el-table-column label="氯氟氰菊酯超标数" align="center" prop="chlorfluazuron" />
      <el-table-column label="氟氯氰菊酯超标数" align="center" prop="fluorochloridone" />
      <el-table-column label="氯氰菊酯超标数" align="center" prop="cypermethrin" />
      <el-table-column label="甲氰菊酯超标数" align="center" prop="fenpyroximate" />
      <el-table-column label="溴氰菊酯超标数" align="center" prop="bromfenac" />
      <el-table-column label="三唑酮超标数" align="center" prop="triazolone" />
      <el-table-column label="百菌清超标数" align="center" prop="pyrimethanil" />
      <el-table-column label="腐霉利超标数" align="center" prop="furalaxyl" />
      <el-table-column label="丙溴磷超标数" align="center" prop="profenofos" />
      <el-table-column label="多菌灵超标数" align="center" prop="carbendazim" />
      <el-table-column label="啶虫脒超标数" align="center" prop="imidacloprid" />
      <el-table-column label="苯醚甲环唑超标数" align="center" prop="epoxiconazole" />
      <el-table-column label="阿维菌素超标数" align="center" prop="abamectin" />
      <el-table-column label="甲氨基阿维菌素苯甲酸盐超标数" align="center" prop="emamectinBenzoate" />
      <el-table-column label="氟啶脲超标数" align="center" prop="fludioxonil" />
      <el-table-column label="灭幼脲超标数" align="center" prop="methomyl" />
      <el-table-column label="灭蝇胺超标数" align="center" prop="fipronil" />
      <el-table-column label="噻虫嗪超标数" align="center" prop="thiamethoxam" />
      <el-table-column label="甲霜灵超标数" align="center" prop="metalaxyl" />
      <el-table-column label="霜霉威超标数" align="center" prop="mefenoxam" />
      <el-table-column label="吡唑醚菌酯超标数" align="center" prop="prochloraz" />
      <el-table-column label="氯吡脲超标数" align="center" prop="linuron" />
      <el-table-column label="嘧霉胺超标数" align="center" prop="azoxystrobin" />
      <el-table-column label="吡虫啉超标数" align="center" prop="imidaclothiz" />
      <el-table-column label="呋虫胺超标数" align="center" prop="furametpyr" />
      <el-table-column label="虱螨脲超标数" align="center" prop="lufenuron" />
      <el-table-column label="倍硫磷超标数" align="center" prop="sulfotep" />
      <el-table-column label="抑霉唑超标数" align="center" prop="propiconazole" />
      <el-table-column label="敌敌畏超标数" align="center" prop="dichlorvos" />
      <el-table-column label="甲基毒死蜱超标数" align="center" prop="chlorpyrifosMethyl" />
      <el-table-column label="氰戊菊酯超标数" align="center" prop="fenvalerate" />
      <el-table-column label="异菌脲超标数" align="center" prop="fluopicolide" />
      <el-table-column label="涕灭威超标数" align="center" prop="spirodiclofen" />
      <el-table-column label="烯酰吗啉超标数" align="center" prop="fenpyrazamine" />
      <el-table-column label="炔螨特超标数" align="center" prop="propargite" />
      <el-table-column label="乙螨唑超标数" align="center" prop="etoxazole" />
      <el-table-column label="嘧菌酯超标数" align="center" prop="pyraclostrobin" />
      <el-table-column label="噻虫胺超标数" align="center" prop="thiacloprid" />
      <el-table-column label="螺虫乙酯超标数" align="center" prop="spinetoram" />
      <el-table-column label="哒螨灵超标数" align="center" prop="pyridaben" />
      <el-table-column label="四螨嗪超标数" align="center" prop="clofentezine" />
      <el-table-column label="虫螨腈超标数" align="center" prop="chlorfenapyr" />
      <el-table-column label="咪鲜胺超标数" align="center" prop="milbemectin" />
      <el-table-column label="双甲脒超标数" align="center" prop="dimethomorph" />
      <el-table-column label="抽检数量" align="center" prop="samplingQuantity" />
      <el-table-column label="合格数" align="center" prop="qualifiedNumber" />
      <el-table-column label="合格率" align="center" prop="qualificationRate" />
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
            v-hasPermi="['out:outStandCompliance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['out:outStandCompliance:remove']"
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

    <!-- 添加或修改参照国际组织或国家标准合格率情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="参照的标准" prop="standard">
          <el-input v-model="form.standard" placeholder="请输入参照的标准" />
        </el-form-item>
        <el-form-item label="国家标准" prop="chinaStandard">
          <el-input v-model="form.chinaStandard" placeholder="请输入国家标准" />
        </el-form-item>
        <el-form-item label="CAC标准" prop="cacStandard">
          <el-input v-model="form.cacStandard" placeholder="请输入CAC标准" />
        </el-form-item>
        <el-form-item label="日本标准" prop="japanStandard">
          <el-input v-model="form.japanStandard" placeholder="请输入日本标准" />
        </el-form-item>
        <el-form-item label="欧盟标准" prop="euStandard">
          <el-input v-model="form.euStandard" placeholder="请输入欧盟标准" />
        </el-form-item>
        <el-form-item label="美国标准" prop="usStandard">
          <el-input v-model="form.usStandard" placeholder="请输入美国标准" />
        </el-form-item>
        <el-form-item label="韩国标准" prop="koreaStandard">
          <el-input v-model="form.koreaStandard" placeholder="请输入韩国标准" />
        </el-form-item>
        <el-form-item label="数据源于哪个市区县" prop="sourceArea">
          <el-input v-model="form.sourceArea" placeholder="请输入数据源于哪个市区县" />
        </el-form-item>
        <el-form-item label="甲胺磷超标数" prop="methamidophos">
          <el-input v-model="form.methamidophos" placeholder="请输入甲胺磷超标数" />
        </el-form-item>
        <el-form-item label="乙酰甲胺磷超标数" prop="acephate">
          <el-input v-model="form.acephate" placeholder="请输入乙酰甲胺磷超标数" />
        </el-form-item>
        <el-form-item label="甲拌磷超标数" prop="Phorate">
          <el-input v-model="form.Phorate" placeholder="请输入甲拌磷超标数" />
        </el-form-item>
        <el-form-item label="氧乐果超标数" prop="oxamyl">
          <el-input v-model="form.oxamyl" placeholder="请输入氧乐果超标数" />
        </el-form-item>
        <el-form-item label="毒死蜱超标数" prop="chlorpyrifos">
          <el-input v-model="form.chlorpyrifos" placeholder="请输入毒死蜱超标数" />
        </el-form-item>
        <el-form-item label="特丁硫磷超标数" prop="fenitrothion">
          <el-input v-model="form.fenitrothion" placeholder="请输入特丁硫磷超标数" />
        </el-form-item>
        <el-form-item label="三唑磷超标数" prop="triazophos">
          <el-input v-model="form.triazophos" placeholder="请输入三唑磷超标数" />
        </el-form-item>
        <el-form-item label="水胺硫磷超标数" prop="fenthion">
          <el-input v-model="form.fenthion" placeholder="请输入水胺硫磷超标数" />
        </el-form-item>
        <el-form-item label="治螟磷超标数" prop="isocarbophos">
          <el-input v-model="form.isocarbophos" placeholder="请输入治螟磷超标数" />
        </el-form-item>
        <el-form-item label="乐果超标数" prop="leGuo">
          <el-input v-model="form.leGuo" placeholder="请输入乐果超标数" />
        </el-form-item>
        <el-form-item label="甲基异柳磷超标数" prop="isofenphosMethyl">
          <el-input v-model="form.isofenphosMethyl" placeholder="请输入甲基异柳磷超标数" />
        </el-form-item>
        <el-form-item label="氟虫腈超标数" prop="flucythrinate">
          <el-input v-model="form.flucythrinate" placeholder="请输入氟虫腈超标数" />
        </el-form-item>
        <el-form-item label="克百威超标数" prop="kebaiwei">
          <el-input v-model="form.kebaiwei" placeholder="请输入克百威超标数" />
        </el-form-item>
        <el-form-item label="联苯菊酯超标数" prop="Bifenthrin">
          <el-input v-model="form.Bifenthrin" placeholder="请输入联苯菊酯超标数" />
        </el-form-item>
        <el-form-item label="氯氟氰菊酯超标数" prop="chlorfluazuron">
          <el-input v-model="form.chlorfluazuron" placeholder="请输入氯氟氰菊酯超标数" />
        </el-form-item>
        <el-form-item label="氟氯氰菊酯超标数" prop="fluorochloridone">
          <el-input v-model="form.fluorochloridone" placeholder="请输入氟氯氰菊酯超标数" />
        </el-form-item>
        <el-form-item label="氯氰菊酯超标数" prop="cypermethrin">
          <el-input v-model="form.cypermethrin" placeholder="请输入氯氰菊酯超标数" />
        </el-form-item>
        <el-form-item label="甲氰菊酯超标数" prop="fenpyroximate">
          <el-input v-model="form.fenpyroximate" placeholder="请输入甲氰菊酯超标数" />
        </el-form-item>
        <el-form-item label="溴氰菊酯超标数" prop="bromfenac">
          <el-input v-model="form.bromfenac" placeholder="请输入溴氰菊酯超标数" />
        </el-form-item>
        <el-form-item label="三唑酮超标数" prop="triazolone">
          <el-input v-model="form.triazolone" placeholder="请输入三唑酮超标数" />
        </el-form-item>
        <el-form-item label="百菌清超标数" prop="pyrimethanil">
          <el-input v-model="form.pyrimethanil" placeholder="请输入百菌清超标数" />
        </el-form-item>
        <el-form-item label="腐霉利超标数" prop="furalaxyl">
          <el-input v-model="form.furalaxyl" placeholder="请输入腐霉利超标数" />
        </el-form-item>
        <el-form-item label="丙溴磷超标数" prop="profenofos">
          <el-input v-model="form.profenofos" placeholder="请输入丙溴磷超标数" />
        </el-form-item>
        <el-form-item label="多菌灵超标数" prop="carbendazim">
          <el-input v-model="form.carbendazim" placeholder="请输入多菌灵超标数" />
        </el-form-item>
        <el-form-item label="啶虫脒超标数" prop="imidacloprid">
          <el-input v-model="form.imidacloprid" placeholder="请输入啶虫脒超标数" />
        </el-form-item>
        <el-form-item label="苯醚甲环唑超标数" prop="epoxiconazole">
          <el-input v-model="form.epoxiconazole" placeholder="请输入苯醚甲环唑超标数" />
        </el-form-item>
        <el-form-item label="阿维菌素超标数" prop="abamectin">
          <el-input v-model="form.abamectin" placeholder="请输入阿维菌素超标数" />
        </el-form-item>
        <el-form-item label="甲氨基阿维菌素苯甲酸盐超标数" prop="emamectinBenzoate">
          <el-input v-model="form.emamectinBenzoate" placeholder="请输入甲氨基阿维菌素苯甲酸盐超标数" />
        </el-form-item>
        <el-form-item label="氟啶脲超标数" prop="fludioxonil">
          <el-input v-model="form.fludioxonil" placeholder="请输入氟啶脲超标数" />
        </el-form-item>
        <el-form-item label="灭幼脲超标数" prop="methomyl">
          <el-input v-model="form.methomyl" placeholder="请输入灭幼脲超标数" />
        </el-form-item>
        <el-form-item label="灭蝇胺超标数" prop="fipronil">
          <el-input v-model="form.fipronil" placeholder="请输入灭蝇胺超标数" />
        </el-form-item>
        <el-form-item label="噻虫嗪超标数" prop="thiamethoxam">
          <el-input v-model="form.thiamethoxam" placeholder="请输入噻虫嗪超标数" />
        </el-form-item>
        <el-form-item label="甲霜灵超标数" prop="metalaxyl">
          <el-input v-model="form.metalaxyl" placeholder="请输入甲霜灵超标数" />
        </el-form-item>
        <el-form-item label="霜霉威超标数" prop="mefenoxam">
          <el-input v-model="form.mefenoxam" placeholder="请输入霜霉威超标数" />
        </el-form-item>
        <el-form-item label="吡唑醚菌酯超标数" prop="prochloraz">
          <el-input v-model="form.prochloraz" placeholder="请输入吡唑醚菌酯超标数" />
        </el-form-item>
        <el-form-item label="氯吡脲超标数" prop="linuron">
          <el-input v-model="form.linuron" placeholder="请输入氯吡脲超标数" />
        </el-form-item>
        <el-form-item label="嘧霉胺超标数" prop="azoxystrobin">
          <el-input v-model="form.azoxystrobin" placeholder="请输入嘧霉胺超标数" />
        </el-form-item>
        <el-form-item label="吡虫啉超标数" prop="imidaclothiz">
          <el-input v-model="form.imidaclothiz" placeholder="请输入吡虫啉超标数" />
        </el-form-item>
        <el-form-item label="呋虫胺超标数" prop="furametpyr">
          <el-input v-model="form.furametpyr" placeholder="请输入呋虫胺超标数" />
        </el-form-item>
        <el-form-item label="虱螨脲超标数" prop="lufenuron">
          <el-input v-model="form.lufenuron" placeholder="请输入虱螨脲超标数" />
        </el-form-item>
        <el-form-item label="倍硫磷超标数" prop="sulfotep">
          <el-input v-model="form.sulfotep" placeholder="请输入倍硫磷超标数" />
        </el-form-item>
        <el-form-item label="抑霉唑超标数" prop="propiconazole">
          <el-input v-model="form.propiconazole" placeholder="请输入抑霉唑超标数" />
        </el-form-item>
        <el-form-item label="敌敌畏超标数" prop="dichlorvos">
          <el-input v-model="form.dichlorvos" placeholder="请输入敌敌畏超标数" />
        </el-form-item>
        <el-form-item label="甲基毒死蜱超标数" prop="chlorpyrifosMethyl">
          <el-input v-model="form.chlorpyrifosMethyl" placeholder="请输入甲基毒死蜱超标数" />
        </el-form-item>
        <el-form-item label="氰戊菊酯超标数" prop="fenvalerate">
          <el-input v-model="form.fenvalerate" placeholder="请输入氰戊菊酯超标数" />
        </el-form-item>
        <el-form-item label="异菌脲超标数" prop="fluopicolide">
          <el-input v-model="form.fluopicolide" placeholder="请输入异菌脲超标数" />
        </el-form-item>
        <el-form-item label="涕灭威超标数" prop="spirodiclofen">
          <el-input v-model="form.spirodiclofen" placeholder="请输入涕灭威超标数" />
        </el-form-item>
        <el-form-item label="烯酰吗啉超标数" prop="fenpyrazamine">
          <el-input v-model="form.fenpyrazamine" placeholder="请输入烯酰吗啉超标数" />
        </el-form-item>
        <el-form-item label="炔螨特超标数" prop="propargite">
          <el-input v-model="form.propargite" placeholder="请输入炔螨特超标数" />
        </el-form-item>
        <el-form-item label="乙螨唑超标数" prop="etoxazole">
          <el-input v-model="form.etoxazole" placeholder="请输入乙螨唑超标数" />
        </el-form-item>
        <el-form-item label="嘧菌酯超标数" prop="pyraclostrobin">
          <el-input v-model="form.pyraclostrobin" placeholder="请输入嘧菌酯超标数" />
        </el-form-item>
        <el-form-item label="噻虫胺超标数" prop="thiacloprid">
          <el-input v-model="form.thiacloprid" placeholder="请输入噻虫胺超标数" />
        </el-form-item>
        <el-form-item label="螺虫乙酯超标数" prop="spinetoram">
          <el-input v-model="form.spinetoram" placeholder="请输入螺虫乙酯超标数" />
        </el-form-item>
        <el-form-item label="哒螨灵超标数" prop="pyridaben">
          <el-input v-model="form.pyridaben" placeholder="请输入哒螨灵超标数" />
        </el-form-item>
        <el-form-item label="四螨嗪超标数" prop="clofentezine">
          <el-input v-model="form.clofentezine" placeholder="请输入四螨嗪超标数" />
        </el-form-item>
        <el-form-item label="虫螨腈超标数" prop="chlorfenapyr">
          <el-input v-model="form.chlorfenapyr" placeholder="请输入虫螨腈超标数" />
        </el-form-item>
        <el-form-item label="咪鲜胺超标数" prop="milbemectin">
          <el-input v-model="form.milbemectin" placeholder="请输入咪鲜胺超标数" />
        </el-form-item>
        <el-form-item label="双甲脒超标数" prop="dimethomorph">
          <el-input v-model="form.dimethomorph" placeholder="请输入双甲脒超标数" />
        </el-form-item>
        <el-form-item label="抽检数量" prop="samplingQuantity">
          <el-input v-model="form.samplingQuantity" placeholder="请输入抽检数量" />
        </el-form-item>
        <el-form-item label="合格数" prop="qualifiedNumber">
          <el-input v-model="form.qualifiedNumber" placeholder="请输入合格数" />
        </el-form-item>
        <el-form-item label="合格率" prop="qualificationRate">
          <el-input v-model="form.qualificationRate" placeholder="请输入合格率" />
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
import { listOutStandCompliance2,listOutStandCompliance, getOutStandCompliance, delOutStandCompliance, addOutStandCompliance, updateOutStandCompliance } from "@/api/out/outStandCompliance";

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
        methamidophos: null,
        acephate: null,
        Phorate: null,
        oxamyl: null,
        chlorpyrifos: null,
        fenitrothion: null,
        triazophos: null,
        fenthion: null,
        isocarbophos: null,
        leGuo: null,
        isofenphosMethyl: null,
        flucythrinate: null,
        kebaiwei: null,
        Bifenthrin: null,
        chlorfluazuron: null,
        fluorochloridone: null,
        cypermethrin: null,
        fenpyroximate: null,
        bromfenac: null,
        triazolone: null,
        pyrimethanil: null,
        furalaxyl: null,
        profenofos: null,
        carbendazim: null,
        imidacloprid: null,
        epoxiconazole: null,
        abamectin: null,
        emamectinBenzoate: null,
        fludioxonil: null,
        methomyl: null,
        fipronil: null,
        thiamethoxam: null,
        metalaxyl: null,
        mefenoxam: null,
        prochloraz: null,
        linuron: null,
        azoxystrobin: null,
        imidaclothiz: null,
        furametpyr: null,
        lufenuron: null,
        sulfotep: null,
        propiconazole: null,
        dichlorvos: null,
        chlorpyrifosMethyl: null,
        fenvalerate: null,
        fluopicolide: null,
        spirodiclofen: null,
        fenpyrazamine: null,
        propargite: null,
        etoxazole: null,
        pyraclostrobin: null,
        thiacloprid: null,
        spinetoram: null,
        pyridaben: null,
        clofentezine: null,
        chlorfenapyr: null,
        milbemectin: null,
        dimethomorph: null,
        samplingQuantity: null,
        qualifiedNumber: null,
        qualificationRate: null,
        createdDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      StandardList: [
        {
          StandardLable: '参照我国标准',
          StageId: 'CN'

        },
        {
          StandardLable: '参照CAC标准',
          StageId: 'CAC'
        },
        {
          StandardLable: '参照美国标准',
          StageId: 'US'
        },
        {
          StandardLable: '参照欧盟标准',
          StageId: 'EU'
        },
        {
          StandardLable: '参照日本标准',
          StageId: 'JPN'
        },
        {
          StandardLable: '参照韩国标准',
          StageId: 'KR'
        },
      ],
      pesticideNameList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询参照国际组织或国家标准合格率情况列表 */
    getList() {
      this.loading = true;
      listOutStandCompliance(this.queryParams).then(response => {
        this.outStandComplianceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listOutStandCompliance2(this.queryParams).then(response => {//二维表使用的列表获取
        this.pesticideNameList = response.rows;
        let newList=Object.assign({},response.rows[0]);
        const newObj = newList.pesticideName;
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
        interStandId: null,
        standard: null,
        chinaStandard: null,
        cacStandard: null,
        japanStandard: null,
        euStandard: null,
        usStandard: null,
        koreaStandard: null,
        sourceArea: null,
        methamidophos: null,
        acephate: null,
        Phorate: null,
        oxamyl: null,
        chlorpyrifos: null,
        fenitrothion: null,
        triazophos: null,
        fenthion: null,
        isocarbophos: null,
        leGuo: null,
        isofenphosMethyl: null,
        flucythrinate: null,
        kebaiwei: null,
        Bifenthrin: null,
        chlorfluazuron: null,
        fluorochloridone: null,
        cypermethrin: null,
        fenpyroximate: null,
        bromfenac: null,
        triazolone: null,
        pyrimethanil: null,
        furalaxyl: null,
        profenofos: null,
        carbendazim: null,
        imidacloprid: null,
        epoxiconazole: null,
        abamectin: null,
        emamectinBenzoate: null,
        fludioxonil: null,
        methomyl: null,
        fipronil: null,
        thiamethoxam: null,
        metalaxyl: null,
        mefenoxam: null,
        prochloraz: null,
        linuron: null,
        azoxystrobin: null,
        imidaclothiz: null,
        furametpyr: null,
        lufenuron: null,
        sulfotep: null,
        propiconazole: null,
        dichlorvos: null,
        chlorpyrifosMethyl: null,
        fenvalerate: null,
        fluopicolide: null,
        spirodiclofen: null,
        fenpyrazamine: null,
        propargite: null,
        etoxazole: null,
        pyraclostrobin: null,
        thiacloprid: null,
        spinetoram: null,
        pyridaben: null,
        clofentezine: null,
        chlorfenapyr: null,
        milbemectin: null,
        dimethomorph: null,
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
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.interStandId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加参照国际组织或国家标准合格率情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const interStandId = row.interStandId || this.ids
      getOutStandCompliance(interStandId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改参照国际组织或国家标准合格率情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.interStandId != null) {
            updateOutStandCompliance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutStandCompliance(this.form).then(response => {
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
      const interStandIds = row.interStandId || this.ids;
      this.$modal.confirm('是否确认删除参照国际组织或国家标准合格率情况编号为"' + interStandIds + '"的数据项？').then(function() {
        return delOutStandCompliance(interStandIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('out/outStandCompliance/export', {
        ...this.queryParams
      }, `outStandCompliance_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
