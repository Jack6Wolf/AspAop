package com.jack.aspaop.aspect;

import android.text.TextUtils;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.annotation.Interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jack
 * @since 2020/9/17 09:42
 */
@Aspect
public class InterceptorAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.Interceptor * *(..))";
    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.jack.aspaop.annotation.Interceptor *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodInterceptor() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorInterceptor() {
    }

    /**
     * " && " 交集  " ,|| "并集
     * "@annotation(interceptor)"//注解筛选,这里主要用于下面方法的'Interceptor'参数获取
     */
//  ok  @Around("(methodInterceptor() || constructorInterceptor()) && @annotation(interceptor)")
    @Around("methodInterceptor() || constructorInterceptor()")
    public Object aroundInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        if (AspAop.init().getInterceptorCallback() == null)
            return joinPoint.proceed();
        Interceptor interceptor = null;
        if (joinPoint.getSignature() instanceof MethodSignature) {
            interceptor = (Interceptor) ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Interceptor.class);
        } else if (joinPoint.getSignature() instanceof ConstructorSignature) {
            interceptor = (Interceptor) ((ConstructorSignature) joinPoint.getSignature()).getConstructor().getAnnotation(Interceptor.class);
        } else {
            throw new Exception("Interceptor注解只能用于方法或构造函数上!");
        }
        if (interceptor == null)
            throw new Exception("Interceptor注解只能用于方法或构造函数上!");
        String value = interceptor.value();
        if (!TextUtils.isEmpty(value)) {
            //拦截
            boolean intercept = AspAop.init().getInterceptorCallback().intercept(value, joinPoint.getSignature().getName());
            if (intercept) {
                return null;
            } else {
                return joinPoint.proceed();
            }
        }
        return joinPoint.proceed();
    }
}
