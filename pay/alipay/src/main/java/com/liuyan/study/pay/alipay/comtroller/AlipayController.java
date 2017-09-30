package com.liuyan.study.pay.alipay.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.liuyan.study.pay.alipay.utils.JSONUtil;
import com.liuyan.study.pay.alipay.utils.ZxingUtils;
import com.liuyan.study.pay.alipay.vo.RequestParamVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by liuyan on 2017/9/29.
 */
@Controller
public class AlipayController {

    @Value("${alipay.RSA.publickey}")
    String ALIPAY_PUBLIC_KEY;//支付宝公钥
    @Value("${alipay.RSA.privatekey}")
    String APP_PRIVATE_KEY; //开发者私钥，由开发者自己生成

    @Value("${alipay.RSA2.publickey}")
    String ALIPAY_PUBLIC_KEY2;//支付宝公钥
    @Value("${alipay.RSA2.privatekey}")
    String APP_PRIVATE_KEY2; //开发者私钥，由开发者自己生成
    @Value("${signType}")
    String signType;
    @Value("${charset}")
    String charset;

    @Value("${alipay.appid}")
    String APP_ID;
    @Value("${alipay.gateway}")
    String URL;

    /**
     * 生成支付宝收款二维码，返回给前端页面。
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, path = "/alipay/qc")
    public void useAlipay(HttpServletResponse response) throws Exception {

        AlipayClient alipayClient = getClient(URL, APP_ID, signType);

        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();//创建API对应的request
        JSONObject json = new JSONObject();
        json.put("out_trade_no", "10202992");
        json.put("total_amount", 0.01);
        json.put("subject", "花花");
        alipayRequest.setBizContent(json.toString());
        String form = "";


        AlipayTradePrecreateResponse
                alipayTradePrecreateResponse = alipayClient.execute(alipayRequest); //调用SDK生成表单

        ZxingUtils.getQRCodeImge(alipayTradePrecreateResponse.getQrCode(), 200, 200, response);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/alipay/html")
    public void useAlipayHtml(HttpServletResponse response) throws Exception {
        AlipayClient alipayClient = getClient(URL, APP_ID, signType);
        //设置请求参数
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        JSONObject json = new JSONObject();
        json.put("out_trade_no", "10202992");
        json.put("total_amount", 0.01);
        json.put("subject", "花花");
        alipayRequest.setBizContent(json.toString());
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html;charset=" + charset);
        response.getWriter().write(result);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    private AlipayClient getClient(String URL, String APP_ID, String signType) throws Exception {
        switch (signType) {
            case "RSA2":
                return new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY2, "json", charset, ALIPAY_PUBLIC_KEY2, signType);
            case "RSA":
                return new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", charset, ALIPAY_PUBLIC_KEY, signType);
            default:
                return new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY2, "json", charset, ALIPAY_PUBLIC_KEY2, signType);
        }
    }
}



