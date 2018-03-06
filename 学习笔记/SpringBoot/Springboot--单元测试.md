## 单元测试在Springboot中的使用

----------
1 添加pom依赖
```xml
<dependency>  
	<groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-test</artifactId>  
    <scope>test</scope>
</dependency>  
```
<br/>
2 编写测试类，在类上加注解@Runwith,@SpringBootTest，如果是测试接口，还可以加上@AutoConfigureMockMvc来模拟api的http请求
```java
/**
 * 
 * @author xus
 * 2017年10月24日下午8:15:26
 * GirlControllerTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //模拟api请求
public class GirlControllerTest {

	@Autowired
	private MockMvc mvc;
	@Test
	public void girlList() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/girls")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("abc"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```