package com.example.rabbitmqdemologinlogs.entity.web;

public enum StatusCode {
    SUCCESS(200),
    NULLERR(000),
    INVADERR(300),
    SERVERERR(500),
    ERR(400);

    Integer code;

    StatusCode(){

    }

    StatusCode(int code){
        this.code = code;
    }

}
