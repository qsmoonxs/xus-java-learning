### 需要学习的知识点
springmvc 框架原理：前段控制器 处理器控制器 处理器适配器 视图解析器

常用注解

参数绑定：简单类型 pojo 集合类型

自定义参数绑定

数据回显

上传图片

json交互

拦截器

restful
___

### springmvc是什么：
springmvc是spring框架一个模块，一个基于mvc的web框架

### mvc在bs下的应用：
设计模式
model：pojo action service  dao。。。（明天画一下mvc图）

### springmvc框架原理：
- 控制器对应 前段控制器：DispatchServlet
- 处理器映射器 HandlerMapping 返回一个执行链 HandlerExecutionChain
- 模型对应 Handler处理器（平常叫controller）
- 处理器适配器 HandleAdapter 去执行Handler
- 视图解析器 View Resolver
- 视图：jsp freemarker excel pds
1. 发起请求到前段控制器（DispatcherServlet）
2. 前段控制器请求HandlerMapping查找Handler：根据xml配置 注解进行查找
3. 处理器映射器HandlerMapping向前段控制器返回Handler
4. 前端控制器调用HandlerAdpater去执行Handler
5. 处理器适配器执行Handler
6. Handler执行完给适配器返回ModelAndView
7. 处理器适配器向前端控制器返回ModelAndView
8. 前端控制器请求视图解析器进行视图解析：根据逻辑视图名解析成真正的视图
9. 视图解析器向前端控制器返回View
10. 前端控制器进行视图渲染：模型数据填充到request域（渲染是什么干的，request域是啥）
11. 前端控制器向用户响应结果

### DispatcherServlet分析：
1. 调用doDispatch
2. 前端控制器调用处理器映射器 

```java
mappedHandler = this.getHandler(processedRequest);
HandlerExecutionChain getHandler()

```

3. 调用处理器适配器执行Handler得到mv
```java
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
```

4. 视图渲染，将model的数据填充到request域
```java
/** 得到视图 **/
view = this.resolveViewName(mv.getViewName(), mv.getModelInternal(), locale, request);
/** 渲染方法 **/
 view.render(mv.getModelInternal(), request, response);
 /**填充数据 **/
 exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request)
```

### 参数绑定转化：
1. HttpMessageConverter 由 RequestMappingHandlerAdapter （注解的那个适配器）使用 将请求转为对象 或者 将对象转换为相应信息
   1. Boolean canRead
   2. Boolean canWrirte
   3. List<MediaType> getSupportedMediaTypes
   4. T read
   5. void Write
   
   在适配器中可以注册转化器 默认的是下面四个
   StringHttpMessageConverter
   ByteHttpMessageConverter
   SourceHttpMessageConverter
   AllEncompassingFormHttpMessageConverter
   
   如何使用
   如果标注@requestBody 那么就是查找把请求转换为对象的那个转换器
   如果标注@responsebodu 那么就是查找把对象转换为相应信息的那个转化器
   spring首先根据请求头或者响应头的Accept属性选择匹配的HttpMessageConverter 然后根据参数类型或者泛型类型过滤
   
2. DataBinder
   在ConversionService中进行数据类型转化 数据格式化 将servletquest中的消息填充到入参对象中 conversionservice可以在xml中注册 应该也有默认的
### Problem
#### 为啥要有适配器去干活：
___
#### 前端控制器怎么进行渲染:
___

#### 打包后怎么没在tomcat的webapp下找到他
___

#### servlet的url-pattern
___
#### queryItemsAnnotation为什么不用写成queryItemsAnnotation.action 前端控制器的urlpattern是*.action，实际访问的时候一定要加上.action
___

#### springmvc的参数解析器

___
####  将请求信息转化绑定到入参中？
1：HttpMessageConverterV
2：DataBind中ConversionService？
3：@requestparam又是咋回事
