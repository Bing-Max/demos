package com.example.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 默认值 true false false 持久化、非独占、非自动删除
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("message = " + message );
    }


}
