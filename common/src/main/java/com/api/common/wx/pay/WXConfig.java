package com.api.common.wx.pay;

import com.api.common.wx.pay.WXPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class WXConfig extends WXPayConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private  String appID;

    private String mchID;

    private String key;

    private String secret;

    private byte[] certData;

    private String certPath;

    private String notifyUrl;

    private IWXPayDomain wXPayDomain;

    public WXConfig(String appID, String mchID, String key, String secret, String certPath, String notifyUrl) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
        this.secret = secret;
        this.certPath = certPath;
        this.notifyUrl = notifyUrl;
        this.setCertPath(certPath);
    }

    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public IWXPayDomain getWXPayDomain(){
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setMchID(String mchID) {
        this.mchID = mchID;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            InputStream certStream = resourceLoader.getResource(certPath).getInputStream();
            int b = -1;
            while ((b = certStream.read()) != -1) {
                outStream.write(b);
            }
            this.certData = outStream.toByteArray();
            outStream.close();
            certStream.close();
            this.certPath = certPath;
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
}
