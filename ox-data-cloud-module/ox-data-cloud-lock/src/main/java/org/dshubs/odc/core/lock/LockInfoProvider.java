package org.dshubs.odc.core.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.dshubs.odc.core.lock.annotation.Lock;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author create by wangxian 2022/4/7
 */
@Component
@Slf4j
public class LockInfoProvider {
    private final ExpressionParser parser = new SpelExpressionParser();


    public LockInfo getLockInfo(JoinPoint joinPoint, Lock lock) {

        Method method = this.getMethod(joinPoint);
        Object[] args = joinPoint.getArgs();
        EvaluationContext context = new StandardEvaluationContext();
        Parameter[] parameters = method.getParameters();

        String lockName = lock.name();
        Object value;
        for (int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            value = args[i];
            context.setVariable(name, value);
        }
        try {
            lockName = parser.parseExpression(lockName).getValue(context, String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new LockInfo(lockName, lock.waitTime(), lock.leaseTime(), lock.timeUnit());
    }

    private Method getMethod(JoinPoint lockPoint) {
        MethodSignature methodSignature = (MethodSignature) lockPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = lockPoint.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return method;
    }
}