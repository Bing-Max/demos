package com.example.rabbitmqdemologinlogs.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户登录实体信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDto implements Serializable {
    @NotNull
    private String userName;
    @NotNull
    private String password;

    private Integer id;
}
