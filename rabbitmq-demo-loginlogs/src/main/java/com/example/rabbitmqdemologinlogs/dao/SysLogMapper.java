package com.example.rabbitmqdemologinlogs.dao;

import com.example.rabbitmqdemologinlogs.entity.SysLog;

public interface SysLogMapper {

    // 根据用户id删除用户记录
    int deleteByPrimaryKey(Integer id);

    // 插入用户实体信息
    int insert(SysLog record);

    // 插入用户实体信息
    int insertSelective(SysLog record);

    // 根据用户id查询用户实体信息
    SysLog selectByPrimaryKey(Integer id);

    // 更新用户实体信息
    int updateByPrimaryKeySelective(SysLog record);

    // 更新用户实体信息
    int updateByPrimaryKey(SysLog record);
}
