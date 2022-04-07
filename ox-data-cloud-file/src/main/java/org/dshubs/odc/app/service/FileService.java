package org.dshubs.odc.app.service;

import org.dshubs.odc.dto.DownloadDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.zhou 2022/4/7
 **/
public interface FileService {

    /**
     * 上传文件
     * @param file 文件
     * @param bucket 桶名称
     * @param fileName 文件名
     * @throws Exception 异常
     */
    String upload(MultipartFile file, String bucket, String fileName) throws Exception ;

    void download(DownloadDTO downloadDTO, HttpServletResponse response) throws Exception;
}
