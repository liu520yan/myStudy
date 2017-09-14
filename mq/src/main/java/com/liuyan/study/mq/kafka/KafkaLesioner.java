package com.liuyan.study.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by liuyan on 2017/9/13.
 */
@Slf4j
@Component
public class KafkaLesioner {

    @KafkaListener(topics = {"s1-veh.main.remaind"})
    public void processMessage(String content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        log.info("时间{} :", formatter.format(ldt));
        log.info("数据{} :", content);
    }
}
