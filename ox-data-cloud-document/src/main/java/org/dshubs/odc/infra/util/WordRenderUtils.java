package org.dshubs.odc.infra.util;

import com.deepoove.poi.XWPFTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Word动态渲染工具
 *
 * @author create by wangxian 2021/12/29
 */
public class WordRenderUtils {

    private WordRenderUtils(){

    }

    public static void render(InputStream template, Map<String,String> params,OutputStream outputStream) throws IOException {
        XWPFTemplate xwpfTemplate = XWPFTemplate.compile(template).render(params);
        xwpfTemplate.write(outputStream);
    }

}
