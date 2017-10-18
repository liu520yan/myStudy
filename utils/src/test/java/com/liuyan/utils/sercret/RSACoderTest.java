package com.liuyan.utils.sercret;

import static org.junit.Assert.*;

import com.liuyan.utils.secret.RSACoder;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by teeyoung on 17/8/29.
 */
public class RSACoderTest {

    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.out.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

        System.out.println(encodedData);
        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);

        String outputStr = new String(decodedData);
        System.out.println("加密前: " + inputStr + "\n" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

    }

    @Test
    public void testSign() throws Exception {
        System.out.println("私钥加密——公钥解密");
        String inputStr = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDc4NzEyOTUsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUkVBRCIsIldSSVRFIl0sImp0aSI6ImNkMTYzODQyLTFhYzItNDVlNC04YWU0LTk5M2YzODg4ZWM5NSIsImNsaWVudF9pZCI6ImFjbWUiLCJzY29wZSI6WyJvcGVuaWQiXX0";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        System.out.println(encodedData);

        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.out.println("加密前: " + inputStr + "\n" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.out.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.out.println("签名:\n" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.out.println("状态:\n" + status);
        assertTrue(status);

    }


}