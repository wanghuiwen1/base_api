package com.api.water.web.constant;

import com.api.common.wx.pay.WXConfig;

public class Constant {
    public final static WXConfig WFR_WX_CONFIG = new WXConfig("wx4c202ab98659addb","1539277401","chiputabutuputaopi2019zaichiyici","1ee1855689740930f4c7b9cf33dd7b29","cert/apiclient_cert.p12","https://wfrkj.com:8080/payment/notify");
    public final static WXConfig WFR_WX_PUBLIC= new WXConfig("wx8343924181c5dadd","1539277401","chiputabutuputaopi2019zaichiyici","1ee1855689740930f4c7b9cf33dd7b29","cert/apiclient_cert.p12","https://wfrkj.com:8080/payment/notify");

    public final static WXConfig WFR_APP_CONFIG = new WXConfig("wx9a70e53168f4ec27","1539277401","chiputabutuputaopi2019zaichiyici","1ee1855689740930f4c7b9cf33dd7b29","cert/apiclient_cert.p12","https://wfrkj.com:8080/payment/notify");

    public static class GENDER{
        public static final Byte GENDER_MALE=1;
        public static final Byte GENDER_FEMALE=2;
    }
}
