# springboot-helloword
springboot快速上手

### 快速搭建第一个SpringBoot项目
1. 访问http://start.spring.io/
2. 通过 IntelliJ IDEA File>New>Project>Spring Initializr 快速创建
这样一个简单的springboot项目就创建好了


### pom.xml

1:引入父模块
```XML
<parent>
    <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.5.6.RELEASE</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>
```
2:加入web模块

```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 测试
添加一个Controller包创建HelloWord.java
```
@RestController
public class HelloWord {

    @RequestMapping("/")
    public String test(){
        return "Hello Word!";
    }

}

@RestController 的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
```

启动主程序，打开浏览器访问http://localhost:8080/，就可以看到效果了

### 开发环境的调试
```
<!--热启动,方便修改代码后可以实时生效-->
 <dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

<build>
<!--maven插件,方便springboot项目打成jar 通过java -jar的方式执行-->
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
            </configuration>
        </plugin>
</plugins>
</build>
```