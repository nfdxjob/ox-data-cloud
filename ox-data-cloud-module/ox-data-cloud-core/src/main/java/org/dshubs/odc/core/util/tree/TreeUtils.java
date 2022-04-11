package org.dshubs.odc.core.util.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author create by wangxian 2022/4/11
 */
public class TreeUtils {
    /**
     * 构建顶级节点只有一个的树形结构
     *
     * @param list 数据
     * @param root 顶级节点ID
     * @param <T>  泛型
     * @return 树形结构
     */
    public static <T extends TreeNode<T>> T buildTree(List<T> list, Serializable root) {
        return TreeUtils.buildTree(list, root, null);
    }

    /**
     * 构建顶级节点有多个的树形结构
     *
     * @param list     数据
     * @param parentId 顶级父节点ID
     * @param <T>      泛型
     * @return 树形结构
     */
    public static <T extends TreeNode<T>> List<T> buildTreeList(List<T> list, Serializable parentId) {
        List<T> treeList = new ArrayList<>();
        for (T node : list) {
            if (node.getParentId().equals(parentId)) {
                treeList.add(node);
                TreeUtils.buildTree(list, node.getId());
            }
        }
        return treeList;
    }


    private static <T extends TreeNode<T>> T buildTree(List<T> list, Serializable root, T rootNode) {
        if (rootNode == null) {
            for (T node : list) {
                if (node.getId().equals(root)) {
                    rootNode = node;
                    break;
                }
            }
        }
        if (rootNode == null) {
            return null;
        }
        for (T node : list) {
            if (node.getParentId().equals(rootNode.getId())) {
                if (rootNode.getChildren() == null) {
                    rootNode.setChildren(new ArrayList<>());
                }
                rootNode.getChildren().add(node);
                buildTree(list, node.getId(), node);
            }
        }
        return rootNode;
    }
}

