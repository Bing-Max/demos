package com.example.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//设置默认队列
                    exchange = @Exchange(value = "directs",type = "direct"),// 默认类型direct
                    key = {"info","error","warning"}
            )
    })
    public void receive1(String message){
        System.out.println("consumer1: " + message);

    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//设置默认队列
                    exchange = @Exchange(value = "directs",type = "direct"),// 默认类型direct
                    key = {"error",}
            )
    })
    public void receive2(String message) {
        System.out.println("consumer2: " + message);
    }
}
