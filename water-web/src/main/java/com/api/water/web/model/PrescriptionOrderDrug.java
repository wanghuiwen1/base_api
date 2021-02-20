package com.api.water.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "prescription_order_drug")
public class PrescriptionOrderDrug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prescript_drug_id")
    private Long prescriptDrugId;

    @Column(name = "drug_num")
    private Integer drugNum;

    private BigDecimal price;

    private String remark;

    private Integer day;

    @Column(name = "order_id")
    private Long orderId;

    /**
     * 药量结束时间
     */
    @Column(name = "till_day")
    private Date tillDay;

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
     * @return prescript_drug_id
     */
    public Long getPrescriptDrugId() {
        return prescriptDrugId;
    }

    /**
     * @param prescriptDrugId
     */
    public void setPrescriptDrugId(Long prescriptDrugId) {
        this.prescriptDrugId = prescriptDrugId;
    }

    /**
     * @return drug_num
     */
    public Integer getDrugNum() {
        return drugNum;
    }

    /**
     * @param drugNum
     */
    public void setDrugNum(Integer drugNum) {
        this.drugNum = drugNum;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取药量结束时间
     *
     * @return till_day - 药量结束时间
     */
    public Date getTillDay() {
        return tillDay;
    }

    /**
     * 设置药量结束时间
     *
     * @param tillDay 药量结束时间
     */
    public void setTillDay(Date tillDay) {
        this.tillDay = tillDay;
    }
}