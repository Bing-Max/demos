package com.example.rabbitmqdemologinlogs.dao;

import com.example.rabbitmqdemologinlogs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /*删除用户信息通过id*/
    int deleteByPrimaryKey(Integer id);

    /*插入用户信息*/
    int insert(User record);

    /*插入用户实体信息*/
    int insertSelective(User record);

    /*通过id查找用户*/
    User selectByPrimaryKey(Integer id);

    /*更新用户实体信息*/
    int updateByPrimaryKeySelective(User record);

    /**/
    int updateByPrimaryKey(User record);

    /*根据用户名和密码查询*/
    User selectByUserNamePassword(@Param("userName")  String username, @Param("password") String password);
}
