package org.dshubs.odc.api.controller.v1;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dshubs.odc.api.vo.GenerateCodeVO;
import org.dshubs.odc.api.vo.PageData;
import org.dshubs.odc.api.vo.TableInfoGenerateQuery;
import org.dshubs.odc.domain.entity.generate.TableInfo;
import org.dshubs.odc.infra.util.JsonResult;
import org.dshubs.odc.service.generate.TableInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangxian 2022/02/24
 */
@Controller
@RequestMapping("/generate")
@Slf4j
public class CodeGenerateController {
    private final TableInfoService tableInfoService;

    public CodeGenerateController(TableInfoService tableInfoService) {
        this.tableInfoService = tableInfoService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/tables/page")
    @ApiOperation("分页获取所有表")
    @ResponseBody
    public JsonResult<PageData<TableInfo>> pageQueryTable(TableInfoGenerateQuery query) {
        return JsonResult.ok(tableInfoService.pageQuery(query));
    }

    @PostMapping("/code/zip")
    @ApiOperation("生成代码,下载压缩包")
    public void generate(@RequestBody GenerateCodeVO generateCode, HttpServletResponse response) throws IOException {
        log.info("生成代码,request body:{}", generateCode);
        byte[] data = tableInfoService.generateCode(generateCode);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
