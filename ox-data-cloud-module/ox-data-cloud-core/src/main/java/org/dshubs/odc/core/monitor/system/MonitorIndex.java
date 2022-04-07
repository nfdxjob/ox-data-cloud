package org.dshubs.odc.core.monitor.system;

import lombok.Data;

/**
 * 监控指标
 *
 * @author create by wangxian 2022/2/21
 */
@Data
public class MonitorIndex {
    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;
}
