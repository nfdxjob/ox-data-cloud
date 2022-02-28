package org.dshubs.odc.mybatis.app.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author create by wangxian 2022/3/1
 */
public interface IBaseService<T> {

    List<T> list();

    PageData<T> page(PageRequest pageParam);

    PageData<T> page(PageRequest pageParam, T condition);

    PageData<T> page(PageRequest pageParam, Wrapper<T> queryWrapper);

    T insert(T entity);

    T update(T entity);

    int deleteById(Serializable id);
}
