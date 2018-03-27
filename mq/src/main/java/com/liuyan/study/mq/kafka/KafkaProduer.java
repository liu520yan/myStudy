package com.liuyan.study.mq.kafka;

import com.alibaba.fastjson.JSONObject;
import com.liuyan.study.mq.vo.MsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by liuyan on 2017/9/13.
 */
@Component
public class KafkaProduer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void kafkaDemo(Object vo) {
        String voString = JSONObject.toJSON(vo).toString();
        kafkaTemplate.sendDefault(voString);
    }
}
