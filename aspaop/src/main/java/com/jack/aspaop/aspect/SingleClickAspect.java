package com.jack.aspaop.aspect;

import android.view.View;

import com.jack.aspaop.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * @author jack
 * @since 2020/9/17 10:28
 */
@Aspect
public class SingleClickAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.jack.aspaop.annotation.SingleClick * *(..))";


    @Pointcut(POINTCUT_METHOD)
    public void pointcutSingleClick() {
    }

    /**
     * void onClick(View v);
     */
    @Around("pointcutSingleClick() && @annotation(singleClick)")
    public void aroundSingleClick(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        View view = null;
        Object arg = joinPoint.getArgs()[0];
        if (arg instanceof View) {
            view = (View) arg;
        } else {
            throw new IllegalArgumentException("SingleClick注解只能用于类似void xxx(View v)方法上!");
        }
        int[] ids = singleClick.ids();
        //当前view不需要防重复点击
        if (!hasId(ids, view.getId())) {
            joinPoint.proceed();
        } else {
            //获取最后点击时间
            Object tag = view.getTag(view.getId());
            long lastClickTime = ((tag != null) ? (long) tag : 0L);
            //获取当前点击时间
            long currentTime = Calendar.getInstance().getTimeInMillis();
            long value = singleClick.value();
            //过滤掉500毫秒内的连续点击
            if (currentTime - lastClickTime > value) {
                view.setTag(view.getId(), currentTime);
                joinPoint.proceed();//执行原方法
            }
        }
    }


    /**
     * 当前view是否需要防重复点击
     */
    public boolean hasId(int[] ids, int viewId) {
        if (ids == null)
            return false;
        for (int id : ids) {
            if (id == viewId)
                return true;
        }
        return false;
    }
}
