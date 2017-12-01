package com.liuyan.study.aop;

import java.util.regex.Pattern;

/**
 * Created by liuyan on 2017/11/30.
 */
public class UserAgentTools {
    /**
     * 识别设备类型
     *
     * @param userAgent 设备标识
     * @return 设备类型
     */
    public static Integer recognize(String userAgent) {
        if (Pattern.compile("(Windows Phone|Android|iPhone|iPod)").matcher(userAgent).find()) {
            return UserAgentTypeEnum.PHONE.getCode();
        }
        if (Pattern.compile("(iPad)").matcher(userAgent).find()) {
            return UserAgentTypeEnum.TABLET.getCode();
        }
        return UserAgentTypeEnum.PC.getCode();
    }

}
