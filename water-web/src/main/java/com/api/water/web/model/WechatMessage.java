package com.api.water.web.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_message")
public class WechatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接收方
     */
    @Column(name = "to_user_name")
    private String toUserName;

    /**
     * 发送方
     */
    @Column(name = "from_user_name")
    private String fromUserName;

    /**
     * 消息创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 消息类型
     */
    @Column(name = "msg_type")
    private String msgType;

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 消息id
     */
    @Column(name = "msg_id")
    private String msgId;

    /**
     * 图片链接
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 图片消息媒体id
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 语音消息格式
     */
    private String format;

    /**
     * 语音识别结果
     */
    private String recognition;

    /**
     * 视频消息缩略图的媒体id
     */
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

    /**
     * 地理位置纬度
     */
    @Column(name = "location_x")
    private String locationX;

    /**
     * 地理位置经度
     */
    @Column(name = "location_y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    private String scale;

    /**
     * 地理位置信息
     */
    private String label;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息描述
     */
    private String description;

    /**
     * 消息链接
     */
    private String url;

    /**
     * 事件类型
     */
    private String event;

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    @Column(name = "event_key")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    /**
     * 地理位置纬度
     */
    private String latitude;

    /**
     * 地理位置经度
     */
    private String longitude;

    /**
     * 地理位置精度
     */
    @Column(name = "precision_local")
    private String precisionLocal;

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
     * 获取接收方
     *
     * @return to_user_name - 接收方
     */
    public String getToUserName() {
        return toUserName;
    }

    /**
     * 设置接收方
     *
     * @param toUserName 接收方
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    /**
     * 获取发送方
     *
     * @return from_user_name - 发送方
     */
    public String getFromUserName() {
        return fromUserName;
    }

    /**
     * 设置发送方
     *
     * @param fromUserName 发送方
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * 获取消息创建时间
     *
     * @return create_time - 消息创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置消息创建时间
     *
     * @param createTime 消息创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取消息类型
     *
     * @return msg_type - 消息类型
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置消息类型
     *
     * @param msgType 消息类型
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取文本消息内容
     *
     * @return content - 文本消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文本消息内容
     *
     * @param content 文本消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取消息id
     *
     * @return msg_id - 消息id
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设置消息id
     *
     * @param msgId 消息id
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取图片链接
     *
     * @return pic_url - 图片链接
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置图片链接
     *
     * @param picUrl 图片链接
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取图片消息媒体id
     *
     * @return media_id - 图片消息媒体id
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置图片消息媒体id
     *
     * @param mediaId 图片消息媒体id
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * 获取语音消息格式
     *
     * @return format - 语音消息格式
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置语音消息格式
     *
     * @param format 语音消息格式
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 获取语音识别结果
     *
     * @return recognition - 语音识别结果
     */
    public String getRecognition() {
        return recognition;
    }

    /**
     * 设置语音识别结果
     *
     * @param recognition 语音识别结果
     */
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    /**
     * 获取视频消息缩略图的媒体id
     *
     * @return thumb_media_id - 视频消息缩略图的媒体id
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设置视频消息缩略图的媒体id
     *
     * @param thumbMediaId 视频消息缩略图的媒体id
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * 获取地理位置纬度
     *
     * @return location_x - 地理位置纬度
     */
    public String getLocationX() {
        return locationX;
    }

    /**
     * 设置地理位置纬度
     *
     * @param locationX 地理位置纬度
     */
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取地理位置经度
     *
     * @return location_y - 地理位置经度
     */
    public String getLocationY() {
        return locationY;
    }

    /**
     * 设置地理位置经度
     *
     * @param locationY 地理位置经度
     */
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取地图缩放大小
     *
     * @return scale - 地图缩放大小
     */
    public String getScale() {
        return scale;
    }

    /**
     * 设置地图缩放大小
     *
     * @param scale 地图缩放大小
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * 获取地理位置信息
     *
     * @return label - 地理位置信息
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置地理位置信息
     *
     * @param label 地理位置信息
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取消息标题
     *
     * @return title - 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置消息标题
     *
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取消息描述
     *
     * @return description - 消息描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置消息描述
     *
     * @param description 消息描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取消息链接
     *
     * @return url - 消息链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置消息链接
     *
     * @param url 消息链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取事件类型
     *
     * @return event - 事件类型
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置事件类型
     *
     * @param event 事件类型
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * 获取事件KEY值，qrscene_为前缀，后面为二维码的参数值
     *
     * @return event_key - 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设置事件KEY值，qrscene_为前缀，后面为二维码的参数值
     *
     * @param eventKey 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 获取二维码的ticket，可用来换取二维码图片
     *
     * @return ticket - 二维码的ticket，可用来换取二维码图片
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 设置二维码的ticket，可用来换取二维码图片
     *
     * @param ticket 二维码的ticket，可用来换取二维码图片
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * 获取地理位置纬度
     *
     * @return latitude - 地理位置纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置地理位置纬度
     *
     * @param latitude 地理位置纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取地理位置经度
     *
     * @return longitude - 地理位置经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置地理位置经度
     *
     * @param longitude 地理位置经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取地理位置精度
     *
     * @return precision_local - 地理位置精度
     */
    public String getPrecisionLocal() {
        return precisionLocal;
    }

    /**
     * 设置地理位置精度
     *
     * @param precisionLocal 地理位置精度
     */
    public void setPrecisionLocal(String precisionLocal) {
        this.precisionLocal = precisionLocal;
    }
}