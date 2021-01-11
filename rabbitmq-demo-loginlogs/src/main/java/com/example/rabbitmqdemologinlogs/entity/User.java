package com.example.rabbitmqdemologinlogs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String userName;
    private Integer id;
    private String password;
    private Date createTime;
}
