package com.caiwei.demo.activemq;

import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Consumer
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/18 23:03
 */
@Component
@Log
public class Consumer {

    @JmsListener(destination = "order",containerFactory = MQConfig.QUEUE)
    public void readQueueOrderMsg(String msg) {
        log.info(msg);
    }

    @JmsListener(destination = "mail",containerFactory = MQConfig.QUEUE)
    public void readQueueMailMsg(String msg) {
        log.info(msg);
    }

    @JmsListener(destination = "news",containerFactory = MQConfig.TOPIC)
    public void readTopicNewsMsg(String msg) {
        log.info(msg);
    }

    @JmsListener(destination = "events",containerFactory = MQConfig.TOPIC)
    public void readTopicEventsMsg(String msg) {
        log.info(msg);
    }
}
