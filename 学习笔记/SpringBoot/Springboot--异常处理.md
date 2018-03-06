## 异常处理在Spingrboot中的应用

----------
- 自定义异常类继承**RuntimeException**
<br/>
- 抛出异常 捕获异常
```java
/**
 * 获取年龄抛出异常
 * @param id
 * @throws Exception
 */
public void getAge(int id) throws Exception{
	girl g = girlRepository.findOne(id);
	int age = g.getAge();
	if(age < 10) {
		throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
	} else if (age > 10 && age < 16) {
		throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
	}
}
```
- 编写异常处理类，在类上加注解@ControllerAdvice，在处理的方法上加@ExceptionHandler注解，传入异常对象
``` java
@ControllerAdvice
public class ExceptionHandle {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handle(Exception e) {
		if(e instanceof GirlException) {
			GirlException girlException = (GirlException)e;
			return ResultUtil.error(girlException.getCode(), girlException.getMessage());
		} else {
			logger.error("[系统异常] {}",e);
			return ResultUtil.error(-1,"未知错误");
		}
	}
}
```