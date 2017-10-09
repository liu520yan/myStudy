package com.liuyan.study.pay.alipay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by liuyan on 2017/9/29.
 */
public class JSONUtil {

    public static String toJSONString(Object o) {
        return JSONObject.toJSONString(o);
    }
}
