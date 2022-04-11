package org.dshubs.odc.core.util.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.dshubs.odc.core.util.JsonUtils;
import org.junit.Test;

import java.util.List;


public class TreeUtilsTest {
    @Test
    public void buildTreeTest() {
        TestNode testNode = new TestNode(1L, 0L, null);
        TestNode testNode2 = new TestNode(2L, 1L, null);
        TestNode testNode3 = new TestNode(3L, 2L, null);
        TestNode testNode4 = new TestNode(4L, 2L, null);
        TestNode testNode5 = new TestNode(5L, 3L, null);
        List<TestNode> nodes = Lists.newArrayList(testNode, testNode2, testNode3, testNode4, testNode5);
        for (int i = 6 ; i <10000 ; i++) {
            TestNode tmpNode = new TestNode((long) i, 3L, null);
            nodes.add(tmpNode);
        }
        TestNode result = TreeUtils.buildTree(nodes, 1L);
//        System.out.println(result);
        System.out.println(JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void buildTreeListTest() {
        TestNode testNode = new TestNode(1L, 0L, null);
        TestNode testNode2 = new TestNode(2L, 0L, null);
        TestNode testNode3 = new TestNode(3L, 1L, null);
        TestNode testNode4 = new TestNode(4L, 2L, null);
        TestNode testNode5 = new TestNode(5L, 2L, null);
        List<TestNode> nodes = Lists.newArrayList(testNode, testNode2, testNode3, testNode4, testNode5);
        for (int i = 6 ; i <10 ; i++) {
            TestNode tmpNode = new TestNode((long) i, 3L, null);
            nodes.add(tmpNode);
        }
        List<TestNode>result = TreeUtils.buildTreeList(nodes, 0L);
        System.out.println(JsonUtils.getInstance().toJson(result));
    }

    @Data
    @AllArgsConstructor
    static class TestNode implements TreeNode<TestNode> {
        private Long id;

        private Long parentId;

        private List<TestNode> children;

        @Override
        public Long getId() {
            return this.id;
        }

        @Override
        public Long getParentId() {
            return this.parentId;
        }

        @Override
        public void setChildren(List<TestNode> children) {
            this.children = children;
        }

        @Override
        public List<TestNode> getChildren() {
            return this.children;
        }
    }
}