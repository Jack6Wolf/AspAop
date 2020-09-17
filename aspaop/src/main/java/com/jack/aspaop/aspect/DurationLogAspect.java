package com.jack.aspaop.aspect;

import android.util.Log;

import com.jack.aspaop.AspAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * @author jack
 * @since 2020/9/17 11:39
 */
@Aspect
public class DurationLogAspect {
    private static final String TAG = "DurationLog";
    /**
     * 方法切入点
     */
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.DurationLog * *(..))";
    /**
     * 构造方法切入点
     */
    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.jack.aspaop.annotation.DurationLog *.new(..))";


    @Pointcut(POINTCUT_METHOD)
    public void methodDurationLog() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorDurationLog() {
    }


    @Around("methodDurationLog() || constructorDurationLog()")
    public Object aroundDurationLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //Release包不打印日志
        if (AspAop.init().getBuildType() == AspAop.DEBUG) {
            //拿到方法的签名
            Signature signature = joinPoint.getSignature();
            if (!(signature instanceof MethodSignature || signature instanceof ConstructorSignature)) {
                throw new Exception("DurationLog注解只能用于方法或构造函数上!");
            }
            String className = signature.getDeclaringType().getSimpleName();
            String methodName = signature.getName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(className).append("#").append(methodName);
            for (Object obj : joinPoint.getArgs()) {
                if (obj != null)
                    stringBuilder.append("(").append(obj.getClass().getSimpleName()).append(")");
            }
            long startTime = System.nanoTime();
            Object result = joinPoint.proceed();//执行原方法
            //计算时间差
            long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            stringBuilder.append("--->:").append(duration).append("ms");
            Log.d(TAG, stringBuilder.toString());
            return result;
        } else {
            return joinPoint.proceed();
        }
    }
}
