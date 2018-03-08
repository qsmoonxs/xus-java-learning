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

### Problem
#### 为啥要有适配器去干活：
___
#### 前端控制器怎么进行渲染:




