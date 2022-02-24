package org.dshubs.odc.core.monitor;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author create by wangxian 2022/2/21
 */
@Slf4j
public class JvmMonitor implements IMonitor {
    @Override
    public List<MonitorIndex> getData() {
        List<MonitorIndex> monitorIndices = new ArrayList<>();
        Properties props = System.getProperties();
        System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：" + props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL：" + props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径：" + props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本：" + props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商：" + props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称：" + props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本：" + props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商：" + props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称：" + props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本：" + props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：" + props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称：" + props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号：" + props.getProperty("java.class.version"));
        System.out.println("Java的类路径：" + props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：" + props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：" + props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
        return monitorIndices;
    }


    private static final String LINUX_MEMORY_INFO_PATH = "/proc/meminfo";

    private static final Pattern LINUX_MEMORY_REGEX = Pattern.compile("^MemTotal:\\s*(\\d+)\\s+kB$");


    public static int getNumberCPUCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static long getSizeOfPhysicalMemory() {
        //在Oracle JVM下系统有可能直接告诉我们系统内存，因此首先通过这种方式尝试获取内存
        try {
            Class<?> clazz = Class.forName("com.sun.management.OperatingSystemMXBean");
            Method method = clazz.getMethod("getTotalPhysicalMemorySize");
            OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
            //检查是否是sun提供的MXBean
            if (clazz.isInstance(operatingSystemMXBean)) {
                return (Long) method.invoke(operatingSystemMXBean);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
            return getSizeOfPhysicalMemoryForLinux();
        } else if (System.getProperty("os.name").toLowerCase().startsWith("window")) {
            return getSizeOfPhysicalMemoryForWindows();
        } else if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            return getSizeOfPhysicalMemoryForMac();
        } else if (System.getProperty("os.name").toLowerCase().startsWith("freebsd")) {
            return getSizeOfPhysicalMemoryForFreeBSD();
        } else {
            return -1;
        }


    }

    /**
     * Linux系统获取内存
     */
    private static long getSizeOfPhysicalMemoryForLinux() {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(LINUX_MEMORY_INFO_PATH))) {
            String line;
            while ((line = lineReader.readLine()) != null) {
                Matcher matcher = LINUX_MEMORY_REGEX.matcher(line);
                if (matcher.matches()) {
                    String totalMemory = matcher.group(1);
                    return Long.parseLong(totalMemory) * 1024L;
                }
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Mac系统获取内存
     */
    private static long getSizeOfPhysicalMemoryForMac() {
        BufferedReader bi = null;
        try {
            Process proc = Runtime.getRuntime().exec("sysctl hw.memsize");
            bi = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = bi.readLine()) != null) {
                if (line.startsWith("hw.memsize")) {
                    long memerySize = Long.parseLong(line.split(":")[1].trim());
                    bi.close();
                    proc.destroy();
                    return memerySize;
                }
            }
        } catch (Throwable t) {
            return -1;
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (IOException ignored) {
                }
            }
        }
        return -1;
    }

    /**
     * FreeBSD系统
     */
    private static long getSizeOfPhysicalMemoryForFreeBSD() {
        BufferedReader bi = null;
        try {
            Process proc = Runtime.getRuntime().exec("sysctl hw.physmem");

            bi = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = bi.readLine()) != null) {
                if (line.startsWith("hw.physmem")) {
                    long memsize = Long.parseLong(line.split(":")[1].trim());
                    bi.close();
                    proc.destroy();
                    return memsize;
                }
            }
            return -1;
        } catch (Throwable t) {
            return -1;
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * Window 系统
     */
    private static long getSizeOfPhysicalMemoryForWindows() {
        BufferedReader bi = null;
        try {
            Process proc = Runtime.getRuntime().exec("wmic memorychip get capacity");
            bi = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = bi.readLine();
            if (line == null) {
                return -1L;
            }
            if (!line.startsWith("Capacity")) {
                return -1L;
            }
            long sizeOfPhyiscalMemory = 0L;
            while ((line = bi.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                line = line.replaceAll(" ", "");
                sizeOfPhyiscalMemory += Long.parseLong(line);
            }
            return sizeOfPhyiscalMemory;
        } catch (Throwable t) {
            return -1L;
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (Throwable ignored) {
                }
            }
        }
    }


    public static void main(String[] args) {
        IMonitor iMonitor = new JvmMonitor();
        iMonitor.getData();
        System.out.println( JvmMonitor.getNumberCPUCores());
        System.out.println( JvmMonitor.getSizeOfPhysicalMemory());
    }
}
