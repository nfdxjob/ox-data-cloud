package ${packageName}.app.service.impl;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import ${packageName}.app.service.${className}Service;
import  ${packageName}.infra.mapper.${className}Mapper;
import  ${packageName}.domain.entity.${className};
import org.springframework.stereotype.Service;


/**
 * ${tableComment}逻辑控制层
 *
 * @author ${author} ${date}
 */
@Service
public class ${className}Service extends BaseServiceImpl<${className}Mapper,${className}> implements ${className}Service {

}

