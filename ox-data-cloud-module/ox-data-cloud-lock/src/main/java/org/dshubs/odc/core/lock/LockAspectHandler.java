package org.dshubs.odc.core.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.dshubs.odc.core.lock.annotation.Lock;
import org.dshubs.odc.core.lock.app.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author create by wangxian 2022/4/7
 */
@Component
@Aspect
@Order(0)
public class LockAspectHandler {

    @Autowired
    private LockService lockService;

    @Autowired
    private LockInfoProvider lockInfoProvider;

    @Around("@annotation(lock)")
    public Object lock(ProceedingJoinPoint pjp, Lock lock) throws Throwable {

        LockInfo lockInfo = lockInfoProvider.getLockInfo(pjp, lock);

        if (lockService.getLockInfo() != null) {

            return pjp.proceed();
        }
        Object result;
        boolean lockSuccess = false;
        try {

            lockService.setLockInfo(lockInfo);
            boolean lockResult = lockService.isLock();
            if (!lockResult) {

            }
            lockSuccess = true;
            result = pjp.proceed();
        } finally {
            if (lockSuccess) {
                lockService.releaseLock();
            }
        }

        return result;

    }
}
