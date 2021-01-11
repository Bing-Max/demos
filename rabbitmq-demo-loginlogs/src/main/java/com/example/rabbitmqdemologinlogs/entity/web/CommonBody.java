package com.example.rabbitmqdemologinlogs.entity.web;

public class CommonBody {
    Integer code;
    String message;
    Object data;

    public CommonBody(){
        super();
    }

    public CommonBody(Integer code){
        this.code=code;
    }

    public CommonBody(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public CommonBody(Integer code, String message, Object data){
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public CommonBody(StatusCode statusCode){
        this(statusCode.code);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public CommonBody setCode(Integer code) {
        this.code = code;
        return this;
    }

    public CommonBody setCode(StatusCode code) {
        this.code = code.code;
        return this;
    }

    public CommonBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonBody setData(Object data) {
        this.data = data;
        return this;
    }
}
