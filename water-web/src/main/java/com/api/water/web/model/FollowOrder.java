package com.api.water.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "follow_order")
public class FollowOrder implements Serializable {
    //已支付
    public final static Integer STATUS_PAY=1;
    //未支付
    public final static Integer STATUS_WAITPAY=2;
    //已退款
    public final static Integer STATUS_REFUND=3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follow_id")
    private Long followId;

    private BigDecimal amount;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "create_date")
    private Date createDate;

    /**
     * 1已支付２未支付３已退款４已结束
     */
    private Boolean status;

    private Byte isaccept;

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
     * @return follow_id
     */
    public Long getFollowId() {
        return followId;
    }

    /**
     * @param followId
     */
    public void setFollowId(Long followId) {
        this.followId = followId;
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
     * 获取1已支付２未支付３已退款４已结束
     *
     * @return status - 1已支付２未支付３已退款４已结束
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置1已支付２未支付３已退款４已结束
     *
     * @param status 1已支付２未支付３已退款４已结束
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return isaccept
     */
    public Byte getIsaccept() {
        return isaccept;
    }

    /**
     * @param isaccept
     */
    public void setIsaccept(Byte isaccept) {
        this.isaccept = isaccept;
    }
}