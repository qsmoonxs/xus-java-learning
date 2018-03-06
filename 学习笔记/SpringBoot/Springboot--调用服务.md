### 在Springboot中调用服务

----------
- 添加pom依赖
```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```
- 启动类上添加@EnableFeignClients注解
- 配置文件中配置eureka环境
``` xml
#eureka
eureka.client.service-url.defaultZone=http://192.168.88.126:30001/eureka
eureka.instance.lease-renewal-interval-in-seconds=10
eureka.instance.lease-expiration-duration-in-seconds=30
eureka.instance.prefer-ip-address=true
eureka.instance.instance-id: ${spring.cloud.client.ipAddress}:${server.port}
eureka.instance.metadata-map.cluster=AUTH-RESOURCE
```
- 编写接口，然后可以注入调用
```java
@FeignClient(value = "AUTH-USER")
public interface AuthUserFeignClient {
	@RequestMapping(value = "/userProduct/rdsUsedByUser")
	public Map<String, Object> getUserByRdsId(Integer rdsId);
}
```