package com.hik.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class MqSenderTest {

    private AmqpTemplate amqpTemplate;

//    AmqpTemplate amqpTemplate=new RabbitTemplate();
    @Test
    public void send(){

        amqpTemplate.convertAndSend("myQueue","now"+new Date());
    }
}
