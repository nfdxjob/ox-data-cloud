package org.dshubs.odc.core.lock;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author create by wangxian 2022/4/7
 */
@Data
public class LockInfo {

    /**
     * 锁的key
     */
    private String lockId;

    /**
     * 等待时间
     */
    private long waitTime;

    /**
     * 租赁时间
     */
    private long leaseTime;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;


    public LockInfo(String lockId, long waitTime, long leaseTime, TimeUnit timeUnit) {
        this.lockId = lockId;
        this.waitTime = waitTime;
        this.leaseTime = leaseTime;
        this.timeUnit = timeUnit;
    }

}
