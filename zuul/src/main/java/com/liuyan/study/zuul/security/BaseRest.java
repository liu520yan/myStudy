package com.liuyan.study.zuul.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyan on 2017/9/6.
 */
public class BaseRest {
    @Value("${appkey}")
    protected String appKey;
    @Value("${secretKey}")
    protected String secretKey;

    @Autowired
    protected RestTemplate restTemplate;
//    @Autowired
//    protected AsyncRestTemplate asyncRestTemplate;

    private static Logger logger = LoggerFactory.getLogger(BaseRest.class);

    protected String generateSign(String uri, Map<String, String> params) {
        String sign = null;
        if (params == null)
            params = new HashMap<>();
        params.put("appkey", appKey);
        try {
            sign = SignatureGenerator.generate(uri, params, secretKey);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return sign;
    }
}
