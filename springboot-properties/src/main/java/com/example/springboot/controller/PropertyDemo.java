package com.example.springboot.controller;

import com.example.springboot.config.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-13 18:45
 **/
@RestController
public class PropertyDemo {

    @Autowired
    private UserBean user;


    @Value("${name}")
    private String name;

    @RequestMapping("/getName")
    public String testProperty(){
        return "你好->" + name;
    }

    @RequestMapping("/user")
    public String testProperties(){
        return user.getName();
    }

}
