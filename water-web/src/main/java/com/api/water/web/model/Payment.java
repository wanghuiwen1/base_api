package com.api.water.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payer;

    private BigDecimal amount;

    private Long payee;

    private String remark;

    @Column(name = "order_Id")
    private Long orderId;

    @Column(name = "create_date")
    private Date createDate;

    private Byte type;

    private Byte status;

    @Column(name = "complete_date")
    private Date completeDate;

    @Column(name = "formId")
    private String formid;

    @Column(name = "pay_method")
    private String payMethod;

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
     * @return payer
     */
    public Long getPayer() {
        return payer;
    }

    /**
     * @param payer
     */
    public void setPayer(Long payer) {
        this.payer = payer;
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
     * @return payee
     */
    public Long getPayee() {
        return payee;
    }

    /**
     * @param payee
     */
    public void setPayee(Long payee) {
        this.payee = payee;
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
     * @return order_Id
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
     * @return complete_date
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     * @param completeDate
     */
    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    /**
     * @return formId
     */
    public String getFormid() {
        return formid;
    }

    /**
     * @param formid
     */
    public void setFormid(String formid) {
        this.formid = formid;
    }

    /**
     * @return pay_method
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @param payMethod
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}