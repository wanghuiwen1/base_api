package com.api.water.web.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "follow_user")
public class FollowUser implements Serializable {
    @Id
    @Column(name = "follow_id")
    private Long followId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "last_read")
    private Integer lastRead;

    private static final long serialVersionUID = 1L;

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
     * @return last_read
     */
    public Integer getLastRead() {
        return lastRead;
    }

    /**
     * @param lastRead
     */
    public void setLastRead(Integer lastRead) {
        this.lastRead = lastRead;
    }
}