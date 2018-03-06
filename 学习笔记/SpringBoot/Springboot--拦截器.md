### 拦截器在Springboot中的使用
1. 实现HandlerInterceptor接口
``` java
/**
	 * 检查是否登录
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("拦截器进行拦截：");
		//response.sendRedirect("/err.html");
		return true; //true，放行
	}
```
2. 继承WebMvcConfigurerAdapter接口，配置拦截器拦截规则
``` java
@Configuration
public class LoginInterceptConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercept()).addPathPatterns("/**").excludePathPatterns("/login");
	}
}

```