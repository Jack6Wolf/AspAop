package com.jack.aspaop;

import org.aspectj.lang.reflect.MethodSignature;

/**
 * 降级后的回调
 *
 * @author jack
 * @since 2020/9/14 17:50
 */
public interface DemotionCallback {
    /**
     * @param signature 降级标记的该方法的签名函数，从中可以获取该method的相关信息参数...
     * @param args      方法的参数对象
     */
    void demotion(MethodSignature signature, Object[] args);
}
