package org.dshubs.odc.mybatis.config.permission.expression;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.SimpleNode;

/**
 * 自定义SQL
 *
 * @author create by wangxian 2022/3/3
 */
public class CustomSqlExpression implements Expression {
    private final String sql;


    public CustomSqlExpression(String sql) {
        this.sql = sql;
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {

    }

    @Override
    public SimpleNode getASTNode() {
        return null;
    }

    @Override
    public void setASTNode(SimpleNode node) {

    }

    @Override
    public String toString() {
        return this.sql;
    }
}
