package com.example.springboot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-14 17:25
 **/
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PersonController {
    @GetMapping(value = "/test")
    public String test(){
        return "test....";
    }
}
