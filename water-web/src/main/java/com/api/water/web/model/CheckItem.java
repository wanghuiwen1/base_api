package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "check_item")
public class CheckItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 类型１检查报告２功能评估３危险度评估
     */
    private Integer type;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图片地址
     *
     * @return url - 图片地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片地址
     *
     * @param url 图片地址
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * 获取类型１检查报告２功能评估３危险度评估
     *
     * @return type - 类型１检查报告２功能评估３危险度评估
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型１检查报告２功能评估３危险度评估
     *
     * @param type 类型１检查报告２功能评估３危险度评估
     */
    public void setType(Integer type) {
        this.type = type;
    }
}