package org.dshubs.odc.core.lock;

import org.aspectj.lang.JoinPoint;
import org.dshubs.odc.core.lock.annotation.Lock;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

/**
 * @author create by wangxian 2022/4/7
 */
@Component
public class LockInfoProvider {
    private final ExpressionParser parser = new SpelExpressionParser();


    public LockInfo getLockInfo(JoinPoint joinPoint, Lock lock) {
        LockInfo lockInfo = new LockInfo(lock.name(), lock.waitTime(), lock.leaseTime(), lock.timeUnit());
        return lockInfo;
    }
}