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
public class SysLog {
    private Integer id;
    private Integer userId;
    private String module;
    private String data;
    private String memo;
    private Date createTime;
}
