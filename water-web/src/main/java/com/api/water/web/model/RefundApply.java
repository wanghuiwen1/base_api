package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "refund_apply")
public class RefundApply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 退款订单
     */
    @Column(name = "follow_order_id")
    private Long followOrderId;

    /**
     * 申请人
     */
    private Long applicant;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 处理人
     */
    private Long handler;

    /**
     * 处理状态　１未处理，２已退款，３拒绝退款
     */
    private Integer status;

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
     * 获取退款订单
     *
     * @return follow_order_id - 退款订单
     */
    public Long getFollowOrderId() {
        return followOrderId;
    }

    /**
     * 设置退款订单
     *
     * @param followOrderId 退款订单
     */
    public void setFollowOrderId(Long followOrderId) {
        this.followOrderId = followOrderId;
    }

    /**
     * 获取申请人
     *
     * @return applicant - 申请人
     */
    public Long getApplicant() {
        return applicant;
    }

    /**
     * 设置申请人
     *
     * @param applicant 申请人
     */
    public void setApplicant(Long applicant) {
        this.applicant = applicant;
    }

    /**
     * 获取退款原因
     *
     * @return reason - 退款原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置退款原因
     *
     * @param reason 退款原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取处理人
     *
     * @return handler - 处理人
     */
    public Long getHandler() {
        return handler;
    }

    /**
     * 设置处理人
     *
     * @param handler 处理人
     */
    public void setHandler(Long handler) {
        this.handler = handler;
    }

    /**
     * 获取处理状态　１未处理，２已退款，３拒绝退款
     *
     * @return status - 处理状态　１未处理，２已退款，３拒绝退款
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置处理状态　１未处理，２已退款，３拒绝退款
     *
     * @param status 处理状态　１未处理，２已退款，３拒绝退款
     */
    public void setStatus(Integer status) {
        this.status = status;
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