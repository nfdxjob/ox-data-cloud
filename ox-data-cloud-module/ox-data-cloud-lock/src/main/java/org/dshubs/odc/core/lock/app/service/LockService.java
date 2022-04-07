package org.dshubs.odc.core.lock.app.service;

import org.dshubs.odc.core.lock.LockInfo;

/**
 * @author create by wangxian 2022/4/7
 */
public interface LockService {

    /**
     * 设置锁的信息
     *
     * @param lockInfo the lockInfo to set
     */
    void setLockInfo(LockInfo lockInfo);

    /**
     * 获取锁的信息
     *
     * @return LockInfo
     */
    LockInfo getLockInfo();

    /**
     * 清除锁的信息
     */
    void clearLockInfo();

    /**
     * 判断是否锁定
     *
     * @return boolean
     */
    boolean isLock();

    /**
     * 释放锁
     */
    void releaseLock();
}
