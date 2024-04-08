package com.ruoyi.detection.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

/*
*
* excel导入的实体,动态农药name和value
* 这个实体类版本不是动态的农药name和value，是写死的，具体的农药名字
* 这个只是用来导入用的，在新的方法中，用到的是动态导入，这个类已经没用什么利用价值了
* */
public class agriOut2CitySampleTestDetails {

    // 主表的主键
    private Long citySampleTestDetailsId;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleCode;


    /** 样品名称 */
    @Excel(name = "样品名称")
    private String vegFruName;

    /** 抽样地点 */
    @Excel(name = "抽样地点")
    private String samplingLocation;

    /** 检测单位 */
    @Excel(name = "检测单位")
    private String detectLocation;

    /** 抽样数量（kg/个数） */
    @Excel(name = "抽样数量（kg/个数）")
    private Double samplingQuantity;

    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;

    /** 抽样环节 */
    @Excel(name = "抽样环节")
    private String samplingStageType;

    /** 抽样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽样日期", width = 30)
    private String samplingDate;



    @Excel(name = "甲胺磷", groupName = "检   测   结   果       mg/kg")
    private Double ny1;
    @Excel(name = "乙酰甲胺磷")
    private Double ny2;
    @Excel(name = "甲拌磷")
    private Double ny3;
    @Excel(name = "氧乐果")
    private Double ny4;
    @Excel(name = "毒死蜱")
    private Double ny5;
    @Excel(name = "特丁硫磷")
    private Double ny6;
    @Excel(name = "三唑磷")
    private Double ny7;
    @Excel(name = "水胺硫磷")
    private Double ny8;
    @Excel(name = "治螟磷")
    private Double ny9;
    @Excel(name = "乐果")
    private Double ny10;
    @Excel(name = "甲基异柳磷")
    private Double ny11;
    @Excel(name = "氟虫腈")
    private Double ny12;
    @Excel(name = "克百威")
    private Double ny13;
    @Excel(name = "联苯菊酯")
    private Double ny14;
    @Excel(name = "氯氟氰菊酯")
    private Double ny15;
    @Excel(name = "氟氯氰菊酯")
    private Double ny16;
    @Excel(name = "氯氰菊酯")
    private Double ny17;
    @Excel(name = "甲氰菊酯")
    private Double ny18;
    @Excel(name = "溴氰菊酯")
    private Double ny19;
    @Excel(name = "三唑酮")
    private Double ny20;
    @Excel(name = "百菌清")
    private Double ny21;
    @Excel(name = "腐霉利")
    private Double ny22;
    @Excel(name = "丙溴磷")
    private Double ny23;
    @Excel(name = "多菌灵")
    private Double ny24;
    @Excel(name = "啶虫脒")
    private Double ny25;
    @Excel(name = "苯醚甲环唑")
    private Double ny26;
    @Excel(name = "阿维菌素")
    private Double ny27;
    @Excel(name = "甲氨基阿维菌素苯甲酸盐")
    private Double ny28;
    @Excel(name = "氟啶脲")
    private Double ny29;
    @Excel(name = "灭幼脲")
    private Double ny30;
    @Excel(name = "灭蝇胺")
    private Double ny31;
    @Excel(name = "噻虫嗪")
    private Double ny32;
    @Excel(name = "甲霜灵")
    private Double ny33;
    @Excel(name = "霜霉威")
    private Double ny34;
    @Excel(name = "吡唑醚菌酯")
    private Double ny35;
    @Excel(name = "氯吡脲")
    private Double ny36;
    @Excel(name = "嘧霉胺")
    private Double ny37;
    @Excel(name = "吡虫啉")
    private Double ny38;
    @Excel(name = "呋虫胺")
    private Double ny39;
    @Excel(name = "虱螨脲")
    private Double ny40;
    @Excel(name = "倍硫磷")
    private Double ny41;
    @Excel(name = "抑霉唑")
    private Double ny42;
    @Excel(name = "敌敌畏")
    private Double ny43;
    @Excel(name = "甲基毒死蜱")
    private Double ny44;
    @Excel(name = "氰戊菊酯")
    private Double ny45;
    @Excel(name = "异菌脲")
    private Double ny46;
    @Excel(name = "涕灭威")
    private Double ny47;
    @Excel(name = "烯酰吗啉")
    private Double ny48;
    @Excel(name = "炔螨特")
    private Double ny49;
    @Excel(name = "乙螨唑")
    private Double ny50;
    @Excel(name = "嘧菌酯")
    private Double ny51;
    @Excel(name = "噻虫胺")
    private Double ny52;
    @Excel(name = "丙环唑")
    private Double ny53;
    @Excel(name = "螺虫乙酯")
    private Double ny54;
    @Excel(name = "哒螨灵")
    private Double ny55;
    @Excel(name = "四螨嗪")
    private Double ny56;
    @Excel(name = "虫螨腈")
    private Double ny57;
    @Excel(name = "咪鲜胺")
    private Double ny58;

    /** 国家标准 */
    @Excel(name = "依据国家标准", groupName = "结论")
    private String chinaStandard;

    /** CAC标准 */
    @Excel(name = "CAC标准")
    private String cacStandard;

    /** 日本标准 */
    @Excel(name = "日本标准")
    private String japanStandard;

    /** 欧盟标准 */
    @Excel(name = "欧盟标准")
    private String euStandard;

    /** 美国标准 */
    @Excel(name = "美国标准")
    private String usStandard;

    /** 韩国标准 */
    @Excel(name = "韩国标准")
    private String koreaStandard;

    /** 农药名 */
//    @Excel(name = "农药名")
//    private String pesticideName;
//
//    /** 检测值（mg/kg） */
//    @Excel(name = "检测值")
//    private Double pesticideDetValue;


    public Long getCitySampleTestDetailsId() {
        return citySampleTestDetailsId;
    }

    public void setCitySampleTestDetailsId(Long citySampleTestDetailsId) {
        this.citySampleTestDetailsId = citySampleTestDetailsId;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getVegFruName() {
        return vegFruName;
    }

    public void setVegFruName(String vegFruName) {
        this.vegFruName = vegFruName;
    }

    public String getSamplingLocation() {
        return samplingLocation;
    }

    public void setSamplingLocation(String samplingLocation) {
        this.samplingLocation = samplingLocation;
    }

    public String getDetectLocation() {
        return detectLocation;
    }

    public void setDetectLocation(String detectLocation) {
        this.detectLocation = detectLocation;
    }

    public Double getSamplingQuantity() {
        return samplingQuantity;
    }

    public void setSamplingQuantity(Double samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    public String getSamplingBase() {
        return samplingBase;
    }

    public void setSamplingBase(String samplingBase) {
        this.samplingBase = samplingBase;
    }

    public String getSamplingStageType() {
        return samplingStageType;
    }

    public void setSamplingStageType(String samplingStageType) {
        this.samplingStageType = samplingStageType;
    }

    public String getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(String samplingDate) {
        this.samplingDate = samplingDate;
    }

    public Double getNy1() {
        return ny1;
    }

    public void setNy1(Double ny1) {
        this.ny1 = ny1;
    }

    public Double getNy2() {
        return ny2;
    }

    public void setNy2(Double ny2) {
        this.ny2 = ny2;
    }

    public Double getNy3() {
        return ny3;
    }

    public void setNy3(Double ny3) {
        this.ny3 = ny3;
    }

    public Double getNy4() {
        return ny4;
    }

    public void setNy4(Double ny4) {
        this.ny4 = ny4;
    }

    public Double getNy5() {
        return ny5;
    }

    public void setNy5(Double ny5) {
        this.ny5 = ny5;
    }

    public Double getNy6() {
        return ny6;
    }

    public void setNy6(Double ny6) {
        this.ny6 = ny6;
    }

    public Double getNy7() {
        return ny7;
    }

    public void setNy7(Double ny7) {
        this.ny7 = ny7;
    }

    public Double getNy8() {
        return ny8;
    }

    public void setNy8(Double ny8) {
        this.ny8 = ny8;
    }

    public Double getNy9() {
        return ny9;
    }

    public void setNy9(Double ny9) {
        this.ny9 = ny9;
    }

    public Double getNy10() {
        return ny10;
    }

    public void setNy10(Double ny10) {
        this.ny10 = ny10;
    }

    public Double getNy11() {
        return ny11;
    }

    public void setNy11(Double ny11) {
        this.ny11 = ny11;
    }

    public Double getNy12() {
        return ny12;
    }

    public void setNy12(Double ny12) {
        this.ny12 = ny12;
    }

    public Double getNy13() {
        return ny13;
    }

    public void setNy13(Double ny13) {
        this.ny13 = ny13;
    }

    public Double getNy14() {
        return ny14;
    }

    public void setNy14(Double ny14) {
        this.ny14 = ny14;
    }

    public Double getNy15() {
        return ny15;
    }

    public void setNy15(Double ny15) {
        this.ny15 = ny15;
    }

    public Double getNy16() {
        return ny16;
    }

    public void setNy16(Double ny16) {
        this.ny16 = ny16;
    }

    public Double getNy17() {
        return ny17;
    }

    public void setNy17(Double ny17) {
        this.ny17 = ny17;
    }

    public Double getNy18() {
        return ny18;
    }

    public void setNy18(Double ny18) {
        this.ny18 = ny18;
    }

    public Double getNy19() {
        return ny19;
    }

    public void setNy19(Double ny19) {
        this.ny19 = ny19;
    }

    public Double getNy20() {
        return ny20;
    }

    public void setNy20(Double ny20) {
        this.ny20 = ny20;
    }

    public Double getNy21() {
        return ny21;
    }

    public void setNy21(Double ny21) {
        this.ny21 = ny21;
    }

    public Double getNy22() {
        return ny22;
    }

    public void setNy22(Double ny22) {
        this.ny22 = ny22;
    }

    public Double getNy23() {
        return ny23;
    }

    public void setNy23(Double ny23) {
        this.ny23 = ny23;
    }

    public Double getNy24() {
        return ny24;
    }

    public void setNy24(Double ny24) {
        this.ny24 = ny24;
    }

    public Double getNy25() {
        return ny25;
    }

    public void setNy25(Double ny25) {
        this.ny25 = ny25;
    }

    public Double getNy26() {
        return ny26;
    }

    public void setNy26(Double ny26) {
        this.ny26 = ny26;
    }

    public Double getNy27() {
        return ny27;
    }

    public void setNy27(Double ny27) {
        this.ny27 = ny27;
    }

    public Double getNy28() {
        return ny28;
    }

    public void setNy28(Double ny28) {
        this.ny28 = ny28;
    }

    public Double getNy29() {
        return ny29;
    }

    public void setNy29(Double ny29) {
        this.ny29 = ny29;
    }

    public Double getNy30() {
        return ny30;
    }

    public void setNy30(Double ny30) {
        this.ny30 = ny30;
    }

    public Double getNy31() {
        return ny31;
    }

    public void setNy31(Double ny31) {
        this.ny31 = ny31;
    }

    public Double getNy32() {
        return ny32;
    }

    public void setNy32(Double ny32) {
        this.ny32 = ny32;
    }

    public Double getNy33() {
        return ny33;
    }

    public void setNy33(Double ny33) {
        this.ny33 = ny33;
    }

    public Double getNy34() {
        return ny34;
    }

    public void setNy34(Double ny34) {
        this.ny34 = ny34;
    }

    public Double getNy35() {
        return ny35;
    }

    public void setNy35(Double ny35) {
        this.ny35 = ny35;
    }

    public Double getNy36() {
        return ny36;
    }

    public void setNy36(Double ny36) {
        this.ny36 = ny36;
    }

    public Double getNy37() {
        return ny37;
    }

    public void setNy37(Double ny37) {
        this.ny37 = ny37;
    }

    public Double getNy38() {
        return ny38;
    }

    public void setNy38(Double ny38) {
        this.ny38 = ny38;
    }

    public Double getNy39() {
        return ny39;
    }

    public void setNy39(Double ny39) {
        this.ny39 = ny39;
    }

    public Double getNy40() {
        return ny40;
    }

    public void setNy40(Double ny40) {
        this.ny40 = ny40;
    }

    public Double getNy41() {
        return ny41;
    }

    public void setNy41(Double ny41) {
        this.ny41 = ny41;
    }

    public Double getNy42() {
        return ny42;
    }

    public void setNy42(Double ny42) {
        this.ny42 = ny42;
    }

    public Double getNy43() {
        return ny43;
    }

    public void setNy43(Double ny43) {
        this.ny43 = ny43;
    }

    public Double getNy44() {
        return ny44;
    }

    public void setNy44(Double ny44) {
        this.ny44 = ny44;
    }

    public Double getNy45() {
        return ny45;
    }

    public void setNy45(Double ny45) {
        this.ny45 = ny45;
    }

    public Double getNy46() {
        return ny46;
    }

    public void setNy46(Double ny46) {
        this.ny46 = ny46;
    }

    public Double getNy47() {
        return ny47;
    }

    public void setNy47(Double ny47) {
        this.ny47 = ny47;
    }

    public Double getNy48() {
        return ny48;
    }

    public void setNy48(Double ny48) {
        this.ny48 = ny48;
    }

    public Double getNy49() {
        return ny49;
    }

    public void setNy49(Double ny49) {
        this.ny49 = ny49;
    }

    public Double getNy50() {
        return ny50;
    }

    public void setNy50(Double ny50) {
        this.ny50 = ny50;
    }

    public Double getNy51() {
        return ny51;
    }

    public void setNy51(Double ny51) {
        this.ny51 = ny51;
    }

    public Double getNy52() {
        return ny52;
    }

    public void setNy52(Double ny52) {
        this.ny52 = ny52;
    }

    public Double getNy53() {
        return ny53;
    }

    public void setNy53(Double ny53) {
        this.ny53 = ny53;
    }

    public Double getNy54() {
        return ny54;
    }

    public void setNy54(Double ny54) {
        this.ny54 = ny54;
    }

    public Double getNy55() {
        return ny55;
    }

    public void setNy55(Double ny55) {
        this.ny55 = ny55;
    }

    public Double getNy56() {
        return ny56;
    }

    public void setNy56(Double ny56) {
        this.ny56 = ny56;
    }

    public Double getNy57() {
        return ny57;
    }

    public void setNy57(Double ny57) {
        this.ny57 = ny57;
    }

    public Double getNy58() {
        return ny58;
    }

    public void setNy58(Double ny58) {
        this.ny58 = ny58;
    }

    public String getChinaStandard() {
        return chinaStandard;
    }

    public void setChinaStandard(String chinaStandard) {
        this.chinaStandard = chinaStandard;
    }

    public String getCacStandard() {
        return cacStandard;
    }

    public void setCacStandard(String cacStandard) {
        this.cacStandard = cacStandard;
    }

    public String getJapanStandard() {
        return japanStandard;
    }

    public void setJapanStandard(String japanStandard) {
        this.japanStandard = japanStandard;
    }

    public String getEuStandard() {
        return euStandard;
    }

    public void setEuStandard(String euStandard) {
        this.euStandard = euStandard;
    }

    public String getUsStandard() {
        return usStandard;
    }

    public void setUsStandard(String usStandard) {
        this.usStandard = usStandard;
    }

    public String getKoreaStandard() {
        return koreaStandard;
    }

    public void setKoreaStandard(String koreaStandard) {
        this.koreaStandard = koreaStandard;
    }

    @Override
    public String toString() {
        return "agriOut2CitySampleTestDetails{" +
                "citySampleTestDetailsId=" + citySampleTestDetailsId +
                ", sampleCode='" + sampleCode + '\'' +
                ", vegFruName='" + vegFruName + '\'' +
                ", samplingLocation='" + samplingLocation + '\'' +
                ", detectLocation='" + detectLocation + '\'' +
                ", samplingQuantity=" + samplingQuantity +
                ", samplingBase='" + samplingBase + '\'' +
                ", samplingStageType='" + samplingStageType + '\'' +
                ", samplingDate='" + samplingDate + '\'' +
                ", ny1=" + ny1 +
                ", ny2=" + ny2 +
                ", ny3=" + ny3 +
                ", ny4=" + ny4 +
                ", ny5=" + ny5 +
                ", ny6=" + ny6 +
                ", ny7=" + ny7 +
                ", ny8=" + ny8 +
                ", ny9=" + ny9 +
                ", ny10=" + ny10 +
                ", ny11=" + ny11 +
                ", ny12=" + ny12 +
                ", ny13=" + ny13 +
                ", ny14=" + ny14 +
                ", ny15=" + ny15 +
                ", ny16=" + ny16 +
                ", ny17=" + ny17 +
                ", ny18=" + ny18 +
                ", ny19=" + ny19 +
                ", ny20=" + ny20 +
                ", ny21=" + ny21 +
                ", ny22=" + ny22 +
                ", ny23=" + ny23 +
                ", ny24=" + ny24 +
                ", ny25=" + ny25 +
                ", ny26=" + ny26 +
                ", ny27=" + ny27 +
                ", ny28=" + ny28 +
                ", ny29=" + ny29 +
                ", ny30=" + ny30 +
                ", ny31=" + ny31 +
                ", ny32=" + ny32 +
                ", ny33=" + ny33 +
                ", ny34=" + ny34 +
                ", ny35=" + ny35 +
                ", ny36=" + ny36 +
                ", ny37=" + ny37 +
                ", ny38=" + ny38 +
                ", ny39=" + ny39 +
                ", ny40=" + ny40 +
                ", ny41=" + ny41 +
                ", ny42=" + ny42 +
                ", ny43=" + ny43 +
                ", ny44=" + ny44 +
                ", ny45=" + ny45 +
                ", ny46=" + ny46 +
                ", ny47=" + ny47 +
                ", ny48=" + ny48 +
                ", ny49=" + ny49 +
                ", ny50=" + ny50 +
                ", ny51=" + ny51 +
                ", ny52=" + ny52 +
                ", ny53=" + ny53 +
                ", ny54=" + ny54 +
                ", ny55=" + ny55 +
                ", ny56=" + ny56 +
                ", ny57=" + ny57 +
                ", ny58=" + ny58 +
                ", chinaStandard='" + chinaStandard + '\'' +
                ", cacStandard='" + cacStandard + '\'' +
                ", japanStandard='" + japanStandard + '\'' +
                ", euStandard='" + euStandard + '\'' +
                ", usStandard='" + usStandard + '\'' +
                ", koreaStandard='" + koreaStandard + '\'' +
                '}';
    }
}
