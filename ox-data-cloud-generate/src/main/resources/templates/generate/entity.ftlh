package ${packageName}.domain.entity;

import org.dshubs.odc.core.domain.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if hasLocalDate>
import java.time.LocalDate;
</#if>
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>

/**
* ${tableComment}
*
* @author ${author} ${date}
*/
@Data
@ApiModel("${tableComment}模型")
@EqualsAndHashCode(callSuper=false)
@TableName("${tableName}")
<#if extendsBaseEntity>
public class ${className} extends AuditEntity {
<#else>
public class ${className} {
</#if>


<#list columns as column>
    <#if !column.ignore>
    /**
     * ${column.comment}
     */
    @ApiModelProperty("${column.comment}")
    <#if column.hasPrimaryKey>
    @TableId
    </#if>
    private ${column.attrType} ${column.attrName};

    </#if>
</#list>
}
