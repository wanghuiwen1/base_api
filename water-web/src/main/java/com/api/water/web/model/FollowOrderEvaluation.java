package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "follow_order_evaluation")
public class FollowOrderEvaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follow_order")
    private Long followOrder;

    /**
     * 评分
     */
    @Column(name = "evaluation_score")
    private Double evaluationScore;

    /**
     * 评价内容
     */
    @Column(name = "evaluation_content")
    private String evaluationContent;

    /**
     * 评价时间
     */
    @Column(name = "evaluation_time")
    private Date evaluationTime;

    /**
     * 1已处理，２未处理，３无需处理
     */
    private Integer status;

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
     * @return follow_order
     */
    public Long getFollowOrder() {
        return followOrder;
    }

    /**
     * @param followOrder
     */
    public void setFollowOrder(Long followOrder) {
        this.followOrder = followOrder;
    }

    /**
     * 获取评分
     *
     * @return evaluation_score - 评分
     */
    public Double getEvaluationScore() {
        return evaluationScore;
    }

    /**
     * 设置评分
     *
     * @param evaluationScore 评分
     */
    public void setEvaluationScore(Double evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    /**
     * 获取评价内容
     *
     * @return evaluation_content - 评价内容
     */
    public String getEvaluationContent() {
        return evaluationContent;
    }

    /**
     * 设置评价内容
     *
     * @param evaluationContent 评价内容
     */
    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    /**
     * 获取评价时间
     *
     * @return evaluation_time - 评价时间
     */
    public Date getEvaluationTime() {
        return evaluationTime;
    }

    /**
     * 设置评价时间
     *
     * @param evaluationTime 评价时间
     */
    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /**
     * 获取1已处理，２未处理，３无需处理
     *
     * @return status - 1已处理，２未处理，３无需处理
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1已处理，２未处理，３无需处理
     *
     * @param status 1已处理，２未处理，３无需处理
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}