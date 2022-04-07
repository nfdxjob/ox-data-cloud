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

    /**
     * List all data
     *
     * @return List
     */
    List<T> list();


    /**
     * page query data list
     *
     * @param pageParam page
     * @return PageData
     */
    PageData<T> page(PageRequest pageParam);

    /**
     * page query data list
     *
     * @param pageParam page
     * @param condition data filter condition
     * @return PageData
     */
    PageData<T> page(PageRequest pageParam, T condition);


    /**
     * page query data list
     *
     * @param pageParam    page
     * @param queryWrapper data filter condition
     * @return PageData
     */
    PageData<T> page(PageRequest pageParam, Wrapper<T> queryWrapper);

    /**
     * Select By Primary key
     * @param primaryKey primary key
     * @return T
     */
    T selectById(Serializable primaryKey);

    /**
     * insert entity
     *
     * @param entity entity
     * @return entity
     */
    T insert(T entity);

    /**
     * update by primary key
     *
     * @param entity delete body
     * @return T
     */
    T update(T entity);

    /**
     * delete by primary key
     *
     * @param id primary key
     * @return deleted rows
     */
    int deleteById(Serializable id);
}
