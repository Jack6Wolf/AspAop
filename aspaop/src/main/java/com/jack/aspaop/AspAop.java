package com.jack.aspaop;

import com.jack.aspaop.annotation.NeedDemotion;

/**
 * Sdk初始化
 * 设置手机内存等级
 * 设置拦截方法后的回调
 *
 * @author jack
 * @since 2020/9/14 17:49
 */
public class AspAop {
    private DemotionCallback callback;
    private int level = NeedDemotion.DEFAULT;

    private AspAop() {
    }

    public static AspAop init() {
        return SingletonInstance.INSTANCE;
    }

    public DemotionCallback getCallback() {
        return callback;
    }

    public void addCallback(DemotionCallback callback) {
        this.callback = callback;
    }

    public int getMemoryLevel() {
        return level;
    }

    public void setMemoryLevel(int level) {
        this.level = level;
    }

    private static class SingletonInstance {
        private static AspAop INSTANCE = new AspAop();
    }
}
