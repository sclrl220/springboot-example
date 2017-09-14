package com.example.springboot.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lantuLrl
 * @description 解决跨域请求
 * @create Create By:2017-09-14 16:57
 **/
//@Configuration
public class CorsFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean initFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyCROSSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("myCROSSFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyCROSSFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
            resp.setHeader("Access-Control-Max-Age", "18000");
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {

        }
    }
}
