# springboot-mybatis
springboot整合mybatis



### 1:集成mybatis自动生成插件 mybatis-generator

pom.xml配置:
```xml
<!--mybatis反向生成类-->
<dependency>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-core</artifactId>
	<version>1.3.2</version>
</dependency>

<plugins>
	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		<version>1.5.4.RELEASE</version>
		<executions>
			<execution>
				<goals>
				<goal>repackage</goal>
				</goals>
			</execution>
		</executions>
    </plugin>

<!--mybatis 逆向工程插件-->
	<plugin>
		<groupId>org.mybatis.generator</groupId>
		<artifactId>mybatis-generator-maven-plugin</artifactId>
		<version>1.3.5</version>
		<configuration>
			<!--指定生成规则文件地址-->
			<configurationFile>src/main/resources/mybatis-plugs/generatorConfig.xml</configurationFile>
			<verbose>true</verbose>
			<overwrite>true</overwrite>
		</configuration>
	</plugin>
</plugins>
```

详情见:\src\main\resources\mybatis-plugs\*
+ generatorConfig.xml 自动生成domain,dao,xml配置

+ mybatisGeneratorinit.properties 配置自动生成插件资源配置

+ 在启动类中配置扫描mapper // 或者在mapper类上注解@Mapper
```java
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("org.spring.springboot.dao")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}

```

![ddmpper](http://owbc4f77a.bkt.clouddn.com/ddmpper.png)



### 2:集成mybatis分页插件 PageHelper

+ 通过JavaConfig的方式配置PageHelper
```java
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
```
+ 使用

在方法调用之前调用PageHelper.startPage(0, 10);会自动在sql上添加limt分页信息
```java
PageHelper.startPage(0, 10);
List<InputItem> inputItems = inputItemMapper.selectByExample(new InputItemExample());
```

### 3:redis使用
+ 添加redis资源配置
redis.properties
```properties
# Redis集群服务器地址(添加集群配置)
redis.cluster.nodes=192.168.0.116:6180,192.168.0.116:6181,192.168.0.116:6182,192.168.0.116:6183,192.168.0.116:6184,192.168.0.116:6185
```

+ 通过JavaConfig的方式配置Redis
```java
@Configuration
@PropertySource(value = "classpath:redis/redis.properties")
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    // 创建Redis集群
    @Bean
    public JedisCluster JedisClusterFactory() {
        LOG.info("JedisCluster创建！！");
        LOG.info("redis地址：" + host + ":" + port);

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis); // 拆分节点组合

        String[] clusterNodes = nodes.split(",");
        for (String clusterNode : clusterNodes) {
            String[] hostAndPort = clusterNode.split(":");
            String host = hostAndPort[0];
            int port = Integer.valueOf(hostAndPort[1]);
            jedisClusterNodes.add(new HostAndPort(host, port));
        }
        return new JedisCluster(jedisClusterNodes, jedisPoolConfig);
    }
}
```

+ 使用
```java
public List<InputItem> pageInputItems(Integer pageNum, Integer pageSize) {
        String items = jedisCluster.get("items123");
        if (StringUtils.isEmpty(items)) {
            PageHelper.startPage(0, 10);
            List<InputItem> inputItems = inputItemMapper.selectByExample(new InputItemExample());
            jedisCluster.set("items123", JSON.toJSONString(inputItems));
            return inputItems;
        }

        return JSON.parseObject(items, new TypeReference<List<InputItem>>() {
        });
}
```


### log4j日志配置

+ log4j.properties 日志输入配置
关键:<br/>>
追踪mybatis执行过程[这里是输出mybatis执行SQL语句的关键]
log4j.logger.org.spring.springboot.dao=DEBUG

+ 在application.properties中指定使用的日志文件
```
配置多环境日志输出格式
logging.config=classpath:log4j.properties
配置包下日志输出格式,可在日志配置文件中直接引用
logging.level.=DEBUG
```

![mybatisProject](http://owbc4f77a.bkt.clouddn.com/mybatisProject.png)

END