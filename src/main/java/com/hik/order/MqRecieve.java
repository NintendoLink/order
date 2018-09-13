package com.hik.order;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收MQ消息
 */
@Component
public class MqRecieve {
    @RabbitListener(queues = "myQueue")
    public void process(String message){
        System.out.println("this is message:"+message);
    }
}
