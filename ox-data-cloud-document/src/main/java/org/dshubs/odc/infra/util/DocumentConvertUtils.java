package org.dshubs.odc.infra.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * 文档转换工具类
 * @author create by wangxian 2021/12/29
 */
@Slf4j
public class DocumentConvertUtils {

    private DocumentConvertUtils(){}

    public static void docToPdf(InputStream word, OutputStream pdf) {
        License license = new License();
        try {
            license.setLicense(DocumentConvertUtils.class.getClassLoader().getResourceAsStream("license.xml"));
            Document template = new Document(word);
            template.save(pdf, SaveFormat.PDF);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void docToPng(InputStream word, OutputStream png){
        License license = new License();
        try {
            license.setLicense(DocumentConvertUtils.class.getClassLoader().getResourceAsStream("license.xml"));
            Document template = new Document(word);
            template.save(png, SaveFormat.PNG);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
