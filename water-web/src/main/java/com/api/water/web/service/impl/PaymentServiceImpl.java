package com.api.water.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.api.common.IPUtil;
import com.api.common.wx.pay.WXConfig;
import com.api.common.wx.pay.WXPay;
import com.api.common.wx.pay.WXPayConstants;
import com.api.common.wx.pay.WXPayUtil;
import com.api.core.response.*;
import com.api.water.web.constant.ConstPayment;
import com.api.water.web.dao.PaymentMapper;
import com.api.water.web.model.Payment;
import com.api.water.web.service.PaymentService;
import com.api.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.api.common.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
@Service
@Transactional
public class PaymentServiceImpl extends AbstractService<Payment> implements PaymentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private PaymentMapper paymentMapper;
    @Value("${wx.pay.prefix}")
    private String prefix;

    @Override
    public Result list(String search, String order, Integer page, Integer size){
        Map<String, Object> params = JSONUtils.json2map(search);
        Map<String, Object> orderParams = JSONUtils.json2map(order);
        for (String key : orderParams.keySet()) {
                if (orderParams.get(key) != null && orderParams.get(key).equals("ascending")) orderParams.put(key, "asc");
                if (orderParams.get(key) != null && orderParams.get(key).equals("descending")) orderParams.put(key, "desc");
            }
        PageHelper.startPage(page, size);
        List<Map<String, Object>> res = paymentMapper.baseList(params, orderParams);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @Override
    public Result createOrder(HttpServletRequest request, Long orderId, Byte orderType, String type, String openid, String formId, WXConfig wxConfig, String typesOfDrug) {
        WXPay wxPay = new WXPay(wxConfig);
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setType(orderType);
        payment.setPayMethod(type);
        payment = paymentMapper.selectOne(payment);

        if (payment == null) return ResultGenerator.genFailResult(ResultMessage.NO_CONTENT);
        if(payment.getStatus().equals(ConstPayment.STATUS_PAY))  return ResultGenerator.genFailResult("订单已经支付");
        //添加formid
        try {
            Map<String, String> param = new HashMap<>();
            param.put("body", "订单详情");
            param.put("out_trade_no", prefix+type+typesOfDrug + payment.getId());
            //微信以分为单位
            param.put("total_fee", String.valueOf(payment.getAmount().multiply(new BigDecimal(100)).intValue()));
            logger.info(String.valueOf(payment.getAmount().multiply(new BigDecimal(100)).intValue()));
            param.put("spbill_create_ip", IPUtil.getIpAddress(request));
            if (openid != null) {
                param.put("openid", openid);
            }
            param.put("notify_url", wxPay.getNotifyUrl());
            if(type.equals("JSAPI")||type.equals("PUBLIC") ){
                param.put("trade_type", "JSAPI");
            }else {
                param.put("trade_type", type);
            }
            param.put("sign", WXPayUtil.generateSignature(param, wxConfig.getKey(), WXPayConstants.SignType.MD5));
            Map<String, String> o = wxPay.unifiedOrder(param);

            logger.info("==================wxpay============================"+ wxConfig.getAppID() + JSON.toJSONString(o));
            Map<String, String> res = new HashMap<>();
            if (o.get("result_code").equals("FAIL")) {
                return ResultGenerator.genResultAndData(ResultEnum.PAYFAILD, res);
            }
            if(type.equals("JSAPI")||type.equals("PUBLIC") ){
                res.put("appId", wxConfig.getAppID());
                res.put("package", "prepay_id=" + o.get("prepay_id"));
                res.put("signType", WXPayConstants.SignType.MD5.name());
                res.put("nonceStr", o.get("nonce_str"));
                res.put("timeStamp", Long.toString(new Date().getTime()));
            }

            if(type.equals("APP")){
                res.put("appid", wxConfig.getAppID());
                res.put("prepayid",o.get("prepay_id"));
                res.put("partnerid",wxConfig.getMchID());
                res.put("package", "Sign=WXPay");
                res.put("noncestr", o.get("nonce_str"));
                res.put("timestamp", Long.toString(new Date().getTime()/1000));
            }

            payment.setFormid(o.get("prepay_id"));
            update(payment);
            String sign = WXPayUtil.generateSignature(res, wxConfig.getKey(), WXPayConstants.SignType.MD5);
            res.put("paySign", sign);
            res.put("formid",o.get("prepay_id"));
            res.put("payId",payment.getId().toString());
            return ResultGenerator.genSuccessResult(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(ResultMessage.FAIL);
        }
    }
}
