package com.api.water.web.service;
import com.api.common.wx.pay.WXConfig;
import com.api.water.web.model.Payment;
import com.api.core.service.Service;
import com.api.core.response.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
public interface PaymentService extends Service<Payment> {
   Result list(String search, String order, Integer page, Integer size);
   Result createOrder(HttpServletRequest request, Long orderId, Byte orderType, String type, String openid, String formId, WXConfig wxConfig, String typesOfDrug);
}
