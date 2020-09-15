package com.jack.aspaop.aspect;

import android.util.Log;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.annotation.NeedDemotion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切面类专门处理注解
 *
 * @author jack
 * @since 2020/9/14 14:15
 */
@Aspect
public class DemotionAspect {
    private static final String TAG = "DemotionAspect";
    /**
     * "execution("//执行语句
     * "@com.jack.aspaop.annotation.NeedDemotion" //注解筛选
     * " * " //类路径,*为任意路径
     * "*"  //方法名,*为任意方法名
     * "(..)" //方法参数,'..'为任意个任意类型参数
     * ")" +
     * " && " +//并集
     * "@annotation(needDemotion)"//注解筛选,这里主要用于下面方法的'NeedLogin'参数获取
     */
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.NeedDemotion * *(..))";

    //任何一个包下面的任何一个类下面的任何一个带有NeedDemotion的方法，构成了这个切面
    @Pointcut(POINTCUT_METHOD)
    public void pointcutNeedDemotion() {
    }

    //拦截方法
    @Around("pointcutNeedDemotion()")
    public Object aroundNeedDemotion(ProceedingJoinPoint joinPoint) throws Throwable {
        //拿到方法的签名
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new Exception("NeedDemotion注解只能用于方法上!");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        NeedDemotion needDemotion = methodSignature.getMethod().getAnnotation(NeedDemotion.class);
        if (needDemotion == null) {
            return null;
        }
        //如果当前手机等级小于等于规定的降级等级不执行这段代码
        if (AspAop.init().getMemoryLevel() <= needDemotion.value()) {
            //类名
            String className = methodSignature.getDeclaringType().getSimpleName();
            //方法名
            String methodName = methodSignature.getName();
            Log.w(TAG, String.format("%s的%s方法被降级了，不执行!", className, methodName));
            if (AspAop.init().getCallback() != null) {
                AspAop.init().getCallback().demotion(methodSignature);
            }
            return null;
        } else {
            //方法继续执行
            return joinPoint.proceed();
        }
    }
}
