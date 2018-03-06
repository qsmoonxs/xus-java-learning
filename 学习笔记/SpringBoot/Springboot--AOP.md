###  AOP在springboot的使用
- AOP面向切面编程咯，比如记录请求信息，记录返回的响应信息，记录日志等

----------

 1 添加pom依赖
  
``` xml
<dependency>  
	<groupId>org.springframework.boot</groupId>  
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>  
```
 2 编写切面类，加上@Aspect注解和@Component，以便让spring扫描到
 a. @Pointcut：切入点，可以写上哪些类方法需要执行这个切面
``` java
@Pointcut("execution(* com.xus.controller..*(..))")//execution切入的路径(在controller包下的类的方法执行之前先执行这个)
	public void log() {
		
	}
```
b. @AfterReturing
c. @Before
<br/>
具体更多的注解去找资料 常用的应该就这几个 具体还需要在做例子才知道