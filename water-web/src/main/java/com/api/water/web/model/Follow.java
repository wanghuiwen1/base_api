package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Follow implements Serializable {
    //等待开始
    public final static Integer STATUS_WAITING=1;
    //咨询中
    public final static Integer STATUS_FOLLOWING=2;
    //已结束
    public final static Integer STATUS_OVER=3;
    //重新开始
    public final static Integer STATUS_RESTART=4;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private Long whoset;

    @Column(name = "group_chat_id")
    private String groupChatId;

    /**
     * １等待开始２咨询中３已结束４重新开始
     */
    private Integer status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "doctor_id")
    private Long doctorId;

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
     * @return whoset
     */
    public Long getWhoset() {
        return whoset;
    }

    /**
     * @param whoset
     */
    public void setWhoset(Long whoset) {
        this.whoset = whoset;
    }

    /**
     * @return group_chat_id
     */
    public String getGroupChatId() {
        return groupChatId;
    }

    /**
     * @param groupChatId
     */
    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }

    /**
     * 获取１等待开始２咨询中３已结束４重新开始
     *
     * @return status - １等待开始２咨询中３已结束４重新开始
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置１等待开始２咨询中３已结束４重新开始
     *
     * @param status １等待开始２咨询中３已结束４重新开始
     */
    public void setStatus(Integer status) {
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
}