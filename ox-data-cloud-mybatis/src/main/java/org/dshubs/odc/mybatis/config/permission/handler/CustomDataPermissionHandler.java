package org.dshubs.odc.mybatis.config.permission.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.dshubs.odc.core.oauth.DetailsUtils;
import org.dshubs.odc.mybatis.config.permission.expression.CustomSqlExpression;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义数据权限处理
 *
 * @author create by wangxian 2022/3/1
 */
@Slf4j
public class CustomDataPermissionHandler implements DataPermissionHandler {


    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        log.debug("自定义数据权限处理,id:{},where:{}", mappedStatementId, where);
        CustomUserDetails customUserDetails = DetailsUtils.getUserDetails();
        log.debug("当前用户信息:{}", customUserDetails);
        if (StringUtils.equals("org.dshubs.odc.infra.mapper.TenantMapper.selectList", mappedStatementId)) {
            log.debug("自定义数据权限处理");
            Set<String> ids = Sets.newLinkedHashSet();
            ids.add("1");
            ids.add("2");
            ids.add("3");
            InExpression inExpression = new InExpression(new Column("tenant_id"), new ExpressionList(ids.stream().map(StringValue::new).collect(Collectors.toList())));
            if (where == null) {
                return inExpression;
            }
            return new AndExpression(where, inExpression);
        }
        if (StringUtils.equals("org.dshubs.odc.infra.mapper.EmployeeMapper.listEmployee", mappedStatementId)) {
            return new CustomSqlExpression("oe.org_code in ('museng','sany')");
        }
        return where;

    }
}
