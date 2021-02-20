package com.api.water.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

public class Drug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "drug_name")
    private String drugName;

    @Column(name = "drug_kind")
    private Byte drugKind;

    private Byte type;

    @Column(name = "drug_code")
    private String drugCode;

    private Byte pretype;

    private Byte hctype;

    private String norms;

    private String dosage;

    private String taboo;

    private String dnotice;

    private String scfactory;

    @Column(name = "drug_price")
    private BigDecimal drugPrice;

    private Boolean issue;

    @Column(name = "create_date")
    private Integer createDate;

    @Column(name = "com_name")
    private String comName;

    private String image;

    @Column(name = "third_id")
    private Long thirdId;

    private String unit;

    /**
     * 药品名称首字母
     */
    @Column(name = "drug_name_initials")
    private String drugNameInitials;

    /**
     * 通用名称首字母
     */
    @Column(name = "com_name_initials")
    private String comNameInitials;

    /**
     * 药品总量
     */
    private Long total;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return drug_name
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * @param drugName
     */
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    /**
     * @return drug_kind
     */
    public Byte getDrugKind() {
        return drugKind;
    }

    /**
     * @param drugKind
     */
    public void setDrugKind(Byte drugKind) {
        this.drugKind = drugKind;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return drug_code
     */
    public String getDrugCode() {
        return drugCode;
    }

    /**
     * @param drugCode
     */
    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    /**
     * @return pretype
     */
    public Byte getPretype() {
        return pretype;
    }

    /**
     * @param pretype
     */
    public void setPretype(Byte pretype) {
        this.pretype = pretype;
    }

    /**
     * @return hctype
     */
    public Byte getHctype() {
        return hctype;
    }

    /**
     * @param hctype
     */
    public void setHctype(Byte hctype) {
        this.hctype = hctype;
    }

    /**
     * @return norms
     */
    public String getNorms() {
        return norms;
    }

    /**
     * @param norms
     */
    public void setNorms(String norms) {
        this.norms = norms;
    }

    /**
     * @return dosage
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * @param dosage
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * @return taboo
     */
    public String getTaboo() {
        return taboo;
    }

    /**
     * @param taboo
     */
    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    /**
     * @return dnotice
     */
    public String getDnotice() {
        return dnotice;
    }

    /**
     * @param dnotice
     */
    public void setDnotice(String dnotice) {
        this.dnotice = dnotice;
    }

    /**
     * @return scfactory
     */
    public String getScfactory() {
        return scfactory;
    }

    /**
     * @param scfactory
     */
    public void setScfactory(String scfactory) {
        this.scfactory = scfactory;
    }

    /**
     * @return drug_price
     */
    public BigDecimal getDrugPrice() {
        return drugPrice;
    }

    /**
     * @param drugPrice
     */
    public void setDrugPrice(BigDecimal drugPrice) {
        this.drugPrice = drugPrice;
    }

    /**
     * @return issue
     */
    public Boolean getIssue() {
        return issue;
    }

    /**
     * @param issue
     */
    public void setIssue(Boolean issue) {
        this.issue = issue;
    }

    /**
     * @return create_date
     */
    public Integer getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    /**
     * @return com_name
     */
    public String getComName() {
        return comName;
    }

    /**
     * @param comName
     */
    public void setComName(String comName) {
        this.comName = comName;
    }

    /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return third_id
     */
    public Long getThirdId() {
        return thirdId;
    }

    /**
     * @param thirdId
     */
    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取药品名称首字母
     *
     * @return drug_name_initials - 药品名称首字母
     */
    public String getDrugNameInitials() {
        return drugNameInitials;
    }

    /**
     * 设置药品名称首字母
     *
     * @param drugNameInitials 药品名称首字母
     */
    public void setDrugNameInitials(String drugNameInitials) {
        this.drugNameInitials = drugNameInitials;
    }

    /**
     * 获取通用名称首字母
     *
     * @return com_name_initials - 通用名称首字母
     */
    public String getComNameInitials() {
        return comNameInitials;
    }

    /**
     * 设置通用名称首字母
     *
     * @param comNameInitials 通用名称首字母
     */
    public void setComNameInitials(String comNameInitials) {
        this.comNameInitials = comNameInitials;
    }

    /**
     * 获取药品总量
     *
     * @return total - 药品总量
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置药品总量
     *
     * @param total 药品总量
     */
    public void setTotal(Long total) {
        this.total = total;
    }
}