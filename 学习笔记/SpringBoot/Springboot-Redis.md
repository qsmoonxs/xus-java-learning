### redis在Springboot中的使用

- 添加pom依赖
``` xml
<dependency>
  			<groupId>org.springframework.boot</groupId>
  			 <artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
```
- 写redisConfig，注入实现redis连接的bean，配置redisTemplates模板，注入了bean就要在application的写好连接信息。spring.redis.host=192.168.88.34 这个配置的是spring默认的redisbean的信息。
``` java
@Bean
	@ConfigurationProperties(prefix = "spring.config.redis")
	JedisConnectionFactory connectionFactory() {
		  JedisConnectionFactory jedis = new JedisConnectionFactory();
	        jedis.setPoolConfig(poolConfig());
		
		return jedis;
    }
  @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("connectionFactory")	JedisConnectionFactory connectionFactory) {
        LOG.info( "ip = " + connectionFactory.getHostName());
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        //template.setHashKeySerializer(template.getKeySerializer());  
        //template.setHashValueSerializer(template.getValueSerializer());
        return template;
    }
```
用的是JedisConnectionFactory这个连接，查看源码知道这个连接的主机叫hostName，那么在application中就要写这个信息，区分springboot默认的bean配置 (被坑了很久)
```xml
 
#redis配置信息
spring.config.redis.hostName=192.168.88.34
spring.config.redis.port=6379
spring.config.redis.password=myredis
spring.config.redis.maxActive=50
spring.config.redis.maxTotal=4
spring.config.redis.maxWait=2
spring.config.redis.maxIdle=6
spring.config.redis.minIdle=6
spring.config.redis.timeout=0
spring.config.redis.testOnBorrow=true
```
- redisTemplates
``` java
@Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("connectionFactory")	JedisConnectionFactory connectionFactory) {
        LOG.info( "ip = " + connectionFactory.getHostName());
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        //template.setHashKeySerializer(template.getKeySerializer());  
        //template.setHashValueSerializer(template.getValueSerializer());
        return template;
    }
@Autowired
	private RedisTemplate<String, Object> redisTemplate;
//根据key和list存入数据库
		redisTemplate.opsForValue().set(redisKey, list);

```