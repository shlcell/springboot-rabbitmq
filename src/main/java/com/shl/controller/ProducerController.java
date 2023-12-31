package com.shl.controller;

import com.shl.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirm")
@Slf4j
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMessage/{message}")
    public void sendMessage(@PathVariable String message) {
        log.info("发送消息内容:{}", message);

        //指定消息 id 为 1：正常发送
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, ConfirmConfig.CONFIRM_ROUTING_KEY, message + ConfirmConfig.CONFIRM_ROUTING_KEY, correlationData1);

        //指定消息 id 为 2：routingKey错误
        CorrelationData correlationData2 = new CorrelationData("2");
        String routingKey = "key2";
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData2);

        //指定消息 id 为 3:交换机名称错误
        CorrelationData correlationData3 = new CorrelationData("3");
        rabbitTemplate.convertAndSend("a", ConfirmConfig.CONFIRM_ROUTING_KEY, message + ConfirmConfig.CONFIRM_ROUTING_KEY, correlationData3);
    }
}
