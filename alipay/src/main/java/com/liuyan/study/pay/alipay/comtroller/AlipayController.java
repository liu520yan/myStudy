package com.liuyan.study.pay.alipay.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.liuyan.study.pay.alipay.utils.ZxingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


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
     * 网页支付
     * //todo 不知道为什么一直有问题啊， 没有调试成功。
     *
     * @param httpResponse
     */
    @RequestMapping(method = RequestMethod.GET, path = "/alipay/web")
    public void webAlipay(HttpServletResponse httpResponse) throws Exception {

        AlipayClient alipayClient = getClient(URL, APP_ID, signType);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        JSONObject json = new JSONObject();
        json.put("out_trade_no", "10202992");
        json.put("total_amount", 0.01);
        json.put("subject", "花花");
        alipayRequest.setBizContent(json.toString());
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + charset);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

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

    /**
     * app调用接口，唤起支付宝app
     *
     * @param response
     * @throws Exception
     */
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



