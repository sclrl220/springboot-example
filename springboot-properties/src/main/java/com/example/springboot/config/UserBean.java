package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-14 11:08
 **/
@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource("classpath:config/person.properties")
public class UserBean {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
