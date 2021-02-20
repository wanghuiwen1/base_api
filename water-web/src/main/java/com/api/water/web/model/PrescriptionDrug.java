package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "prescription_drug")
public class PrescriptionDrug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prescription_id")
    private Long prescriptionId;

    @Column(name = "drug_id")
    private Long drugId;

    private String frequency;

    private String way;

    private String means;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private String morning;

    private String noon;

    private String night;

    private String sleep;

    @Column(name = "morning_type")
    private String morningType;

    @Column(name = "noon_type")
    private String noonType;

    @Column(name = "night_type")
    private String nightType;

    @Column(name = "sleep_type")
    private String sleepType;

    private Byte prohibit;

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
     * @return prescription_id
     */
    public Long getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * @param prescriptionId
     */
    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    /**
     * @return drug_id
     */
    public Long getDrugId() {
        return drugId;
    }

    /**
     * @param drugId
     */
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    /**
     * @return frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return way
     */
    public String getWay() {
        return way;
    }

    /**
     * @param way
     */
    public void setWay(String way) {
        this.way = way;
    }

    /**
     * @return means
     */
    public String getMeans() {
        return means;
    }

    /**
     * @param means
     */
    public void setMeans(String means) {
        this.means = means;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return morning
     */
    public String getMorning() {
        return morning;
    }

    /**
     * @param morning
     */
    public void setMorning(String morning) {
        this.morning = morning;
    }

    /**
     * @return noon
     */
    public String getNoon() {
        return noon;
    }

    /**
     * @param noon
     */
    public void setNoon(String noon) {
        this.noon = noon;
    }

    /**
     * @return night
     */
    public String getNight() {
        return night;
    }

    /**
     * @param night
     */
    public void setNight(String night) {
        this.night = night;
    }

    /**
     * @return sleep
     */
    public String getSleep() {
        return sleep;
    }

    /**
     * @param sleep
     */
    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    /**
     * @return morning_type
     */
    public String getMorningType() {
        return morningType;
    }

    /**
     * @param morningType
     */
    public void setMorningType(String morningType) {
        this.morningType = morningType;
    }

    /**
     * @return noon_type
     */
    public String getNoonType() {
        return noonType;
    }

    /**
     * @param noonType
     */
    public void setNoonType(String noonType) {
        this.noonType = noonType;
    }

    /**
     * @return night_type
     */
    public String getNightType() {
        return nightType;
    }

    /**
     * @param nightType
     */
    public void setNightType(String nightType) {
        this.nightType = nightType;
    }

    /**
     * @return sleep_type
     */
    public String getSleepType() {
        return sleepType;
    }

    /**
     * @param sleepType
     */
    public void setSleepType(String sleepType) {
        this.sleepType = sleepType;
    }

    /**
     * @return prohibit
     */
    public Byte getProhibit() {
        return prohibit;
    }

    /**
     * @param prohibit
     */
    public void setProhibit(Byte prohibit) {
        this.prohibit = prohibit;
    }
}