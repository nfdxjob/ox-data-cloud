package ${packageName}.controller;
import ${packageName}.entity.${className};
import ${packageName}.vo.PageData;
import ${packageName}.app.service.${className}Service;
import ${packageName}.infra.util.IErrorCode;
import ${packageName}.infra.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ${tableComment}API访问
 *
 * @author ${author} ${date}
 */
@RestController
@RequestMapping("/api/v1/${tableName}s")
@Api(tags = "${tableComment}API")
@Slf4j
public class ${className}Controller {
    private final ${className}Service ${lowerClassName}Service;

    public ${className}Controller(${className}Service ${lowerClassName}Service) {
      this.${lowerClassName}Service = ${lowerClassName}Service;
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public JsonResult<${className}> findById(@PathVariable("id") Long ${lowerClassName}Id) {
     return JsonResult.ok(${lowerClassName}Service.selectById(${lowerClassName}Id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public JsonResult<IErrorCode> deleteById(@PathVariable("id") Long ${lowerClassName}Id) {
      ${lowerClassName}Service.deleteById(${lowerClassName}Id);
      return JsonResult.ok();
    }

   @PostMapping
   @ApiOperation("新增")
   public JsonResult<${className}> insert(@RequestBody @Validated ${className} ${lowerClassName}) {
      log.info("新增,参数:{}", ${lowerClassName});
      ${className} result = ${lowerClassName}Service.insert(${lowerClassName});
      return JsonResult.ok(result);
   }
}
