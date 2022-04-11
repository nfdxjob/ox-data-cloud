package org.dshubs.odc.core.util;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供一些转换工具方法
 *
 * @author create by wangxian 2022/4/11
 */
public class ConvertUtils {

    public static <T, K> List<T> convertList(List<K> source, Class<T> clazz) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        source.forEach(k -> list.add(convert(k, clazz)));
        return list;
    }


    public static <T, K> T convert(K source, Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(source, t);
        return t;
    }
}