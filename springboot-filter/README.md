# springboot-filter
处理跨域请求

1:方式一添加Filter
```

Access-Control-Allow-Headers:Origin, X-Requested-With, Content-Type, Accept,token
Access-Control-Allow-Methods:POST,GET,OPTIONS
Access-Control-Allow-Origin:*
Access-Control-Max-Age:18000

```
2:Spring4.2以后
在类或者方法上注释
@CrossOrigin(origins ="http://172.168.1.120/tpp") 或者 @CrossOrigin(origins ="*")
目前好像只有JDK1.8才能编译通过,1.7编译-Error:(13, 1) java: 对于属性<clinit>, 注释org.springframework.web.bind.annotation.CrossOrigin缺少值

