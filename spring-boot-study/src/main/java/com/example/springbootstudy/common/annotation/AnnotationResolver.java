package com.example.springbootstudy.common.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



@Component
public class AnnotationResolver implements HandlerMethodArgumentResolver{

    //어떤 파라미터를 지정할건지?
    @Override
    public boolean supportsParameter(MethodParameter parameter) {


        // 이 어노테이션이 붙어있으며, 그리고 이 타입이 무엇인지 지정한다.
        boolean isThisAnnotation = parameter.getParameterAnnotation(AnnotationPractice.class) !=null;
        boolean isStringClass = String.class.equals(parameter.getParameterType());
        System.out.println("isThis? " +isThisAnnotation);
        System.out.println("isString? " + isStringClass);
        return isThisAnnotation && isStringClass;
    }


    //해당하는 파라미터값에 무엇을 넣을건지?
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return "안녕?";
    }
}
