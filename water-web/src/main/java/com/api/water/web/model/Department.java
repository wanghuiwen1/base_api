package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Department implements Serializable {
    @Id
    private Long ksid;

    @Column(name = "hospital_id")
    private Long hospitalId;

    private String name;

    private String phone;

    private String info;

    @Column(name = "is_manage")
    private Boolean isManage;

    private Byte status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "is_follow")
    private Boolean isFollow;

    @Column(name = "ks_his_id")
    private String ksHisId;

    @Column(name = "ks_pell_code")
    private String ksPellCode;

    @Column(name = "ks_code")
    private String ksCode;

    @Column(name = "is_warning")
    private Boolean isWarning;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "ward_number")
    private Integer wardNumber;

    private static final long serialVersionUID = 1L;

    /**
     * @return ksid
     */
    public Long getKsid() {
        return ksid;
    }

    /**
     * @param ksid
     */
    public void setKsid(Long ksid) {
        this.ksid = ksid;
    }

    /**
     * @return hospital_id
     */
    public Long getHospitalId() {
        return hospitalId;
    }

    /**
     * @param hospitalId
     */
    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return is_manage
     */
    public Boolean getIsManage() {
        return isManage;
    }

    /**
     * @param isManage
     */
    public void setIsManage(Boolean isManage) {
        this.isManage = isManage;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return is_follow
     */
    public Boolean getIsFollow() {
        return isFollow;
    }

    /**
     * @param isFollow
     */
    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    /**
     * @return ks_his_id
     */
    public String getKsHisId() {
        return ksHisId;
    }

    /**
     * @param ksHisId
     */
    public void setKsHisId(String ksHisId) {
        this.ksHisId = ksHisId;
    }

    /**
     * @return ks_pell_code
     */
    public String getKsPellCode() {
        return ksPellCode;
    }

    /**
     * @param ksPellCode
     */
    public void setKsPellCode(String ksPellCode) {
        this.ksPellCode = ksPellCode;
    }

    /**
     * @return ks_code
     */
    public String getKsCode() {
        return ksCode;
    }

    /**
     * @param ksCode
     */
    public void setKsCode(String ksCode) {
        this.ksCode = ksCode;
    }

    /**
     * @return is_warning
     */
    public Boolean getIsWarning() {
        return isWarning;
    }

    /**
     * @param isWarning
     */
    public void setIsWarning(Boolean isWarning) {
        this.isWarning = isWarning;
    }

    /**
     * @return ward_name
     */
    public String getWardName() {
        return wardName;
    }

    /**
     * @param wardName
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * @return ward_number
     */
    public Integer getWardNumber() {
        return wardNumber;
    }

    /**
     * @param wardNumber
     */
    public void setWardNumber(Integer wardNumber) {
        this.wardNumber = wardNumber;
    }
}