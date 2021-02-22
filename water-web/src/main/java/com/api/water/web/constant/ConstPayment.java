package com.api.water.web.constant;

public class ConstPayment {

    //会诊
    public final static Byte ORDER_TYPE_HUI=1;
    //咨询
    public final static  Byte ORDER_TYPE_ZI = 2;
    //提现
    public final static  Byte ORDER_TYPE_TI = 3;
    //处方
    public static final Byte ORDER_TYPE_CHU = 4;
    //退款
    public final static  Byte ORDER_TYPE_TUI = 5;


    //等待支付
    public final static Byte STATUS_WAIT_PAY=0;
    //以支付
    public final static Byte STATUS_PAY=1;
    //申请退款
    public final static Byte STATUS_REFUND=2;
    //退款成功
    public final static Byte STATUS_REFUND_SUCCESS=3;


    //处方订单备注
    public final static String REMARK_CHU="处方订单";

    public final static String REMARK_TUI="退款订单";

    public ConstPayment() {
    }

    //平台账号
    public final static Long ADMIN_ACCOUNT=-1l;


}
