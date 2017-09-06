package com.liuyan.study.zuul.security;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by liuyan on 2017/9/6.
 */
public class SignatureGenerator {
    public SignatureGenerator() {
    }

    public static String generate(String urlResourcePart, Map<String, String> params, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<Map.Entry<String, String>> parameters = new LinkedList(params.entrySet());
        Collections.sort(parameters, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return ((String)o1.getKey()).compareTo((String)o2.getKey());
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(urlResourcePart).append("_");
        Iterator i$ = parameters.iterator();

        while(i$.hasNext()) {
            Map.Entry<String, String> param = (Map.Entry)i$.next();
            sb.append((String)param.getKey()).append("=").append((String)param.getValue()).append("_");
        }

        sb.append(secretKey);
        String baseString = URLEncoder.encode(sb.toString(), "UTF-8");
        return MD5Util.md5(baseString);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> params = new HashMap();
        params.put("vin", "1");
        params.put("appkey", "5995424178");
        String urlResourcePart = "admin/coordinate";
        String sign = generate(urlResourcePart, params, "rqndb8zsojt980kdzj85utt7idefydxw");
        System.out.println(sign);
    }
}
