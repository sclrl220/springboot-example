# springboot-properties
我们在开发Spring Boot应用时，通常同一套程序会被应用和安装到几个不同的环境，比如：开发、测试、生产等。
其中每个环境的数据库地址、服务器端口等等配置都会不同，如果在为不同环境打包时都要频繁修改配置文件的话，
那必将是个非常繁琐且容易发生错误的事。

### 多环境开发使用多资源文件阐述
在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识，比如：

   application-dev.properties：开发环境
   application-test.properties：测试环境
   application-prod.properties：生产环境

至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值。

如：spring.profiles.active=dev就会加载application-dev.properties配置文件内容


### 项目测试
主要文件的为UTF_8编码
获取通过手动配置
```
banner.charset=UTF-8
server.tomcat.uri-encoding=UTF-8
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true
spring.messages.encoding=UTF-8
```
application-dev.properties：开发环境
```
#Tomcat端口
server.port=8081

name=张三

http://localhost:8081/getName 查看效果
```
application-prod.properties：生产环境
```
#Tomcat端口
server.port=8082

name=李四

http://localhost:8081/getName 查看效果
```

### 资源文件通过注入javabean的方式进行操作
1:在resource>config>下创建person.properties
```
person.name=张珊
person.password=12345678
```

2:com.exaple.springboot>config>UserBean
```
@Configuration
@ConfigurationProperties(prefix = "person") // 指定对应资源文件中的前缀
@PropertySource("classpath:config/person.properties") // 指明资源文件的路径
public class UserBean {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    ...get set此处省略
}

```

