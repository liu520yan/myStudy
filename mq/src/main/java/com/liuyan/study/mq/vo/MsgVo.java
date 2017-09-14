package com.liuyan.study.mq.vo;

import com.liuyan.study.mq.pojo.Msg;
import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Date;

/**
 * Created by liuyan on 2017/9/13.
 */
@Data
public class MsgVo {
    Integer id;
    String name;
}
