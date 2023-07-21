package com.shl.consumer;

import com.shl.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmConsumer {

    //    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME, containerFactory = "consumer1RabbitListenerContainerFactory")
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveMsg(Message message) {
        String msg = new String(message.getBody());
        log.info("接收到队列 confirm.queue 消息:{}", msg);
    }
}
