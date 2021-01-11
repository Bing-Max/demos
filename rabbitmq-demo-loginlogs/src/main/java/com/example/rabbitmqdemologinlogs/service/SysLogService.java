package com.example.rabbitmqdemologinlogs.service;

import com.example.rabbitmqdemologinlogs.dao.SysLogMapper;
import com.example.rabbitmqdemologinlogs.entity.SysLog;
import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public int insertRecord(UserLoginDto dto){
        // 登录记录存库
        SysLog sysLog = new SysLog();
        try {
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(dto.getId());
            sysLog.setData(objectMapper.writeValueAsString(dto));
            sysLog.setMemo("用户登录成功记录信息");
            sysLog.setModule("用户登录模块");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return sysLogMapper.insert(sysLog);
    }
}
