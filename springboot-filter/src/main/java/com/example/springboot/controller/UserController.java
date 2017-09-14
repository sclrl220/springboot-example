package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-14 17:19
 **/
@RestController
public class UserController {

    @GetMapping(value = "/test")
    public String test(){
        return "test....";
    }

}
