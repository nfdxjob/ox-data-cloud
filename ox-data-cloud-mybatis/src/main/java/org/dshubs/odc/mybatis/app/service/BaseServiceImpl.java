package org.dshubs.odc.mybatis.app.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis通用功能封装
 *
 * @author create by wangxian 2022/2/28
 */
@Service
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements IBaseService<T> {

    @Autowired
    protected M baseMapper;


    @Override
    public List<T> list() {
        return baseMapper.selectList(null);
    }

    @Override
    public PageData<T> page(PageRequest pageParam) {
        IPage<T> page = baseMapper.selectPage(getPageQuery(pageParam), null);
        return new PageData<>(page.getRecords(), page.getTotal());
    }

    @Override
    public PageData<T> page(PageRequest pageParam, T condition) {
        return this.page(pageParam, new QueryWrapper<>(condition));
    }

    @Override
    public PageData<T> page(PageRequest pageParam, Wrapper<T> queryWrapper) {
        IPage<T> page = baseMapper.selectPage(getPageQuery(pageParam), queryWrapper);
        return new PageData<>(page.getRecords(), page.getTotal());
    }

    @Override
    public T insert(T entity) {
        this.baseMapper.insert(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        this.baseMapper.updateById(entity);
        return entity;
    }

    @Override
    public int deleteById(Serializable id) {
        return this.baseMapper.deleteById(id);
    }

    private Page<T> getPageQuery(PageRequest pageParam) {
        Page<T> page = new Page<>(pageParam.getPage(), pageParam.getPerPage());
        if (StringUtils.isNotBlank(pageParam.getSort())) {
            String[] sortProperties = StringUtils.split(pageParam.getSort(), ",");
            List<OrderItem> orderItems = new ArrayList<>();
            if (sortProperties != null) {
                for (String sortProperty : sortProperties) {
                    String sortType = "asc";
                    if (sortProperty.contains("|")) {
                        String[] sortArray = StringUtils.split(sortProperty, "|");
                        if (sortArray.length >= 1) {
                            sortType = sortArray[1];
                        }
                        OrderItem orderItem = new OrderItem(sortArray[0], sortType.equals("asc"));
                        orderItems.add(orderItem);
                    }
                }
            }
            page.addOrder(orderItems);

        }
        return page;
    }


}
