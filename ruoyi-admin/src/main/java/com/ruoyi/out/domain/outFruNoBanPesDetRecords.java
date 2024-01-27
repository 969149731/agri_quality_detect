package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 水果非禁止使用农药检出及超标情况对象 out_fru_no_ban_pes_det_records
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outFruNoBanPesDetRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识每条检测记录 */
    private Long fruNoBanPesDetRecordsId;

    /** 农药名称 */
    @Excel(name = "农药名称")
    private String pesticideName;

    /** 毒死蜱 */
    @Excel(name = "毒死蜱")
    private String chlorpyrifos;

    /** 敌敌畏 */
    @Excel(name = "敌敌畏")
    private String ddvp;

    /** 三唑磷 */
    @Excel(name = "三唑磷")
    private String triazophos;

    /** 丙溴磷 */
    @Excel(name = "丙溴磷")
    private String profenofos;

    /** 甲基毒死蜱 */
    @Excel(name = "甲基毒死蜱")
    private String methylChlorpyrifos;

    /** 氯氰菊酯 */
    @Excel(name = "氯氰菊酯")
    private String lambdaCyhalothrin;

    /** 氰戊菊酯 */
    @Excel(name = "氰戊菊酯")
    private String cypermethrin;

    /** 甲氰菊酯 */
    @Excel(name = "甲氰菊酯")
    private String fenvalerate;

    /** 氯氟氰菊酯 */
    @Excel(name = "氯氟氰菊酯")
    private String chlorfluazuron;

    /** 溴氰菊酯 */
    @Excel(name = "溴氰菊酯")
    private String deltamethrin;

    /** 联苯菊酯 */
    @Excel(name = "联苯菊酯")
    private String bifenthrin;

    /** 异菌脲 */
    @Excel(name = "异菌脲")
    private String fluopicolide;

    /** 腐霉利 */
    @Excel(name = "腐霉利")
    private String metalaxyl;

    /** 阿维菌素 */
    @Excel(name = "阿维菌素")
    private String abamectin;

    /** 甲氨基阿维菌素苯甲酸盐 */
    @Excel(name = "甲氨基阿维菌素苯甲酸盐")
    private String emamectinBenzoate;

    /** 啶虫脒 */
    @Excel(name = "啶虫脒")
    private String imidacloprid;

    /** 烯酰吗啉 */
    @Excel(name = "烯酰吗啉")
    private String oxamyl;

    /** 吡唑醚菌酯 */
    @Excel(name = "吡唑醚菌酯")
    private String pyraclostrobin;

    /** 炔螨特 */
    @Excel(name = "炔螨特")
    private String fenpyroximate;

    /** 乙螨唑 */
    @Excel(name = "乙螨唑")
    private String tebuconazole;

    /** 嘧菌酯 */
    @Excel(name = "嘧菌酯")
    private String azoxystrobin;

    /** 噻虫胺 */
    @Excel(name = "噻虫胺")
    private String thiamethoxam;

    /** 噻虫嗪 */
    @Excel(name = "噻虫嗪")
    private String thiacloprid;

    /** 丙环唑 */
    @Excel(name = "丙环唑")
    private String propiconazole;

    /** 螺虫乙酯 */
    @Excel(name = "螺虫乙酯")
    private String spirotetramat;

    /** 哒螨灵 */
    @Excel(name = "哒螨灵")
    private String spiromesifen;

    /** 苯醚甲环唑 */
    @Excel(name = "苯醚甲环唑")
    private String myclobutanil;

    /** 四螨嗪 */
    @Excel(name = "四螨嗪")
    private String spirodiclofen;

    /** 虫螨腈 */
    @Excel(name = "虫螨腈")
    private String acetamiprid;

    /** 倍硫磷 */
    @Excel(name = "倍硫磷")
    private String phosalone;

    /** 抑霉唑 */
    @Excel(name = "抑霉唑")
    private String Imazalil;

    /** 咪鲜胺 */
    @Excel(name = "咪鲜胺")
    private String ametoctradin;

    /** 检出次数 */
    @Excel(name = "检出次数")
    private Long deteNum;

    /** 超标次数 */
    @Excel(name = "超标次数")
    private Long exDetNum;

    /** 记录生产基地的检查次数 */
    @Excel(name = "记录生产基地的检查次数")
    private Long productionInspectCount;

    /** 记录生产基地的超标次数 */
    @Excel(name = "记录生产基地的超标次数")
    private Long productionExceedCount;

    /** 记录批发市场的检查次数 */
    @Excel(name = "记录批发市场的检查次数")
    private Long wholesaleInspectCount;

    /** 记录批发市场的超标次数 */
    @Excel(name = "记录批发市场的超标次数")
    private Long wholesaleExceedCount;

    /** 记录运输车的检查次数 */
    @Excel(name = "记录运输车的检查次数")
    private Long vehicleInspectCount;

    /** 记录运输车的超标次数 */
    @Excel(name = "记录运输车的超标次数")
    private Long vehicleExceedCount;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId) 
    {
        this.fruNoBanPesDetRecordsId = fruNoBanPesDetRecordsId;
    }

    public Long getFruNoBanPesDetRecordsId() 
    {
        return fruNoBanPesDetRecordsId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setChlorpyrifos(String chlorpyrifos) 
    {
        this.chlorpyrifos = chlorpyrifos;
    }

    public String getChlorpyrifos() 
    {
        return chlorpyrifos;
    }
    public void setDdvp(String ddvp) 
    {
        this.ddvp = ddvp;
    }

    public String getDdvp() 
    {
        return ddvp;
    }
    public void setTriazophos(String triazophos) 
    {
        this.triazophos = triazophos;
    }

    public String getTriazophos() 
    {
        return triazophos;
    }
    public void setProfenofos(String profenofos) 
    {
        this.profenofos = profenofos;
    }

    public String getProfenofos() 
    {
        return profenofos;
    }
    public void setMethylChlorpyrifos(String methylChlorpyrifos) 
    {
        this.methylChlorpyrifos = methylChlorpyrifos;
    }

    public String getMethylChlorpyrifos() 
    {
        return methylChlorpyrifos;
    }
    public void setLambdaCyhalothrin(String lambdaCyhalothrin) 
    {
        this.lambdaCyhalothrin = lambdaCyhalothrin;
    }

    public String getLambdaCyhalothrin() 
    {
        return lambdaCyhalothrin;
    }
    public void setCypermethrin(String cypermethrin) 
    {
        this.cypermethrin = cypermethrin;
    }

    public String getCypermethrin() 
    {
        return cypermethrin;
    }
    public void setFenvalerate(String fenvalerate) 
    {
        this.fenvalerate = fenvalerate;
    }

    public String getFenvalerate() 
    {
        return fenvalerate;
    }
    public void setChlorfluazuron(String chlorfluazuron) 
    {
        this.chlorfluazuron = chlorfluazuron;
    }

    public String getChlorfluazuron() 
    {
        return chlorfluazuron;
    }
    public void setDeltamethrin(String deltamethrin) 
    {
        this.deltamethrin = deltamethrin;
    }

    public String getDeltamethrin() 
    {
        return deltamethrin;
    }
    public void setBifenthrin(String bifenthrin) 
    {
        this.bifenthrin = bifenthrin;
    }

    public String getBifenthrin() 
    {
        return bifenthrin;
    }
    public void setFluopicolide(String fluopicolide) 
    {
        this.fluopicolide = fluopicolide;
    }

    public String getFluopicolide() 
    {
        return fluopicolide;
    }
    public void setMetalaxyl(String metalaxyl) 
    {
        this.metalaxyl = metalaxyl;
    }

    public String getMetalaxyl() 
    {
        return metalaxyl;
    }
    public void setAbamectin(String abamectin) 
    {
        this.abamectin = abamectin;
    }

    public String getAbamectin() 
    {
        return abamectin;
    }
    public void setEmamectinBenzoate(String emamectinBenzoate) 
    {
        this.emamectinBenzoate = emamectinBenzoate;
    }

    public String getEmamectinBenzoate() 
    {
        return emamectinBenzoate;
    }
    public void setImidacloprid(String imidacloprid) 
    {
        this.imidacloprid = imidacloprid;
    }

    public String getImidacloprid() 
    {
        return imidacloprid;
    }
    public void setOxamyl(String oxamyl) 
    {
        this.oxamyl = oxamyl;
    }

    public String getOxamyl() 
    {
        return oxamyl;
    }
    public void setPyraclostrobin(String pyraclostrobin) 
    {
        this.pyraclostrobin = pyraclostrobin;
    }

    public String getPyraclostrobin() 
    {
        return pyraclostrobin;
    }
    public void setFenpyroximate(String fenpyroximate) 
    {
        this.fenpyroximate = fenpyroximate;
    }

    public String getFenpyroximate() 
    {
        return fenpyroximate;
    }
    public void setTebuconazole(String tebuconazole) 
    {
        this.tebuconazole = tebuconazole;
    }

    public String getTebuconazole() 
    {
        return tebuconazole;
    }
    public void setAzoxystrobin(String azoxystrobin) 
    {
        this.azoxystrobin = azoxystrobin;
    }

    public String getAzoxystrobin() 
    {
        return azoxystrobin;
    }
    public void setThiamethoxam(String thiamethoxam) 
    {
        this.thiamethoxam = thiamethoxam;
    }

    public String getThiamethoxam() 
    {
        return thiamethoxam;
    }
    public void setThiacloprid(String thiacloprid) 
    {
        this.thiacloprid = thiacloprid;
    }

    public String getThiacloprid() 
    {
        return thiacloprid;
    }
    public void setPropiconazole(String propiconazole) 
    {
        this.propiconazole = propiconazole;
    }

    public String getPropiconazole() 
    {
        return propiconazole;
    }
    public void setSpirotetramat(String spirotetramat) 
    {
        this.spirotetramat = spirotetramat;
    }

    public String getSpirotetramat() 
    {
        return spirotetramat;
    }
    public void setSpiromesifen(String spiromesifen) 
    {
        this.spiromesifen = spiromesifen;
    }

    public String getSpiromesifen() 
    {
        return spiromesifen;
    }
    public void setMyclobutanil(String myclobutanil) 
    {
        this.myclobutanil = myclobutanil;
    }

    public String getMyclobutanil() 
    {
        return myclobutanil;
    }
    public void setSpirodiclofen(String spirodiclofen) 
    {
        this.spirodiclofen = spirodiclofen;
    }

    public String getSpirodiclofen() 
    {
        return spirodiclofen;
    }
    public void setAcetamiprid(String acetamiprid) 
    {
        this.acetamiprid = acetamiprid;
    }

    public String getAcetamiprid() 
    {
        return acetamiprid;
    }
    public void setPhosalone(String phosalone) 
    {
        this.phosalone = phosalone;
    }

    public String getPhosalone() 
    {
        return phosalone;
    }
    public void setImazalil(String Imazalil) 
    {
        this.Imazalil = Imazalil;
    }

    public String getImazalil() 
    {
        return Imazalil;
    }
    public void setAmetoctradin(String ametoctradin) 
    {
        this.ametoctradin = ametoctradin;
    }

    public String getAmetoctradin() 
    {
        return ametoctradin;
    }
    public void setDeteNum(Long deteNum) 
    {
        this.deteNum = deteNum;
    }

    public Long getDeteNum() 
    {
        return deteNum;
    }
    public void setExDetNum(Long exDetNum) 
    {
        this.exDetNum = exDetNum;
    }

    public Long getExDetNum() 
    {
        return exDetNum;
    }
    public void setProductionInspectCount(Long productionInspectCount) 
    {
        this.productionInspectCount = productionInspectCount;
    }

    public Long getProductionInspectCount() 
    {
        return productionInspectCount;
    }
    public void setProductionExceedCount(Long productionExceedCount) 
    {
        this.productionExceedCount = productionExceedCount;
    }

    public Long getProductionExceedCount() 
    {
        return productionExceedCount;
    }
    public void setWholesaleInspectCount(Long wholesaleInspectCount) 
    {
        this.wholesaleInspectCount = wholesaleInspectCount;
    }

    public Long getWholesaleInspectCount() 
    {
        return wholesaleInspectCount;
    }
    public void setWholesaleExceedCount(Long wholesaleExceedCount) 
    {
        this.wholesaleExceedCount = wholesaleExceedCount;
    }

    public Long getWholesaleExceedCount() 
    {
        return wholesaleExceedCount;
    }
    public void setVehicleInspectCount(Long vehicleInspectCount) 
    {
        this.vehicleInspectCount = vehicleInspectCount;
    }

    public Long getVehicleInspectCount() 
    {
        return vehicleInspectCount;
    }
    public void setVehicleExceedCount(Long vehicleExceedCount) 
    {
        this.vehicleExceedCount = vehicleExceedCount;
    }

    public Long getVehicleExceedCount() 
    {
        return vehicleExceedCount;
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
            .append("fruNoBanPesDetRecordsId", getFruNoBanPesDetRecordsId())
            .append("pesticideName", getPesticideName())
            .append("chlorpyrifos", getChlorpyrifos())
            .append("ddvp", getDdvp())
            .append("triazophos", getTriazophos())
            .append("profenofos", getProfenofos())
            .append("methylChlorpyrifos", getMethylChlorpyrifos())
            .append("lambdaCyhalothrin", getLambdaCyhalothrin())
            .append("cypermethrin", getCypermethrin())
            .append("fenvalerate", getFenvalerate())
            .append("chlorfluazuron", getChlorfluazuron())
            .append("deltamethrin", getDeltamethrin())
            .append("bifenthrin", getBifenthrin())
            .append("fluopicolide", getFluopicolide())
            .append("metalaxyl", getMetalaxyl())
            .append("abamectin", getAbamectin())
            .append("emamectinBenzoate", getEmamectinBenzoate())
            .append("imidacloprid", getImidacloprid())
            .append("oxamyl", getOxamyl())
            .append("pyraclostrobin", getPyraclostrobin())
            .append("fenpyroximate", getFenpyroximate())
            .append("tebuconazole", getTebuconazole())
            .append("azoxystrobin", getAzoxystrobin())
            .append("thiamethoxam", getThiamethoxam())
            .append("thiacloprid", getThiacloprid())
            .append("propiconazole", getPropiconazole())
            .append("spirotetramat", getSpirotetramat())
            .append("spiromesifen", getSpiromesifen())
            .append("myclobutanil", getMyclobutanil())
            .append("spirodiclofen", getSpirodiclofen())
            .append("acetamiprid", getAcetamiprid())
            .append("phosalone", getPhosalone())
            .append("Imazalil", getImazalil())
            .append("ametoctradin", getAmetoctradin())
            .append("deteNum", getDeteNum())
            .append("exDetNum", getExDetNum())
            .append("productionInspectCount", getProductionInspectCount())
            .append("productionExceedCount", getProductionExceedCount())
            .append("wholesaleInspectCount", getWholesaleInspectCount())
            .append("wholesaleExceedCount", getWholesaleExceedCount())
            .append("vehicleInspectCount", getVehicleInspectCount())
            .append("vehicleExceedCount", getVehicleExceedCount())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
