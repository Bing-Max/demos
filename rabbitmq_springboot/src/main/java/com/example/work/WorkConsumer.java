package com.example.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("consumer1:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String mesaage){
        System.out.println("consumer2:" + mesaage);
    }
}
