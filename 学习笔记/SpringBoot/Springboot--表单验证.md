## springboot--表单验证

----------
### @Valid --用于表单验证
在实体类的要验证的属性上添加验证的注解，比如
`
@Min(value = 18, message = "未成年少女禁止入内")
	private int age;`
<br/>
然后在调用的方法参数里添加@Vaild
<br/>
``` java
    /**
	 * 添加一个女生
	 * @param cupSize
	 * @param age
	 * @return
	 */
	@GetMapping(value = "/addGirl")
	public Result<girl> Add(@Valid girl g, BindingResult bindingResult) {
		Result result = new Result();
		if(bindingResult.hasErrors()) {
			String mes = bindingResult.getFieldError().getDefaultMessage();
			return ResultUtil.error(1,mes);
		}
		return ResultUtil.success(girlRepository.save(g));
	}
```
bingdingResult就是用来存放valid的信息。
