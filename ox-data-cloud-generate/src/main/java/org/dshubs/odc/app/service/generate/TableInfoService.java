package org.dshubs.odc.app.service.generate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.api.vo.GenerateCodeBO;
import org.dshubs.odc.api.vo.GenerateCodeVO;
import org.dshubs.odc.api.vo.PageData;
import org.dshubs.odc.api.vo.TableInfoGenerateQuery;
import org.dshubs.odc.domain.entity.generate.TableColumn;
import org.dshubs.odc.domain.entity.generate.TableInfo;
import org.dshubs.odc.infra.config.GenerateCodeFiledMappingConfig;
import org.dshubs.odc.infra.exception.GenerateCodeException;
import org.dshubs.odc.infra.mapper.TableInfoMapper;
import org.dshubs.odc.infra.util.GlobalErrorCodeEnum;
import org.dshubs.odc.infra.util.WordUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wangxian 2021/3/16
 */
@Service
@Slf4j
public class TableInfoService {
    private final TableInfoMapper tableInfoMapper;

    private final FreeMarkerConfigurer freeMarkerConfigurer;

    public TableInfoService(TableInfoMapper tableInfoMapper, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.tableInfoMapper = tableInfoMapper;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public PageData<TableInfo> pageQuery(TableInfoGenerateQuery query) {
        Page<TableInfo> page = tableInfoMapper.pageQuery(new Page<>(query.getPage(), query.getSize()), query);
        return new PageData<>(page.getRecords(), page.getTotal());
    }

    public byte[] generateCode(GenerateCodeVO generateCode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        List<String> tableNameList = generateCode.getTables();
        if (!tableNameList.isEmpty()) {
            for (String tableName : tableNameList) {
                TableInfo tableInfo = tableInfoMapper.getTableByName(tableName);
                if (tableInfo == null) {
                    continue;
                }
                List<TableColumn> tableColumns = tableInfoMapper.listTableColumnByTableName(tableName);
                if (tableColumns.isEmpty()) {
                    continue;
                }
                boolean hasBigDecimal = false;
                boolean hasLocalDate = false;
                boolean hasLocalDateTime = false;
                for (TableColumn tableColumn : tableColumns) {
                    String columnName = tableColumn.getColumnName();
                    if (generateCode.getConfig().getExtendsBaseEntity()) {
                        if (GenerateCodeFiledMappingConfig.ignoreContains(columnName)) {
                            tableColumn.setIgnore(true);
                        }
                    }
                    tableColumn.setAttrType(GenerateCodeFiledMappingConfig.getFiledType(tableColumn.getDataType()));
                    tableColumn.setAttrName(WordUtils.toCamelCaseFirstLower(columnName, "_"));
                    String dataType = tableColumn.getDataType();
                    if (!hasBigDecimal && "decimal".equalsIgnoreCase(dataType)) {
                        hasBigDecimal = true;
                    }
                    if (!hasLocalDate && "date".equalsIgnoreCase(dataType)) {
                        hasLocalDate = true;
                    }
                    if (!hasLocalDateTime && "datetime".equalsIgnoreCase(dataType)) {
                        hasLocalDateTime = true;
                    }
                }
                GenerateCodeBO generateCodeBO = new GenerateCodeBO();
                generateCodeBO.setAuthor(generateCode.getConfig().getAuthor());
                generateCodeBO.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                generateCodeBO.setTableName(tableName);
                generateCodeBO.setTableComment(tableInfo.getComment());
                String tablePrefix = generateCode.getConfig().getPrefix();
                if (StringUtils.isNotBlank(tablePrefix)) {
                    String tmpClassName = tableName.substring(tablePrefix.length());
                    generateCodeBO.setClassName(WordUtils.toCamelCaseFirstUpper(tmpClassName, "_"));
                    generateCodeBO.setApiBasePath(StringUtils.replace(tmpClassName, "_", "-") + "s");
                } else {
                    generateCodeBO.setClassName(WordUtils.toCamelCaseFirstUpper(tableName, "_"));
                    generateCodeBO.setApiBasePath(StringUtils.replace(tableName, "_", "-") + "s");
                }
                generateCodeBO.setLowerClassName(WordUtils.firstWordToLowerCase(generateCodeBO.getClassName()));
                generateCodeBO.setPackageName(generateCode.getConfig().getPackageName());
                generateCodeBO.setHasBigDecimal(hasBigDecimal);
                generateCodeBO.setHasLocalDate(hasLocalDate);
                generateCodeBO.setHasLocalDateTime(hasLocalDateTime);
                generateCodeBO.setColumns(tableColumns);
                generateCodeBO.setExtendsBaseEntity(generateCode.getConfig().getExtendsBaseEntity());
                generateTemplate(zip, generateCodeBO);
            }
        }
        IOUtils.closeQuietly(zip, null);
        return outputStream.toByteArray();
    }

    public Template getTemplate(String templatePath) {
        try {
            return freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
        } catch (IOException e) {
            log.error("模板获取失败", e);
        }
        throw new GenerateCodeException(GlobalErrorCodeEnum.GENERATE_CODE_TEMPLATE_NOT_FOUND);
    }


    public String buildTemplate(Template template, GenerateCodeBO generateCodeBO) {
        try {
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, generateCodeBO);
            log.info("解析内容:{}", content);
            return content;
        } catch (IOException | TemplateException e) {
            log.error("模板解析异常,", e);
        }
        throw new GenerateCodeException(GlobalErrorCodeEnum.GENERATE_CODE_TEMPLATE_PARSE_ERROR);
    }

    public void generateTemplate(ZipOutputStream zip, GenerateCodeBO generateCodeBO) {
        List<String> templates = GenerateCodeFiledMappingConfig.getTemplateList();
        for (String templatePath : templates) {
            String template = buildTemplate(getTemplate(templatePath), generateCodeBO);
            String file = getFileName(templatePath, generateCodeBO.getPackageName(), generateCodeBO.getClassName());
            ZipEntry zipEntry = new ZipEntry(file);
            try {
                zip.putNextEntry(zipEntry);
                IOUtils.write(template, zip, "UTF-8");
                zip.closeEntry();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private String getFileName(String templatePath, String packageName, String className) {

        Map<String, String> templateConfigMap = GenerateCodeFiledMappingConfig.getTemplateListMap();
        String type = templateConfigMap.get(templatePath);
        if ("entity".equalsIgnoreCase(type)) {
            return File.separator + packageName.replace(".", File.separator)
                    + File.separator
                    + "domain" +
                    File.separator
                    + "entity" +
                    File.separator
                    + className + ".java";
        }
        if ("mapper".equalsIgnoreCase(type)) {
            return File.separator + packageName.replace(".", File.separator)
                    + File.separator
                    + "infra" +
                    File.separator
                    + "mapper" +
                    File.separator
                    + className + "Mapper.java";
        }
        if ("service".equalsIgnoreCase(type)) {
            return File.separator + packageName.replace(".", File.separator)
                    + File.separator
                    + "app" +
                    File.separator
                    + "service" +
                    File.separator
                    + className + "Service.java";
        }
        if ("serviceImpl".equalsIgnoreCase(type)) {
            return File.separator + packageName.replace(".", File.separator)
                    + File.separator
                    + "app" +
                    File.separator
                    + "service" +
                    File.separator
                    + "impl" +
                    File.separator
                    + className + "ServiceImpl.java";
        }
        if ("controller".equalsIgnoreCase(type)) {
            return File.separator + packageName.replace(".", File.separator)
                    + File.separator
                    + "api"
                    + File.separator
                    + "controller"
                    + File.separator
                    + "v1" +
                    File.separator
                    + className + "Controller.java";
        }
        if ("mapping".equalsIgnoreCase(type)) {
            return File.separator
                    + "mapper" +
                    File.separator
                    + className + "Mapper.xml";
        }
        return "";
    }
}
