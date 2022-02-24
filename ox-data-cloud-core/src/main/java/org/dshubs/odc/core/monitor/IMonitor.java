package org.dshubs.odc.core.monitor;

import java.util.List;

/**
 * @author create by wangxian 2022/2/21
 */
public interface IMonitor {
    /**
     * 获取监控数据
     * @return List
     */
    List<MonitorIndex> getData();
}
