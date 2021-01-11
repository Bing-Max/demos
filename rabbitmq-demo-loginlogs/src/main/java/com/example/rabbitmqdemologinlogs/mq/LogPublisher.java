package com.example.rabbitmqdemologinlogs.mq;

import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String logExKey="user.login.logs";

    public Boolean publishMessage(UserLoginDto dto){
        rabbitTemplate.convertAndSend(logExKey,dto, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, UserLoginDto.class);//设置消息头部指定类型
            return message;
        });

        return true;
    }

}
