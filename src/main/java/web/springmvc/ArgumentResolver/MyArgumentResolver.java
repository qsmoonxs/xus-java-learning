/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package web.springmvc.ArgumentResolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.net.URLDecoder;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 13:21
 *
 */
public class MyArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        /** 指定参数如果被应用MyParam注解，则使用该解析器。
        如果直接返回true，则代表将此解析器用于所有参数 **/
        return methodParameter.hasParameterAnnotation(MyParam.class);
    }

    /**
     * 将request中的请求参数解析到当前Controller参数上
     * @param methodParameter 需要被解析的Controller参数，此参数必须首先传给{@link #supportsParameter}并返回true
     * @param modelAndViewContainer 当前request的ModelAndViewContainer
     * @param nativeWebRequest 当前request
     * @param webDataBinderFactory 生成{@link WebDataBinderFactory}实例的工厂
     * @return 解析后的Controller参数
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        /**参考RequestParamMethodArgumentResolver的实现**/
        Object arg = URLDecoder.decode(nativeWebRequest.getParameter(methodParameter.getParameterName()),"UTF-8");
        if(webDataBinderFactory != null) {
            WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, methodParameter.getParameterName());
            arg = binder.convertIfNecessary(arg, methodParameter.getParameterType(), methodParameter);
        }
        return "hello";
    }
}
