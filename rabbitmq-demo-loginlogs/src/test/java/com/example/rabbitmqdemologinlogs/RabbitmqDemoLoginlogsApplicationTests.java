package com.example.rabbitmqdemologinlogs;

import com.example.rabbitmqdemologinlogs.dao.UserMapper;
import com.example.rabbitmqdemologinlogs.entity.User;
import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitmqDemoLoginlogsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    UserMapper userMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void testConectionAndMapper(){
        User user = userMapper.selectByPrimaryKey(0);
        System.out.println(user);
    }

    @Test
    void testRabbitmq(){
        rabbitTemplate.convertAndSend("lglogs", "login.logs", "login message");
    }

    @Test
    void testRabbitqm(){
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.convertAndSend("user.login.logs");

//        rabbitTemplate.convertAndSend("user.login.logs", new UserLoginDto("user01","123456",0),(message)->{
//
//            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, UserLoginDto.class);
//            return message;
//        });
    }
}
