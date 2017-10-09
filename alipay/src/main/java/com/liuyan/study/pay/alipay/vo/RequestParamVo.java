package com.liuyan.study.pay.alipay.vo;

import lombok.Builder;
import lombok.Data;

/**
 * Created by liuyan on 2017/9/29.
 */
@Data
public class RequestParamVo {
    String out_trade_no; //是	64	商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复	20150320010101001
    String product_code; //是	64销售产品码，与支付宝签约的产品码名称。注：目前仅支持FAST_INSTANT_TRADE_PAY FAST_INSTANT_TRADE_PAY

    String total_amount;//是	11订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]88.88
    String subject; // 订单标题 Iphone6 16G

    //////////////以下非必须############################
    String body; //订单描述
    String goods_detail; // 否 订单包含的商品列表信息，Json格式，详见商品明细说明
    String passback_params;
    String extend_params;
    String goods_type;
    String timeout_express;
    String enable_pay_channels;
    String disable_pay_channels;
    String auth_token;
    String qr_pay_mode;
    String qrcode_width;

}
