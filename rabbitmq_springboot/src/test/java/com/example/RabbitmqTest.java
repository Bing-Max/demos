package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitmqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //"hello world"
    @Test
    public void helloworld(){
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    @Test
    public void workque(){
        //公平调度，除非额外配置
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", ("["+"message" + i+"]"));
        }
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs", "", "[fanout message!!!]");
    }

    @Test
    public void testDirect(){
        String key = "error";
        rabbitTemplate.convertAndSend("directs", key, "direct message!!![" + key + "]");
    }

    @Test
    public void testTopics(){
        String key = "hello.xxx.com";
        rabbitTemplate.convertAndSend("topics", key, "direct message!!![" + key + "]");
    }

}
