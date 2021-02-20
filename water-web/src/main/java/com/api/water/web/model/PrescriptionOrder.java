package com.api.water.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "prescription_order")
public class PrescriptionOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_status")
    private Byte orderStatus;

    private BigDecimal amount;

    @Column(name = "transport_num")
    private String transportNum;

    @Column(name = "verify_id")
    private Long verifyId;

    @Column(name = "verify_time")
    private Date verifyTime;

    @Column(name = "verify_remark")
    private String verifyRemark;

    private Byte status;

    private String address;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "invoice_id")
    private Long invoiceId;

    /**
     * 医生签名
     */
    private String signature;

    @Column(name = "order_type")
    private Byte orderType;

    /**
     * 发货人
     */
    private Long sender;

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
     * @return order_status
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return transport_num
     */
    public String getTransportNum() {
        return transportNum;
    }

    /**
     * @param transportNum
     */
    public void setTransportNum(String transportNum) {
        this.transportNum = transportNum;
    }

    /**
     * @return verify_id
     */
    public Long getVerifyId() {
        return verifyId;
    }

    /**
     * @param verifyId
     */
    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    /**
     * @return verify_time
     */
    public Date getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     */
    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * @return verify_remark
     */
    public String getVerifyRemark() {
        return verifyRemark;
    }

    /**
     * @param verifyRemark
     */
    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return doctor_id
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * @return invoice_id
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * @param invoiceId
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * 获取医生签名
     *
     * @return signature - 医生签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置医生签名
     *
     * @param signature 医生签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return order_type
     */
    public Byte getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     */
    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取发货人
     *
     * @return sender - 发货人
     */
    public Long getSender() {
        return sender;
    }

    /**
     * 设置发货人
     *
     * @param sender 发货人
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }
}