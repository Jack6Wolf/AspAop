package com.jack.aspaop.aspect;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.annotation.Delay;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * @author jack
 * @since 2020/9/17 13:54
 */
@Aspect
public class DelayAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.Delay * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void pointcutDelay() {
    }

    @Around("pointcutDelay() && @annotation(delay)")
    public Object aroundDelay(final ProceedingJoinPoint joinPoint, Delay delay) throws Throwable {
        Object result = null;
        long delayTime = delay.delay();
        if (delayTime > 0) {
            MyRunnable myRunnable = new MyRunnable(joinPoint, delay);
            AspAop.init().handler.postDelayed(myRunnable, delayTime);
        } else {
            result = joinPoint.proceed();
            if (AspAop.init().getDelayCallback() != null) {
                AspAop.init().getDelayCallback().taskResult(delay.key(), result, null);
            }
        }
        return result;
    }

    private static class MyRunnable implements Runnable {
        private ProceedingJoinPoint joinPoint;
        private Delay delay;

        public MyRunnable(ProceedingJoinPoint joinPoint, Delay delay) {
            this.joinPoint = joinPoint;
            this.delay = delay;
        }

        @Override
        public void run() {
            try {
                Object result = joinPoint.proceed();
                if (AspAop.init().getDelayCallback() != null) {
                    AspAop.init().getDelayCallback().taskResult(delay.key(), result, null);
                }
            } catch (Throwable throwable) {
                if (AspAop.init().getDelayCallback() != null) {
                    AspAop.init().getDelayCallback().taskResult(delay.key(), null, throwable);
                }
                throwable.printStackTrace();
            }
        }
    }
}
