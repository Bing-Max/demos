package com.example.topics;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue,
                exchange = @Exchange(value = "topics", type = "topic"),
                key={"user.*","user.save"}
        )
    })
    public void receive1(String message){
        System.out.println("consumer1: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key={"user.#","product.*","#.com"}
            )
    })
    public void receive2(String message){
        System.out.println("consumer2: " + message);
    }
}
