package org.dshubs.odc.core.util.tree;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author create by wangxian 2022/4/11
 */
public interface TreeNode<T> extends Serializable {
    /**
     * id
     *
     * @return id
     */
    Serializable getId();


    /**
     * 获取父Id点
     *
     * @return Long
     */
    Serializable getParentId();


    /**
     * 设置子节点
     *
     * @param children 子节点
     */
    void setChildren(List<T> children);

    /**
     * 获取子节点
     *
     * @return 子节点
     */
    List<T> getChildren();
}
