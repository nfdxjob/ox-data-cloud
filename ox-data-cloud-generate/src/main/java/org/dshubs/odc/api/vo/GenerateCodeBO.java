package org.dshubs.odc.api.vo;

import lombok.Data;
import org.dshubs.odc.domain.entity.generate.TableColumn;

import java.util.List;

/**
 * @author wangxian 2021/3/17
 */
@Data
public class GenerateCodeBO {
    private List<TableColumn> columns;

    /**
     * 作者
     */
    private String author;

    /**
     * 时间
     */
    private String date;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 是否包含BigDecimal
     */
    private Boolean hasBigDecimal;

    /**
     * 是否包含LocalDate
     */
    private Boolean hasLocalDate;

    /**
     * 是否包含LocalDateTime
     */
    private Boolean hasLocalDateTime;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 类名,大写开头
     */
    private String className;

    /**
     * 小写开头className
     */
    private String lowerClassName;

    private Boolean extendsBaseEntity;


}
