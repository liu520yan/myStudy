package com.liuyan.study.mq.controller;

import com.liuyan.study.mq.kafka.KafkaProduer;
import com.liuyan.study.mq.vo.MsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/9/13.
 */
@RestController
public class Msgcontroller {
    @Autowired
    private KafkaProduer kafkaProduer;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void getMsg(@RequestBody Object vo) {
        kafkaProduer.kafkaDemo(vo);
    }
}
