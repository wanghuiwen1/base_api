package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Hospital implements Serializable {
    @Id
    private Long hospitalid;

    /**
     * 医院名称
     */
    @Column(name = "hospital_name")
    private String hospitalName;

    /**
     * 省
     */
    @Column(name = "hospital_province")
    private String hospitalProvince;

    /**
     * 区
     */
    @Column(name = "hospital_location")
    private String hospitalLocation;

    /**
     * 医院类型
     */
    @Column(name = "hospital_type")
    private Byte hospitalType;

    /**
     * 级别
     */
    @Column(name = "hospital_level")
    private Byte hospitalLevel;

    /**
     * 添加时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 状态
     */
    private Boolean status;

    @Column(name = "is_default")
    private Boolean isDefault;

    private Integer phosid;

    private Boolean overstep;

    @Column(name = "is_managebs")
    private Boolean isManagebs;

    @Column(name = "is_allow")
    private Boolean isAllow;

    private String logo;

    private Float yjcqsmall;

    private Float yjcqbig;

    private Float yjchsmall;

    private Float yjchbig;

    private Float yjnormal;

    private static final long serialVersionUID = 1L;

    /**
     * @return hospitalid
     */
    public Long getHospitalid() {
        return hospitalid;
    }

    /**
     * @param hospitalid
     */
    public void setHospitalid(Long hospitalid) {
        this.hospitalid = hospitalid;
    }

    /**
     * 获取医院名称
     *
     * @return hospital_name - 医院名称
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * 设置医院名称
     *
     * @param hospitalName 医院名称
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * 获取省
     *
     * @return hospital_province - 省
     */
    public String getHospitalProvince() {
        return hospitalProvince;
    }

    /**
     * 设置省
     *
     * @param hospitalProvince 省
     */
    public void setHospitalProvince(String hospitalProvince) {
        this.hospitalProvince = hospitalProvince;
    }

    /**
     * 获取区
     *
     * @return hospital_location - 区
     */
    public String getHospitalLocation() {
        return hospitalLocation;
    }

    /**
     * 设置区
     *
     * @param hospitalLocation 区
     */
    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    /**
     * 获取医院类型
     *
     * @return hospital_type - 医院类型
     */
    public Byte getHospitalType() {
        return hospitalType;
    }

    /**
     * 设置医院类型
     *
     * @param hospitalType 医院类型
     */
    public void setHospitalType(Byte hospitalType) {
        this.hospitalType = hospitalType;
    }

    /**
     * 获取级别
     *
     * @return hospital_level - 级别
     */
    public Byte getHospitalLevel() {
        return hospitalLevel;
    }

    /**
     * 设置级别
     *
     * @param hospitalLevel 级别
     */
    public void setHospitalLevel(Byte hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    /**
     * 获取添加时间
     *
     * @return create_date - 添加时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置添加时间
     *
     * @param createDate 添加时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return is_default
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return phosid
     */
    public Integer getPhosid() {
        return phosid;
    }

    /**
     * @param phosid
     */
    public void setPhosid(Integer phosid) {
        this.phosid = phosid;
    }

    /**
     * @return overstep
     */
    public Boolean getOverstep() {
        return overstep;
    }

    /**
     * @param overstep
     */
    public void setOverstep(Boolean overstep) {
        this.overstep = overstep;
    }

    /**
     * @return is_managebs
     */
    public Boolean getIsManagebs() {
        return isManagebs;
    }

    /**
     * @param isManagebs
     */
    public void setIsManagebs(Boolean isManagebs) {
        this.isManagebs = isManagebs;
    }

    /**
     * @return is_allow
     */
    public Boolean getIsAllow() {
        return isAllow;
    }

    /**
     * @param isAllow
     */
    public void setIsAllow(Boolean isAllow) {
        this.isAllow = isAllow;
    }

    /**
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return yjcqsmall
     */
    public Float getYjcqsmall() {
        return yjcqsmall;
    }

    /**
     * @param yjcqsmall
     */
    public void setYjcqsmall(Float yjcqsmall) {
        this.yjcqsmall = yjcqsmall;
    }

    /**
     * @return yjcqbig
     */
    public Float getYjcqbig() {
        return yjcqbig;
    }

    /**
     * @param yjcqbig
     */
    public void setYjcqbig(Float yjcqbig) {
        this.yjcqbig = yjcqbig;
    }

    /**
     * @return yjchsmall
     */
    public Float getYjchsmall() {
        return yjchsmall;
    }

    /**
     * @param yjchsmall
     */
    public void setYjchsmall(Float yjchsmall) {
        this.yjchsmall = yjchsmall;
    }

    /**
     * @return yjchbig
     */
    public Float getYjchbig() {
        return yjchbig;
    }

    /**
     * @param yjchbig
     */
    public void setYjchbig(Float yjchbig) {
        this.yjchbig = yjchbig;
    }

    /**
     * @return yjnormal
     */
    public Float getYjnormal() {
        return yjnormal;
    }

    /**
     * @param yjnormal
     */
    public void setYjnormal(Float yjnormal) {
        this.yjnormal = yjnormal;
    }
}