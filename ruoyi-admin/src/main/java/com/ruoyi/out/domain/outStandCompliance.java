package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 参照国际组织或国家标准合格率情况对象 out_stand_compliance
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outStandCompliance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long interStandId;

    /** 参照的标准 */
    @Excel(name = "参照的标准")
    private String standard;

    /** 国家标准 */
    @Excel(name = "国家标准")
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

    /** 数据源于哪个市区县 */
    @Excel(name = "数据源于哪个市区县")
    private String sourceArea;

    /** 甲胺磷超标数 */
    @Excel(name = "甲胺磷超标数")
    private Long methamidophos;

    /** 乙酰甲胺磷超标数 */
    @Excel(name = "乙酰甲胺磷超标数")
    private Long acephate;

    /** 甲拌磷超标数 */
    @Excel(name = "甲拌磷超标数")
    private Long Phorate;

    /** 氧乐果超标数 */
    @Excel(name = "氧乐果超标数")
    private Long oxamyl;

    /** 毒死蜱超标数 */
    @Excel(name = "毒死蜱超标数")
    private Long chlorpyrifos;

    /** 特丁硫磷超标数 */
    @Excel(name = "特丁硫磷超标数")
    private Long fenitrothion;

    /** 三唑磷超标数 */
    @Excel(name = "三唑磷超标数")
    private Long triazophos;

    /** 水胺硫磷超标数 */
    @Excel(name = "水胺硫磷超标数")
    private Long fenthion;

    /** 治螟磷超标数 */
    @Excel(name = "治螟磷超标数")
    private Long isocarbophos;

    /** 乐果超标数 */
    @Excel(name = "乐果超标数")
    private Long leGuo;

    /** 甲基异柳磷超标数 */
    @Excel(name = "甲基异柳磷超标数")
    private Long isofenphosMethyl;

    /** 氟虫腈超标数 */
    @Excel(name = "氟虫腈超标数")
    private Long flucythrinate;

    /** 克百威超标数 */
    @Excel(name = "克百威超标数")
    private Long kebaiwei;

    /** 联苯菊酯超标数 */
    @Excel(name = "联苯菊酯超标数")
    private Long Bifenthrin;

    /** 氯氟氰菊酯超标数 */
    @Excel(name = "氯氟氰菊酯超标数")
    private Long chlorfluazuron;

    /** 氟氯氰菊酯超标数 */
    @Excel(name = "氟氯氰菊酯超标数")
    private Long fluorochloridone;

    /** 氯氰菊酯超标数 */
    @Excel(name = "氯氰菊酯超标数")
    private Long cypermethrin;

    /** 甲氰菊酯超标数 */
    @Excel(name = "甲氰菊酯超标数")
    private Long fenpyroximate;

    /** 溴氰菊酯超标数 */
    @Excel(name = "溴氰菊酯超标数")
    private Long bromfenac;

    /** 三唑酮超标数 */
    @Excel(name = "三唑酮超标数")
    private Long triazolone;

    /** 百菌清超标数 */
    @Excel(name = "百菌清超标数")
    private Long pyrimethanil;

    /** 腐霉利超标数 */
    @Excel(name = "腐霉利超标数")
    private Long furalaxyl;

    /** 丙溴磷超标数 */
    @Excel(name = "丙溴磷超标数")
    private Long profenofos;

    /** 多菌灵超标数 */
    @Excel(name = "多菌灵超标数")
    private Long carbendazim;

    /** 啶虫脒超标数 */
    @Excel(name = "啶虫脒超标数")
    private Long imidacloprid;

    /** 苯醚甲环唑超标数 */
    @Excel(name = "苯醚甲环唑超标数")
    private Long epoxiconazole;

    /** 阿维菌素超标数 */
    @Excel(name = "阿维菌素超标数")
    private Long abamectin;

    /** 甲氨基阿维菌素苯甲酸盐超标数 */
    @Excel(name = "甲氨基阿维菌素苯甲酸盐超标数")
    private Long emamectinBenzoate;

    /** 氟啶脲超标数 */
    @Excel(name = "氟啶脲超标数")
    private Long fludioxonil;

    /** 灭幼脲超标数 */
    @Excel(name = "灭幼脲超标数")
    private Long methomyl;

    /** 灭蝇胺超标数 */
    @Excel(name = "灭蝇胺超标数")
    private Long fipronil;

    /** 噻虫嗪超标数 */
    @Excel(name = "噻虫嗪超标数")
    private Long thiamethoxam;

    /** 甲霜灵超标数 */
    @Excel(name = "甲霜灵超标数")
    private Long metalaxyl;

    /** 霜霉威超标数 */
    @Excel(name = "霜霉威超标数")
    private Long mefenoxam;

    /** 吡唑醚菌酯超标数 */
    @Excel(name = "吡唑醚菌酯超标数")
    private Long prochloraz;

    /** 氯吡脲超标数 */
    @Excel(name = "氯吡脲超标数")
    private Long linuron;

    /** 嘧霉胺超标数 */
    @Excel(name = "嘧霉胺超标数")
    private Long azoxystrobin;

    /** 吡虫啉超标数 */
    @Excel(name = "吡虫啉超标数")
    private Long imidaclothiz;

    /** 呋虫胺超标数 */
    @Excel(name = "呋虫胺超标数")
    private Long furametpyr;

    /** 虱螨脲超标数 */
    @Excel(name = "虱螨脲超标数")
    private Long lufenuron;

    /** 倍硫磷超标数 */
    @Excel(name = "倍硫磷超标数")
    private Long sulfotep;

    /** 抑霉唑超标数 */
    @Excel(name = "抑霉唑超标数")
    private Long propiconazole;

    /** 敌敌畏超标数 */
    @Excel(name = "敌敌畏超标数")
    private Long dichlorvos;

    /** 甲基毒死蜱超标数 */
    @Excel(name = "甲基毒死蜱超标数")
    private Long chlorpyrifosMethyl;

    /** 氰戊菊酯超标数 */
    @Excel(name = "氰戊菊酯超标数")
    private Long fenvalerate;

    /** 异菌脲超标数 */
    @Excel(name = "异菌脲超标数")
    private Long fluopicolide;

    /** 涕灭威超标数 */
    @Excel(name = "涕灭威超标数")
    private Long spirodiclofen;

    /** 烯酰吗啉超标数 */
    @Excel(name = "烯酰吗啉超标数")
    private Long fenpyrazamine;

    /** 炔螨特超标数 */
    @Excel(name = "炔螨特超标数")
    private Long propargite;

    /** 乙螨唑超标数 */
    @Excel(name = "乙螨唑超标数")
    private Long etoxazole;

    /** 嘧菌酯超标数 */
    @Excel(name = "嘧菌酯超标数")
    private Long pyraclostrobin;

    /** 噻虫胺超标数 */
    @Excel(name = "噻虫胺超标数")
    private Long thiacloprid;

    /** 螺虫乙酯超标数 */
    @Excel(name = "螺虫乙酯超标数")
    private Long spinetoram;

    /** 哒螨灵超标数 */
    @Excel(name = "哒螨灵超标数")
    private Long pyridaben;

    /** 四螨嗪超标数 */
    @Excel(name = "四螨嗪超标数")
    private Long clofentezine;

    /** 虫螨腈超标数 */
    @Excel(name = "虫螨腈超标数")
    private Long chlorfenapyr;

    /** 咪鲜胺超标数 */
    @Excel(name = "咪鲜胺超标数")
    private Long milbemectin;

    /** 双甲脒超标数 */
    @Excel(name = "双甲脒超标数")
    private Long dimethomorph;

    /** 抽检数量 */
    @Excel(name = "抽检数量")
    private Long samplingQuantity;

    /** 合格数 */
    @Excel(name = "合格数")
    private Long qualifiedNumber;

    /** 合格率（百分比） */
    @Excel(name = "合格率", readConverterExp = "百=分比")
    private BigDecimal qualificationRate;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setInterStandId(Long interStandId) 
    {
        this.interStandId = interStandId;
    }

    public Long getInterStandId() 
    {
        return interStandId;
    }
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setChinaStandard(String chinaStandard) 
    {
        this.chinaStandard = chinaStandard;
    }

    public String getChinaStandard() 
    {
        return chinaStandard;
    }
    public void setCacStandard(String cacStandard) 
    {
        this.cacStandard = cacStandard;
    }

    public String getCacStandard() 
    {
        return cacStandard;
    }
    public void setJapanStandard(String japanStandard) 
    {
        this.japanStandard = japanStandard;
    }

    public String getJapanStandard() 
    {
        return japanStandard;
    }
    public void setEuStandard(String euStandard) 
    {
        this.euStandard = euStandard;
    }

    public String getEuStandard() 
    {
        return euStandard;
    }
    public void setUsStandard(String usStandard) 
    {
        this.usStandard = usStandard;
    }

    public String getUsStandard() 
    {
        return usStandard;
    }
    public void setKoreaStandard(String koreaStandard) 
    {
        this.koreaStandard = koreaStandard;
    }

    public String getKoreaStandard() 
    {
        return koreaStandard;
    }
    public void setSourceArea(String sourceArea) 
    {
        this.sourceArea = sourceArea;
    }

    public String getSourceArea() 
    {
        return sourceArea;
    }
    public void setMethamidophos(Long methamidophos) 
    {
        this.methamidophos = methamidophos;
    }

    public Long getMethamidophos() 
    {
        return methamidophos;
    }
    public void setAcephate(Long acephate) 
    {
        this.acephate = acephate;
    }

    public Long getAcephate() 
    {
        return acephate;
    }
    public void setPhorate(Long Phorate) 
    {
        this.Phorate = Phorate;
    }

    public Long getPhorate() 
    {
        return Phorate;
    }
    public void setOxamyl(Long oxamyl) 
    {
        this.oxamyl = oxamyl;
    }

    public Long getOxamyl() 
    {
        return oxamyl;
    }
    public void setChlorpyrifos(Long chlorpyrifos) 
    {
        this.chlorpyrifos = chlorpyrifos;
    }

    public Long getChlorpyrifos() 
    {
        return chlorpyrifos;
    }
    public void setFenitrothion(Long fenitrothion) 
    {
        this.fenitrothion = fenitrothion;
    }

    public Long getFenitrothion() 
    {
        return fenitrothion;
    }
    public void setTriazophos(Long triazophos) 
    {
        this.triazophos = triazophos;
    }

    public Long getTriazophos() 
    {
        return triazophos;
    }
    public void setFenthion(Long fenthion) 
    {
        this.fenthion = fenthion;
    }

    public Long getFenthion() 
    {
        return fenthion;
    }
    public void setIsocarbophos(Long isocarbophos) 
    {
        this.isocarbophos = isocarbophos;
    }

    public Long getIsocarbophos() 
    {
        return isocarbophos;
    }
    public void setLeGuo(Long leGuo) 
    {
        this.leGuo = leGuo;
    }

    public Long getLeGuo() 
    {
        return leGuo;
    }
    public void setIsofenphosMethyl(Long isofenphosMethyl) 
    {
        this.isofenphosMethyl = isofenphosMethyl;
    }

    public Long getIsofenphosMethyl() 
    {
        return isofenphosMethyl;
    }
    public void setFlucythrinate(Long flucythrinate) 
    {
        this.flucythrinate = flucythrinate;
    }

    public Long getFlucythrinate() 
    {
        return flucythrinate;
    }
    public void setKebaiwei(Long kebaiwei) 
    {
        this.kebaiwei = kebaiwei;
    }

    public Long getKebaiwei() 
    {
        return kebaiwei;
    }
    public void setBifenthrin(Long Bifenthrin) 
    {
        this.Bifenthrin = Bifenthrin;
    }

    public Long getBifenthrin() 
    {
        return Bifenthrin;
    }
    public void setChlorfluazuron(Long chlorfluazuron) 
    {
        this.chlorfluazuron = chlorfluazuron;
    }

    public Long getChlorfluazuron() 
    {
        return chlorfluazuron;
    }
    public void setFluorochloridone(Long fluorochloridone) 
    {
        this.fluorochloridone = fluorochloridone;
    }

    public Long getFluorochloridone() 
    {
        return fluorochloridone;
    }
    public void setCypermethrin(Long cypermethrin) 
    {
        this.cypermethrin = cypermethrin;
    }

    public Long getCypermethrin() 
    {
        return cypermethrin;
    }
    public void setFenpyroximate(Long fenpyroximate) 
    {
        this.fenpyroximate = fenpyroximate;
    }

    public Long getFenpyroximate() 
    {
        return fenpyroximate;
    }
    public void setBromfenac(Long bromfenac) 
    {
        this.bromfenac = bromfenac;
    }

    public Long getBromfenac() 
    {
        return bromfenac;
    }
    public void setTriazolone(Long triazolone) 
    {
        this.triazolone = triazolone;
    }

    public Long getTriazolone() 
    {
        return triazolone;
    }
    public void setPyrimethanil(Long pyrimethanil) 
    {
        this.pyrimethanil = pyrimethanil;
    }

    public Long getPyrimethanil() 
    {
        return pyrimethanil;
    }
    public void setFuralaxyl(Long furalaxyl) 
    {
        this.furalaxyl = furalaxyl;
    }

    public Long getFuralaxyl() 
    {
        return furalaxyl;
    }
    public void setProfenofos(Long profenofos) 
    {
        this.profenofos = profenofos;
    }

    public Long getProfenofos() 
    {
        return profenofos;
    }
    public void setCarbendazim(Long carbendazim) 
    {
        this.carbendazim = carbendazim;
    }

    public Long getCarbendazim() 
    {
        return carbendazim;
    }
    public void setImidacloprid(Long imidacloprid) 
    {
        this.imidacloprid = imidacloprid;
    }

    public Long getImidacloprid() 
    {
        return imidacloprid;
    }
    public void setEpoxiconazole(Long epoxiconazole) 
    {
        this.epoxiconazole = epoxiconazole;
    }

    public Long getEpoxiconazole() 
    {
        return epoxiconazole;
    }
    public void setAbamectin(Long abamectin) 
    {
        this.abamectin = abamectin;
    }

    public Long getAbamectin() 
    {
        return abamectin;
    }
    public void setEmamectinBenzoate(Long emamectinBenzoate) 
    {
        this.emamectinBenzoate = emamectinBenzoate;
    }

    public Long getEmamectinBenzoate() 
    {
        return emamectinBenzoate;
    }
    public void setFludioxonil(Long fludioxonil) 
    {
        this.fludioxonil = fludioxonil;
    }

    public Long getFludioxonil() 
    {
        return fludioxonil;
    }
    public void setMethomyl(Long methomyl) 
    {
        this.methomyl = methomyl;
    }

    public Long getMethomyl() 
    {
        return methomyl;
    }
    public void setFipronil(Long fipronil) 
    {
        this.fipronil = fipronil;
    }

    public Long getFipronil() 
    {
        return fipronil;
    }
    public void setThiamethoxam(Long thiamethoxam) 
    {
        this.thiamethoxam = thiamethoxam;
    }

    public Long getThiamethoxam() 
    {
        return thiamethoxam;
    }
    public void setMetalaxyl(Long metalaxyl) 
    {
        this.metalaxyl = metalaxyl;
    }

    public Long getMetalaxyl() 
    {
        return metalaxyl;
    }
    public void setMefenoxam(Long mefenoxam) 
    {
        this.mefenoxam = mefenoxam;
    }

    public Long getMefenoxam() 
    {
        return mefenoxam;
    }
    public void setProchloraz(Long prochloraz) 
    {
        this.prochloraz = prochloraz;
    }

    public Long getProchloraz() 
    {
        return prochloraz;
    }
    public void setLinuron(Long linuron) 
    {
        this.linuron = linuron;
    }

    public Long getLinuron() 
    {
        return linuron;
    }
    public void setAzoxystrobin(Long azoxystrobin) 
    {
        this.azoxystrobin = azoxystrobin;
    }

    public Long getAzoxystrobin() 
    {
        return azoxystrobin;
    }
    public void setImidaclothiz(Long imidaclothiz) 
    {
        this.imidaclothiz = imidaclothiz;
    }

    public Long getImidaclothiz() 
    {
        return imidaclothiz;
    }
    public void setFurametpyr(Long furametpyr) 
    {
        this.furametpyr = furametpyr;
    }

    public Long getFurametpyr() 
    {
        return furametpyr;
    }
    public void setLufenuron(Long lufenuron) 
    {
        this.lufenuron = lufenuron;
    }

    public Long getLufenuron() 
    {
        return lufenuron;
    }
    public void setSulfotep(Long sulfotep) 
    {
        this.sulfotep = sulfotep;
    }

    public Long getSulfotep() 
    {
        return sulfotep;
    }
    public void setPropiconazole(Long propiconazole) 
    {
        this.propiconazole = propiconazole;
    }

    public Long getPropiconazole() 
    {
        return propiconazole;
    }
    public void setDichlorvos(Long dichlorvos) 
    {
        this.dichlorvos = dichlorvos;
    }

    public Long getDichlorvos() 
    {
        return dichlorvos;
    }
    public void setChlorpyrifosMethyl(Long chlorpyrifosMethyl) 
    {
        this.chlorpyrifosMethyl = chlorpyrifosMethyl;
    }

    public Long getChlorpyrifosMethyl() 
    {
        return chlorpyrifosMethyl;
    }
    public void setFenvalerate(Long fenvalerate) 
    {
        this.fenvalerate = fenvalerate;
    }

    public Long getFenvalerate() 
    {
        return fenvalerate;
    }
    public void setFluopicolide(Long fluopicolide) 
    {
        this.fluopicolide = fluopicolide;
    }

    public Long getFluopicolide() 
    {
        return fluopicolide;
    }
    public void setSpirodiclofen(Long spirodiclofen) 
    {
        this.spirodiclofen = spirodiclofen;
    }

    public Long getSpirodiclofen() 
    {
        return spirodiclofen;
    }
    public void setFenpyrazamine(Long fenpyrazamine) 
    {
        this.fenpyrazamine = fenpyrazamine;
    }

    public Long getFenpyrazamine() 
    {
        return fenpyrazamine;
    }
    public void setPropargite(Long propargite) 
    {
        this.propargite = propargite;
    }

    public Long getPropargite() 
    {
        return propargite;
    }
    public void setEtoxazole(Long etoxazole) 
    {
        this.etoxazole = etoxazole;
    }

    public Long getEtoxazole() 
    {
        return etoxazole;
    }
    public void setPyraclostrobin(Long pyraclostrobin) 
    {
        this.pyraclostrobin = pyraclostrobin;
    }

    public Long getPyraclostrobin() 
    {
        return pyraclostrobin;
    }
    public void setThiacloprid(Long thiacloprid) 
    {
        this.thiacloprid = thiacloprid;
    }

    public Long getThiacloprid() 
    {
        return thiacloprid;
    }
    public void setSpinetoram(Long spinetoram) 
    {
        this.spinetoram = spinetoram;
    }

    public Long getSpinetoram() 
    {
        return spinetoram;
    }
    public void setPyridaben(Long pyridaben) 
    {
        this.pyridaben = pyridaben;
    }

    public Long getPyridaben() 
    {
        return pyridaben;
    }
    public void setClofentezine(Long clofentezine) 
    {
        this.clofentezine = clofentezine;
    }

    public Long getClofentezine() 
    {
        return clofentezine;
    }
    public void setChlorfenapyr(Long chlorfenapyr) 
    {
        this.chlorfenapyr = chlorfenapyr;
    }

    public Long getChlorfenapyr() 
    {
        return chlorfenapyr;
    }
    public void setMilbemectin(Long milbemectin) 
    {
        this.milbemectin = milbemectin;
    }

    public Long getMilbemectin() 
    {
        return milbemectin;
    }
    public void setDimethomorph(Long dimethomorph) 
    {
        this.dimethomorph = dimethomorph;
    }

    public Long getDimethomorph() 
    {
        return dimethomorph;
    }
    public void setSamplingQuantity(Long samplingQuantity) 
    {
        this.samplingQuantity = samplingQuantity;
    }

    public Long getSamplingQuantity() 
    {
        return samplingQuantity;
    }
    public void setQualifiedNumber(Long qualifiedNumber) 
    {
        this.qualifiedNumber = qualifiedNumber;
    }

    public Long getQualifiedNumber() 
    {
        return qualifiedNumber;
    }
    public void setQualificationRate(BigDecimal qualificationRate) 
    {
        this.qualificationRate = qualificationRate;
    }

    public BigDecimal getQualificationRate() 
    {
        return qualificationRate;
    }
    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("interStandId", getInterStandId())
            .append("standard", getStandard())
            .append("chinaStandard", getChinaStandard())
            .append("cacStandard", getCacStandard())
            .append("japanStandard", getJapanStandard())
            .append("euStandard", getEuStandard())
            .append("usStandard", getUsStandard())
            .append("koreaStandard", getKoreaStandard())
            .append("sourceArea", getSourceArea())
            .append("methamidophos", getMethamidophos())
            .append("acephate", getAcephate())
            .append("Phorate", getPhorate())
            .append("oxamyl", getOxamyl())
            .append("chlorpyrifos", getChlorpyrifos())
            .append("fenitrothion", getFenitrothion())
            .append("triazophos", getTriazophos())
            .append("fenthion", getFenthion())
            .append("isocarbophos", getIsocarbophos())
            .append("leGuo", getLeGuo())
            .append("isofenphosMethyl", getIsofenphosMethyl())
            .append("flucythrinate", getFlucythrinate())
            .append("kebaiwei", getKebaiwei())
            .append("Bifenthrin", getBifenthrin())
            .append("chlorfluazuron", getChlorfluazuron())
            .append("fluorochloridone", getFluorochloridone())
            .append("cypermethrin", getCypermethrin())
            .append("fenpyroximate", getFenpyroximate())
            .append("bromfenac", getBromfenac())
            .append("triazolone", getTriazolone())
            .append("pyrimethanil", getPyrimethanil())
            .append("furalaxyl", getFuralaxyl())
            .append("profenofos", getProfenofos())
            .append("carbendazim", getCarbendazim())
            .append("imidacloprid", getImidacloprid())
            .append("epoxiconazole", getEpoxiconazole())
            .append("abamectin", getAbamectin())
            .append("emamectinBenzoate", getEmamectinBenzoate())
            .append("fludioxonil", getFludioxonil())
            .append("methomyl", getMethomyl())
            .append("fipronil", getFipronil())
            .append("thiamethoxam", getThiamethoxam())
            .append("metalaxyl", getMetalaxyl())
            .append("mefenoxam", getMefenoxam())
            .append("prochloraz", getProchloraz())
            .append("linuron", getLinuron())
            .append("azoxystrobin", getAzoxystrobin())
            .append("imidaclothiz", getImidaclothiz())
            .append("furametpyr", getFurametpyr())
            .append("lufenuron", getLufenuron())
            .append("sulfotep", getSulfotep())
            .append("propiconazole", getPropiconazole())
            .append("dichlorvos", getDichlorvos())
            .append("chlorpyrifosMethyl", getChlorpyrifosMethyl())
            .append("fenvalerate", getFenvalerate())
            .append("fluopicolide", getFluopicolide())
            .append("spirodiclofen", getSpirodiclofen())
            .append("fenpyrazamine", getFenpyrazamine())
            .append("propargite", getPropargite())
            .append("etoxazole", getEtoxazole())
            .append("pyraclostrobin", getPyraclostrobin())
            .append("thiacloprid", getThiacloprid())
            .append("spinetoram", getSpinetoram())
            .append("pyridaben", getPyridaben())
            .append("clofentezine", getClofentezine())
            .append("chlorfenapyr", getChlorfenapyr())
            .append("milbemectin", getMilbemectin())
            .append("dimethomorph", getDimethomorph())
            .append("samplingQuantity", getSamplingQuantity())
            .append("qualifiedNumber", getQualifiedNumber())
            .append("qualificationRate", getQualificationRate())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
