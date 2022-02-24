package org.dshubs.odc.infra.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 生成代码字段映射配置
 *
 * @author wangxian 2021/3/17
 */

@Data
public class GenerateCodeFiledMappingConfig {

    private static final Map<String, String> FIELD_MAPPING_MAP = Maps.newHashMap();

    private static final List<String> FIELD_IGNORE_LIST = Lists.newLinkedList();

    private static final Map<String, String> TEMPLATE_LIST_MAP = Maps.newHashMap();

    static {
        FIELD_MAPPING_MAP.put("bigint", "Long");
        FIELD_MAPPING_MAP.put("varchar", "String");
        FIELD_MAPPING_MAP.put("tinyint", "Integer");
        FIELD_MAPPING_MAP.put("decimal", "BigDecimal");
        FIELD_MAPPING_MAP.put("date", "LocalDate");
        FIELD_MAPPING_MAP.put("datetime", "LocalDateTime");

        FIELD_IGNORE_LIST.add("id");
        FIELD_IGNORE_LIST.add("create_by");
        FIELD_IGNORE_LIST.add("create_date");
        FIELD_IGNORE_LIST.add("update_by");
        FIELD_IGNORE_LIST.add("update_date");
        FIELD_IGNORE_LIST.add("is_deleted");


        TEMPLATE_LIST_MAP.put("generate/entity.ftlh", "entity");
        TEMPLATE_LIST_MAP.put("generate/mapper.ftlh", "mapper");
        TEMPLATE_LIST_MAP.put("generate/mapping.ftlh", "mapping");
        TEMPLATE_LIST_MAP.put("generate/service.ftlh", "service");
        TEMPLATE_LIST_MAP.put("generate/controller.ftlh", "controller");
    }

    public static List<String> getTemplateList() {
        return Lists.newArrayList(TEMPLATE_LIST_MAP.keySet());
    }

    public static Map<String, String> getTemplateListMap() {
        return TEMPLATE_LIST_MAP;
    }


    public static String getFiledType(String dbType) {
        return FIELD_MAPPING_MAP.getOrDefault(dbType, "String");
    }

    public static Boolean ignoreContains(String key) {
        return FIELD_IGNORE_LIST.contains(key);
    }

}
