package com.api.water.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "doctor_info")
public class DoctorInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Byte sex;

    private Date birthday;

    private Integer age;

    private String province;

    private String location;

    private String head;

    private String intro;

    @Column(name = "ks_id")
    private Long ksId;

    @Column(name = "user_id")
    private Long userId;

    private Integer starttime;

    private String title;

    private String goodat;

    private String level;

    @Column(name = "create_date")
    private Date createDate;

    private Boolean status;

    @Column(name = "his_id")
    private String hisId;

    private String mobile;

    private Byte type;

    /**
     * 0无权开慢病处方　１有权开慢病处方
     */
    private Byte chronic;

    /**
     * 工号
     */
    @Column(name = "work_id")
    private String workId;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sex
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return head
     */
    public String getHead() {
        return head;
    }

    /**
     * @param head
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * @return ks_id
     */
    public Long getKsId() {
        return ksId;
    }

    /**
     * @param ksId
     */
    public void setKsId(Long ksId) {
        this.ksId = ksId;
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
     * @return starttime
     */
    public Integer getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return goodat
     */
    public String getGoodat() {
        return goodat;
    }

    /**
     * @param goodat
     */
    public void setGoodat(String goodat) {
        this.goodat = goodat;
    }

    /**
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
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
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return his_id
     */
    public String getHisId() {
        return hisId;
    }

    /**
     * @param hisId
     */
    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * 获取0无权开慢病处方　１有权开慢病处方
     *
     * @return chronic - 0无权开慢病处方　１有权开慢病处方
     */
    public Byte getChronic() {
        return chronic;
    }

    /**
     * 设置0无权开慢病处方　１有权开慢病处方
     *
     * @param chronic 0无权开慢病处方　１有权开慢病处方
     */
    public void setChronic(Byte chronic) {
        this.chronic = chronic;
    }

    /**
     * 获取工号
     *
     * @return work_id - 工号
     */
    public String getWorkId() {
        return workId;
    }

    /**
     * 设置工号
     *
     * @param workId 工号
     */
    public void setWorkId(String workId) {
        this.workId = workId;
    }
}