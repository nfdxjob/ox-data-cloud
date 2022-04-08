package org.dshubs.odc.core.lock.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.lock.LockInfo;
import org.dshubs.odc.core.lock.app.service.LockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/4/7
 */
@Service
@Slf4j
public class DefaultLockServiceImpl implements LockService {
    @Autowired
    private RedissonClient redissonClient;


    private final ThreadLocal<LockInfo> lockInfoThreadLocal = new ThreadLocal<>();


    @Override
    public void setLockInfo(LockInfo lockInfo) {
        this.lockInfoThreadLocal.set(lockInfo);
    }

    @Override
    public LockInfo getLockInfo() {
        return this.lockInfoThreadLocal.get();
    }

    @Override
    public void clearLockInfo() {
        this.lockInfoThreadLocal.remove();
    }

    @Override
    public boolean isLock() {
        LockInfo lockInfo = this.getLockInfo();
        RLock rLock = this.redissonClient.getFairLock(lockInfo.getName());
        try {
            return rLock.tryLock(lockInfo.getWaitTime(), lockInfo.getLeaseTime(), lockInfo.getTimeUnit());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void releaseLock() {
        LockInfo lockInfo = this.getLockInfo();
        RLock rLock = this.redissonClient.getFairLock(lockInfo.getName());
        if (rLock.isHeldByCurrentThread()) {
            rLock.unlockAsync();
        }
        this.clearLockInfo();
    }
}
