package com.jack.aspaop.aspect;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.annotation.Catch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jack
 * @since 2020/9/23 14:43
 */
@Aspect
public class CatchAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.Catch * *(..))";


    @Pointcut(POINTCUT_METHOD)
    public void methodCatch() {
    }

    @Around("methodCatch() && @annotation(localCatch)")
    public Object aroundCatch(ProceedingJoinPoint joinPoint, Catch localCatch) throws Throwable {
        Object result = null;
        //默认全部都try-catch,也不回调回去
        if (AspAop.init().getCatchCallback() == null) {
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            String value = localCatch.value();
            //不使用catch
            if (value == null) {
                result = joinPoint.proceed();
            } else {
                try {
                    result = joinPoint.proceed();
                } catch (Throwable throwable) {
                    //是否捕获
                    boolean isCatch = AspAop.init().getCatchCallback().capture(value, throwable);
                    //重新抛出
                    if (!isCatch) {
                        throw throwable;
                    }
                }
            }
        }
        return result;
    }
}
