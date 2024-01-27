package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蔬菜上非禁止使用农药检出及超标情况对象 out_veg_no_ban_pes_det_records
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outVegNoBanPesDetRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识每条检测记录 */
    private Long vegNoBanPesDetRecordsId;

    /** 农药名称 */
    @Excel(name = "农药名称")
    private String pesticideName;

    /** 联苯菊酯 */
    @Excel(name = "联苯菊酯")
    private String bifenthrin;

    /** 氯氟氰菊酯 */
    @Excel(name = "氯氟氰菊酯")
    private String chlorfluazuron;

    /** 氟氯氰菊酯 */
    @Excel(name = "氟氯氰菊酯")
    private String flucycloxuron;

    /** 氯氰菊酯 */
    @Excel(name = "氯氰菊酯")
    private String lambdaCyhalothrin;

    /** 甲氰菊酯 */
    @Excel(name = "甲氰菊酯")
    private String fenvalerate;

    /** 溴氰菊酯 */
    @Excel(name = "溴氰菊酯")
    private String deltamethrin;

    /** 三唑酮 */
    @Excel(name = "三唑酮")
    private String triazolone;

    /** 百菌清 */
    @Excel(name = "百菌清")
    private String carbendazim;

    /** 腐霉利 */
    @Excel(name = "腐霉利")
    private String metalaxyl;

    /** 丙溴磷 */
    @Excel(name = "丙溴磷")
    private String profenofos;

    /** 多菌灵 */
    @Excel(name = "多菌灵")
    private String carbaryl;

    /** 啶虫脒 */
    @Excel(name = "啶虫脒")
    private String imidacloprid;

    /** 苯醚甲环唑 */
    @Excel(name = "苯醚甲环唑")
    private String myclobutanil;

    /** 阿维菌素 */
    @Excel(name = "阿维菌素")
    private String abamectin;

    /** 甲氨基阿维菌素苯甲酸盐 */
    @Excel(name = "甲氨基阿维菌素苯甲酸盐")
    private String emamectinBenzoate;

    /** 氟啶脲 */
    @Excel(name = "氟啶脲")
    private String fludioxonil;

    /** 灭幼脲 */
    @Excel(name = "灭幼脲")
    private String diflubenzuron;

    /** 灭蝇胺 */
    @Excel(name = "灭蝇胺")
    private String cyromazine;

    /** 噻虫嗪 */
    @Excel(name = "噻虫嗪")
    private String thiamethoxam;

    /** 甲霜灵 */
    @Excel(name = "甲霜灵")
    private String mancozeb;

    /** 霜霉威 */
    @Excel(name = "霜霉威")
    private String metalaxylM;

    /** 吡唑醚菌酯 */
    @Excel(name = "吡唑醚菌酯")
    private String pyraclostrobin;

    /** 氯吡脲 */
    @Excel(name = "氯吡脲")
    private String chlorantraniliprole;

    /** 嘧霉胺 */
    @Excel(name = "嘧霉胺")
    private String azoxystrobin;

    /** 吡虫啉 */
    @Excel(name = "吡虫啉")
    private String imidaclothiz;

    /** 呋虫胺 */
    @Excel(name = "呋虫胺")
    private String flonicamid;

    /** 虱螨脲 */
    @Excel(name = "虱螨脲")
    private String lufenuron;

    /** 倍硫磷 */
    @Excel(name = "倍硫磷")
    private String phosalone;

    /** 抑霉唑 */
    @Excel(name = "抑霉唑")
    private String difenoconazole;

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

    public void setVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId) 
    {
        this.vegNoBanPesDetRecordsId = vegNoBanPesDetRecordsId;
    }

    public Long getVegNoBanPesDetRecordsId() 
    {
        return vegNoBanPesDetRecordsId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setBifenthrin(String bifenthrin) 
    {
        this.bifenthrin = bifenthrin;
    }

    public String getBifenthrin() 
    {
        return bifenthrin;
    }
    public void setChlorfluazuron(String chlorfluazuron) 
    {
        this.chlorfluazuron = chlorfluazuron;
    }

    public String getChlorfluazuron() 
    {
        return chlorfluazuron;
    }
    public void setFlucycloxuron(String flucycloxuron) 
    {
        this.flucycloxuron = flucycloxuron;
    }

    public String getFlucycloxuron() 
    {
        return flucycloxuron;
    }
    public void setLambdaCyhalothrin(String lambdaCyhalothrin) 
    {
        this.lambdaCyhalothrin = lambdaCyhalothrin;
    }

    public String getLambdaCyhalothrin() 
    {
        return lambdaCyhalothrin;
    }
    public void setFenvalerate(String fenvalerate) 
    {
        this.fenvalerate = fenvalerate;
    }

    public String getFenvalerate() 
    {
        return fenvalerate;
    }
    public void setDeltamethrin(String deltamethrin) 
    {
        this.deltamethrin = deltamethrin;
    }

    public String getDeltamethrin() 
    {
        return deltamethrin;
    }
    public void setTriazolone(String triazolone) 
    {
        this.triazolone = triazolone;
    }

    public String getTriazolone() 
    {
        return triazolone;
    }
    public void setCarbendazim(String carbendazim) 
    {
        this.carbendazim = carbendazim;
    }

    public String getCarbendazim() 
    {
        return carbendazim;
    }
    public void setMetalaxyl(String metalaxyl) 
    {
        this.metalaxyl = metalaxyl;
    }

    public String getMetalaxyl() 
    {
        return metalaxyl;
    }
    public void setProfenofos(String profenofos) 
    {
        this.profenofos = profenofos;
    }

    public String getProfenofos() 
    {
        return profenofos;
    }
    public void setCarbaryl(String carbaryl) 
    {
        this.carbaryl = carbaryl;
    }

    public String getCarbaryl() 
    {
        return carbaryl;
    }
    public void setImidacloprid(String imidacloprid) 
    {
        this.imidacloprid = imidacloprid;
    }

    public String getImidacloprid() 
    {
        return imidacloprid;
    }
    public void setMyclobutanil(String myclobutanil) 
    {
        this.myclobutanil = myclobutanil;
    }

    public String getMyclobutanil() 
    {
        return myclobutanil;
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
    public void setFludioxonil(String fludioxonil) 
    {
        this.fludioxonil = fludioxonil;
    }

    public String getFludioxonil() 
    {
        return fludioxonil;
    }
    public void setDiflubenzuron(String diflubenzuron) 
    {
        this.diflubenzuron = diflubenzuron;
    }

    public String getDiflubenzuron() 
    {
        return diflubenzuron;
    }
    public void setCyromazine(String cyromazine) 
    {
        this.cyromazine = cyromazine;
    }

    public String getCyromazine() 
    {
        return cyromazine;
    }
    public void setThiamethoxam(String thiamethoxam) 
    {
        this.thiamethoxam = thiamethoxam;
    }

    public String getThiamethoxam() 
    {
        return thiamethoxam;
    }
    public void setMancozeb(String mancozeb) 
    {
        this.mancozeb = mancozeb;
    }

    public String getMancozeb() 
    {
        return mancozeb;
    }
    public void setMetalaxylM(String metalaxylM) 
    {
        this.metalaxylM = metalaxylM;
    }

    public String getMetalaxylM() 
    {
        return metalaxylM;
    }
    public void setPyraclostrobin(String pyraclostrobin) 
    {
        this.pyraclostrobin = pyraclostrobin;
    }

    public String getPyraclostrobin() 
    {
        return pyraclostrobin;
    }
    public void setChlorantraniliprole(String chlorantraniliprole) 
    {
        this.chlorantraniliprole = chlorantraniliprole;
    }

    public String getChlorantraniliprole() 
    {
        return chlorantraniliprole;
    }
    public void setAzoxystrobin(String azoxystrobin) 
    {
        this.azoxystrobin = azoxystrobin;
    }

    public String getAzoxystrobin() 
    {
        return azoxystrobin;
    }
    public void setImidaclothiz(String imidaclothiz) 
    {
        this.imidaclothiz = imidaclothiz;
    }

    public String getImidaclothiz() 
    {
        return imidaclothiz;
    }
    public void setFlonicamid(String flonicamid) 
    {
        this.flonicamid = flonicamid;
    }

    public String getFlonicamid() 
    {
        return flonicamid;
    }
    public void setLufenuron(String lufenuron) 
    {
        this.lufenuron = lufenuron;
    }

    public String getLufenuron() 
    {
        return lufenuron;
    }
    public void setPhosalone(String phosalone) 
    {
        this.phosalone = phosalone;
    }

    public String getPhosalone() 
    {
        return phosalone;
    }
    public void setDifenoconazole(String difenoconazole) 
    {
        this.difenoconazole = difenoconazole;
    }

    public String getDifenoconazole() 
    {
        return difenoconazole;
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
            .append("vegNoBanPesDetRecordsId", getVegNoBanPesDetRecordsId())
            .append("pesticideName", getPesticideName())
            .append("bifenthrin", getBifenthrin())
            .append("chlorfluazuron", getChlorfluazuron())
            .append("flucycloxuron", getFlucycloxuron())
            .append("lambdaCyhalothrin", getLambdaCyhalothrin())
            .append("fenvalerate", getFenvalerate())
            .append("deltamethrin", getDeltamethrin())
            .append("triazolone", getTriazolone())
            .append("carbendazim", getCarbendazim())
            .append("metalaxyl", getMetalaxyl())
            .append("profenofos", getProfenofos())
            .append("carbaryl", getCarbaryl())
            .append("imidacloprid", getImidacloprid())
            .append("myclobutanil", getMyclobutanil())
            .append("abamectin", getAbamectin())
            .append("emamectinBenzoate", getEmamectinBenzoate())
            .append("fludioxonil", getFludioxonil())
            .append("diflubenzuron", getDiflubenzuron())
            .append("cyromazine", getCyromazine())
            .append("thiamethoxam", getThiamethoxam())
            .append("mancozeb", getMancozeb())
            .append("metalaxylM", getMetalaxylM())
            .append("pyraclostrobin", getPyraclostrobin())
            .append("chlorantraniliprole", getChlorantraniliprole())
            .append("azoxystrobin", getAzoxystrobin())
            .append("imidaclothiz", getImidaclothiz())
            .append("flonicamid", getFlonicamid())
            .append("lufenuron", getLufenuron())
            .append("phosalone", getPhosalone())
            .append("difenoconazole", getDifenoconazole())
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
