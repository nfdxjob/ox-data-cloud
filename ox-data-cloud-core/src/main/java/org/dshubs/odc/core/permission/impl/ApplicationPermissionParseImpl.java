package org.dshubs.odc.core.permission.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.permission.ApplicationPermissionParse;
import org.dshubs.odc.core.permission.PermissionData;
import org.dshubs.odc.core.util.FieldNameUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author create by wangxian 2021/12/30
 */
@Component
@Slf4j
public class ApplicationPermissionParseImpl implements ApplicationPermissionParse, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private static final String SUFFIX_CONTROLLER = "-controller";

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void parse() {
        this.findController();
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void findController() {
        List<PermissionData> permissionDataList = new ArrayList<>();

        String[] beanNames = this.applicationContext.getBeanNamesForType(Object.class);
        if (ArrayUtils.isNotEmpty(beanNames)) {
            log.info("bean size:{}", beanNames.length);
            Class<?> beanType;
            for (String beanName : beanNames) {
                beanType = this.applicationContext.getType(beanName);
                if (isController(beanType)) {
                    permissionDataList.addAll(this.parseController(beanName, beanType));
                }

            }
        }
        try {
            log.info("API:{}",objectMapper.writeValueAsString(permissionDataList));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
        }
    }


    private boolean isController(Class<?> controller) {
        return (AnnotatedElementUtils.hasAnnotation(controller, Controller.class) || AnnotatedElementUtils.hasAnnotation(controller, RequestMapping.class));
    }

    private List<PermissionData> parseController(String controllerName, Class<?> clazz) {
        List<PermissionData> permissionDataList = new ArrayList<>();
        Api apiController = AnnotatedElementUtils.findMergedAnnotation(clazz, Api.class);
        String resourceCode = this.getResourceCode(apiController, controllerName);
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(clazz, RequestMapping.class);
        String[] controllerPaths = null;
        if (requestMapping != null) {
            controllerPaths = requestMapping.value();
        }
        if (ArrayUtils.isEmpty(controllerPaths)) {
            controllerPaths = new String[]{""};
        }
        for (Method method : clazz.getMethods()) {
            permissionDataList.addAll(this.parseMethod(method, resourceCode, controllerPaths));
        }
        return permissionDataList;
    }

    private List<PermissionData> parseMethod(Method method, String resourceCode, String[] paths) {
        List<PermissionData> permissionDataList = new ArrayList<>();
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if (requestMapping != null) {
            Permission permission = AnnotationUtils.findAnnotation(method, Permission.class);
            if (permission == null) {
                return Collections.emptyList();
            }
            String[] methodPaths = requestMapping.value();
            if (ArrayUtils.isEmpty(methodPaths)) {
                methodPaths = new String[]{""};
            }
            RequestMethod[] requestMethods = requestMapping.method();
            if (ArrayUtils.isEmpty(requestMethods)) {
                requestMethods = RequestMethod.values();
            }
            ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
            String description = null;
            if (apiOperation != null) {
                description = apiOperation.value();
            }
            String methodName = method.getName();
            int index;
            for (RequestMethod requestMethod : requestMethods) {
                index = 0;
                for (String path : paths) {
                    for (String methodPath : methodPaths) {
                        index++;
                        permissionDataList.add(this.buildPermissionData(resourceCode,paths.length,methodPaths.length,
                                requestMethods.length,description,permission,methodName,requestMethod.name().toLowerCase(),index,path,methodPath));
                    }
                }
            }
        }
        return permissionDataList;

    }

    private PermissionData buildPermissionData(String resourceCode, int controllerPathLength, int methodPathLength,
                                               int requestMethodLength, String description, Permission permission,
                                               String methodName, String requestMethod, int index, String controllerPath,
                                               String methodPath) {
        PermissionData permissionData = new PermissionData();
        permissionData.setDescription(description);
        permissionData.setServerName(applicationName);
        permissionData.setMethod(methodName);
        permissionData.setRequestMethod(requestMethod);
        permissionData.setPath(this.concatPath(controllerPath, methodPath));
        if (permission != null) {
            permissionData.setCode(this.generatePermissionCode(permission.code(),methodName,requestMethod,
                    index,requestMethodLength,controllerPathLength,methodPathLength));
            permissionData.setApiIsLogin(permission.apiIsLogin());
            permissionData.setApiIsPublic(permission.apiIsPublic());
            permissionData.setApiIsSign(permission.apiIsSign());
        } else {
            permissionData.setCode(this.generatePermissionCode(null,methodName,requestMethod,
                    index,requestMethodLength,controllerPathLength,methodPathLength));
            permissionData.setApiIsLogin(false);
            permissionData.setApiIsPublic(false);
            permissionData.setApiIsSign(false);
        }
        return permissionData;
    }

    /**
     * 生成权限编码
     * 1. 如果@Permission注解的code(permissionCode)为空，就取方法名(methodName)
     * 2. 如果请求方式是多个，则在权限码后追加请求方式
     * 3. 如果controller路径或者method路径大于1，则在权限码后面追加索引
     *
     * @param permissionCode      @Permission的code字段的值
     * @param methodName          方法名
     * @param requestMethodString 请求方式
     * @param index               索引
     * @param requestMethodLen    请求方法的长度
     * @param controllerPathLen   controller路径长度
     * @param methodPathLen       方法路径长度
     * @return 权限编码
     */
    private String generatePermissionCode(String permissionCode, String methodName, String requestMethodString,
                                          int index, int requestMethodLen, int controllerPathLen, int methodPathLen) {
        String middleLine = "-";
        // 1. 如果@Permission注解的code(permissionCode)为空，就取方法名(methodName)
        permissionCode = StringUtils.defaultIfBlank(permissionCode, methodName);

        if (requestMethodLen > 1) {
            // 2. 如果请求方式是多个，则在权限码后追加请求方式
            permissionCode += middleLine + requestMethodString;
        }

        if (controllerPathLen > 1 || methodPathLen > 1) {
            // 3. 如果controller路径或者method路径大于1，则在权限码后面追加索引
            permissionCode += middleLine + index;
        }

        return permissionCode;
    }

    /**
     * 连接两个路径
     *
     * @param controllerPath 控制器路径
     * @param methodPath     方法路径
     * @return 连接的结果路径
     */
    private String concatPath(String controllerPath, String methodPath) {
        String slash = "/";
        String path = "";
        if (StringUtils.isNotBlank(controllerPath)) {
            path += controllerPath;
        }
        if (!path.startsWith(slash)) {
            path = slash + path;
        }

        if (StringUtils.isNotBlank(methodPath)) {
            if (path.endsWith(slash) && methodPath.startsWith(slash)) {
                path += methodPath.substring(1);
            } else if (path.endsWith(slash) || methodPath.startsWith(slash)) {
                path += methodPath;
            } else {
                path += slash + methodPath;
            }
        }

        return path;
    }


    /**
     * 获取ResourceCode
     *
     * @param apiAnnotation  Api注解对象
     * @param controllerName controller名称
     * @return resourceCode
     */
    private String getResourceCode(Api apiAnnotation, String controllerName) {
        String[] tags = null;
        if (Objects.nonNull(apiAnnotation)) {
            tags = apiAnnotation.tags();
        }
        boolean hasNotTags = ArrayUtils.isEmpty(tags) || (tags.length == 1 && StringUtils.isBlank(tags[0]));
        if (hasNotTags) {
            tags = new String[]{FieldNameUtils.camel2MiddleLine(controllerName, false)};
        }
        Assert.notNull(tags, "tags must not be null");
        String resourceCode = null;
        for (String tag : tags) {
            if (tag.endsWith(SUFFIX_CONTROLLER)) {
                resourceCode = tag.substring(0, tag.length() - SUFFIX_CONTROLLER.length());
            } else {
                resourceCode = tag.replace(" ", "-")
                        .replaceAll("\\(", "-")
                        .replaceAll("\\)", "")
                        .replaceAll("-+", "-")
                        .toLowerCase();
            }
        }

        return resourceCode;
    }
}
