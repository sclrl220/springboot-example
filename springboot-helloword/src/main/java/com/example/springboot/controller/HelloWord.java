package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-13 17:48
 **/
@RestController
public class HelloWord {

    @RequestMapping("/")
    public String test(){
        return "Hello Word!";
    }

}
