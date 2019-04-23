package com.caiwei.demo.activemq;

import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Producer
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/18 22:21
 */
@Log
@RestController
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    @Qualifier("order")
    private Queue order;

    @Autowired
    @Qualifier("mail")
    private Queue mail;

    @Autowired
    @Qualifier("news")
    private Topic news;

    @Autowired
    @Qualifier("events")
    private Topic events;

    @GetMapping("/sendQueueOrderMsg")
    public void sendQueueOrderMsg(String msg) {
        this.jmsTemplate.convertAndSend(order, msg);
        log.info("order消息发送成功");
    }

    @GetMapping("/sendQueueMailMsg")
    public void sendQueueMailMsg(String msg) {
        this.jmsTemplate.convertAndSend(mail, msg);
        log.info("mail消息发送成功");
    }


    @GetMapping("/sendTopicNewsMsg")
    public void sendTopicNewsMsg(String msg) {
        this.jmsTemplate.convertAndSend(news, msg);
        log.info("news消息发送成功");
    }

    @GetMapping("/sendTopicEventsMsg")
    public void sendTopicEventsMsg(String msg) {
        //消息发送的时候，最好第一个用类型，别用名字，否则queue和topic分不太清楚
        this.jmsTemplate.convertAndSend(events,msg);
        log.info("news消息发送成功");
    }


    @GetMapping("/sendMap")
    public void send() {
        Map<String,String> map = new HashMap<>();
        map.put("name", "王鹏达");
        map.put("age", "32");
        map.put("love", "chishi");
        jmsTemplate.convertAndSend("map_msg", map);
        log.info("map消息发送成功");
    }

    @GetMapping("/sendJson")
    public void sendJson() {
        List<String> list = new ArrayList<>();
        list.add("王鹏达");
        list.add("王越");
        list.add("wangshaofeng");
        list.add("tianhong");
        this.jmsTemplate.convertAndSend("msg", JSON.toJSONString(list));
        log.info("json消息发送成功");

    }
}
