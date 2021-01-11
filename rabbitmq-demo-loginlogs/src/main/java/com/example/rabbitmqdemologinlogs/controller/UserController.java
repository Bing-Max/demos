package com.example.rabbitmqdemologinlogs.controller;

import com.example.rabbitmqdemologinlogs.entity.UserLoginDto;
import com.example.rabbitmqdemologinlogs.entity.web.CommonBody;
import com.example.rabbitmqdemologinlogs.entity.web.StatusCode;
import com.example.rabbitmqdemologinlogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String prefix = "user";

    @Autowired
    private UserService userService;

    @RequestMapping(value = prefix+"/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonBody login(@RequestBody @Validated UserLoginDto dto, //映射成对象
                            BindingResult result){
        CommonBody commonBody = new CommonBody();
        if(result.hasErrors()){
            return commonBody.setCode(StatusCode.INVADERR).setMessage("非法输入");
        }

        try{
            Boolean res = userService.login(dto);
            if(res){
                // 登录成功
                return commonBody.setCode(StatusCode.SUCCESS).setMessage("登录成功").setData(dto);
            } else{
                commonBody.setCode(StatusCode.ERR).setMessage("登录失败，用户名、密码错误");
            }
        }catch (Exception e){
                commonBody.setCode(StatusCode.SERVERERR).setMessage(e.getMessage());
        }

        return commonBody;
    }

}
