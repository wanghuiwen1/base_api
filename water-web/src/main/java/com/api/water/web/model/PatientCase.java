package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "patient_case")
public class PatientCase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    /**
     * 就诊日期
     */
    @Column(name = "date_of_visit")
    private Date dateOfVisit;

    /**
     * 主诉
     */
    @Column(name = "main_suit")
    private String mainSuit;

    /**
     * 现病史
     */
    @Column(name = "history_of_present_illness")
    private String historyOfPresentIllness;

    /**
     * 既往史
     */
    @Column(name = "history_of_past_illness")
    private String historyOfPastIllness;

    /**
     * 体格检查
     */
    @Column(name = "physical_examination")
    private String physicalExamination;

    /**
     * 诊断结果
     */
    @Column(name = "diagnosis_result")
    private String diagnosisResult;

    /**
     * 门诊处方
     */
    private String prescription;

    /**
     * 注意事项
     */
    @Column(name = "dos_and_donts")
    private String dosAndDonts;

    /**
     * 医生id
     */
    @Column(name = "doctor_id")
    private Long doctorId;

    /**
     * 签名地址
     */
    @Column(name = "autograph_url")
    private String autographUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取就诊日期
     *
     * @return date_of_visit - 就诊日期
     */
    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    /**
     * 设置就诊日期
     *
     * @param dateOfVisit 就诊日期
     */
    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    /**
     * 获取主诉
     *
     * @return main_suit - 主诉
     */
    public String getMainSuit() {
        return mainSuit;
    }

    /**
     * 设置主诉
     *
     * @param mainSuit 主诉
     */
    public void setMainSuit(String mainSuit) {
        this.mainSuit = mainSuit;
    }

    /**
     * 获取现病史
     *
     * @return history_of_present_illness - 现病史
     */
    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    /**
     * 设置现病史
     *
     * @param historyOfPresentIllness 现病史
     */
    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    /**
     * 获取既往史
     *
     * @return history_of_past_illness - 既往史
     */
    public String getHistoryOfPastIllness() {
        return historyOfPastIllness;
    }

    /**
     * 设置既往史
     *
     * @param historyOfPastIllness 既往史
     */
    public void setHistoryOfPastIllness(String historyOfPastIllness) {
        this.historyOfPastIllness = historyOfPastIllness;
    }

    /**
     * 获取体格检查
     *
     * @return physical_examination - 体格检查
     */
    public String getPhysicalExamination() {
        return physicalExamination;
    }

    /**
     * 设置体格检查
     *
     * @param physicalExamination 体格检查
     */
    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    /**
     * 获取诊断结果
     *
     * @return diagnosis_result - 诊断结果
     */
    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    /**
     * 设置诊断结果
     *
     * @param diagnosisResult 诊断结果
     */
    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    /**
     * 获取门诊处方
     *
     * @return prescription - 门诊处方
     */
    public String getPrescription() {
        return prescription;
    }

    /**
     * 设置门诊处方
     *
     * @param prescription 门诊处方
     */
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    /**
     * 获取注意事项
     *
     * @return dos_and_donts - 注意事项
     */
    public String getDosAndDonts() {
        return dosAndDonts;
    }

    /**
     * 设置注意事项
     *
     * @param dosAndDonts 注意事项
     */
    public void setDosAndDonts(String dosAndDonts) {
        this.dosAndDonts = dosAndDonts;
    }

    /**
     * 获取医生id
     *
     * @return doctor_id - 医生id
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * 设置医生id
     *
     * @param doctorId 医生id
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * 获取签名地址
     *
     * @return autograph_url - 签名地址
     */
    public String getAutographUrl() {
        return autographUrl;
    }

    /**
     * 设置签名地址
     *
     * @param autographUrl 签名地址
     */
    public void setAutographUrl(String autographUrl) {
        this.autographUrl = autographUrl;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}