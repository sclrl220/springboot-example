package com.example.springboot.config;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author lantuLrl
 * @description mybatis-pageHelper分页配置
 * @create Create By:2017-09-11 16:30
 **/
@Configuration
public class PageHelperConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(PageHelperConfiguration.class);

    @Bean
    public PageHelper pageHelper() {
        LOG.info("------Register MyBatis PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
