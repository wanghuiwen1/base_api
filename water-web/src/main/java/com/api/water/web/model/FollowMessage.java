package com.api.water.web.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "follow_message")
public class FollowMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follow_id")
    private Long followId;

    private Long sender;

    @Column(name = "create_date")
    private Integer createDate;

    private String message;

    private Boolean type;

    @Column(name = "audio_length")
    private Integer audioLength;

    private Boolean screen;

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
     * @return sender
     */
    public Long getSender() {
        return sender;
    }

    /**
     * @param sender
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }

    /**
     * @return create_date
     */
    public Integer getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return type
     */
    public Boolean getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * @return audio_length
     */
    public Integer getAudioLength() {
        return audioLength;
    }

    /**
     * @param audioLength
     */
    public void setAudioLength(Integer audioLength) {
        this.audioLength = audioLength;
    }

    /**
     * @return screen
     */
    public Boolean getScreen() {
        return screen;
    }

    /**
     * @param screen
     */
    public void setScreen(Boolean screen) {
        this.screen = screen;
    }
}