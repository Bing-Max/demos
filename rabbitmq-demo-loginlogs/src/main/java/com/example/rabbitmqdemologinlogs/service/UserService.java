package com.example.rabbitmqdemologinlogs.service;

import com.example.rabbitmqdemologinlogs.dao.UserMapper;
import com.example.rabbitmqdemologinlogs.entity.User;
import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import com.example.rabbitmqdemologinlogs.mq.LogPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    private LogPublisher logPublisher;

    public Boolean login(UserLoginDto dto){
        User user = userMapper.selectByUserNamePassword(dto.getUserName(), dto.getPassword());
        if(null != user){ // 正常登录

            dto.setId(user.getId());
            // 发布消息
            logPublisher.publishMessage(dto);
            //
            return true;
        }
        // 登录失败，直接返回false，不必写入日志
        // 如若有特殊情况，继续做业务的处理
        // ...
        return false;
    }
}
