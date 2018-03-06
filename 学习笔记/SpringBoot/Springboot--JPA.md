## jpa在springboot中的使用
#### jpa是一套持久化的规范，spring data jpa是基于这个规范和ORM框架的一套jpa应用框架
----------
 
 * 添加pom依赖
```xml
<dependency>  
    <groupId>mysql</groupId>  
  	<artifactId>mysql-connector-java</artifactId>  
</dependency>
<dependency>  
    <groupId>org.springframework.boot</groupId>  
  	<artifactId>spring-boot-starter-data-jpa</artifactId>  
</dependency>  
```
 * 在application.yml中配置jpa的基本属性，具体的配置可以去找文档
  ` spring:
     　jpa:
        　　hibernate:
            　　　ddl-auto: update
        　　show-sql: true`
<br/>
 * 在实体类中增加@Entity注解，并且在Id属性上增加@Id，#GeneratedValue注解，并且写上无参的构造函数，**这样就可以利用jpa自动生成对应类的数据库表**，ddl-auto一般用update，无表则创建表，有表则更新表
 <br/>
 * 写一个repository继承JpaRepository<>,  这里已经实现了基本的CRUD
 `public interface GirlRepository extends JpaRepository<girl, Integer> {}`
<br/>
 *  在Controller中定义GirlRepository的变量，并且autowired一下就可以操作变量来使用了
 <br/>
 *  以上就是jpa在springboot中的基础使用
 
----------


[还有很多自定义的查询，多表查询，分页查询，jpa都可以](http://www.cnblogs.com/ityouknow/p/5891443.html)

