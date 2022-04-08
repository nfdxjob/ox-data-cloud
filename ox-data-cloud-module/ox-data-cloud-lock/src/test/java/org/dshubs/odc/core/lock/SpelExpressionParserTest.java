package org.dshubs.odc.core.lock;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author create by wangxian 2022/4/8
 */
public class SpelExpressionParserTest {
    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test() {
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", "wangxian");
        context.setVariable("age", 18);
        String expression = "#name + ' is ' + #age + ' years old'";
        String result = parser.parseExpression(expression).getValue(context, String.class);
        Assert.assertEquals("wangxian is 18 years old", result);
    }

    @Test
    public void test1() {
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", "wangxian");
        context.setVariable("age", 18);
        String expression = "'entry'";
        String result = parser.parseExpression(expression).getValue(context, String.class);
        Assert.assertEquals("entry", result);
    }

}
