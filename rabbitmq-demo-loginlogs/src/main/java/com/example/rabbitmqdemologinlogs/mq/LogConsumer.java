package com.example.rabbitmqdemologinlogs.mq;

import com.example.rabbitmqdemologinlogs.entity.SysLog;
import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import com.example.rabbitmqdemologinlogs.service.SysLogService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    @Autowired
    private SysLogService sysLogService;

    @RabbitListener(bindings =
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "lglogs",type = "topic"),
                    key = "#.login.logs"
            )
    )
    public void consumeLoginMessage(@Payload UserLoginDto dto){
        System.out.println(dto);
        if(null != dto){
            sysLogService.insertRecord(dto);
        }
    }
}
