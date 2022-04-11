package org.dshubs.odc.app.service;

import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/7
 **/
public interface FileService {

    /**
     * 上传文件
     * @param file 文件
     * @param bucket 桶名称
     * @param directory 目录
     * @param fileName 文件名
     * @throws Exception 异常
     * @return FileInfoVO 文件信息
     */
    FileInfoVO uploadFile(MultipartFile file, String bucket, String directory, String fileName) throws Exception;

    /**
     * 上传文件
     * @param file 文件
     * @param bucket 桶名称
     * @param directory 目录
     * @param fileName 文件名
     * @throws Exception 异常
     * @return FileInfoVO 文件信息
     */
    FileInfoVO uploadFileWithMd5(MultipartFile file, String bucket, String directory, String fileName) throws Exception;

    /**
     * 前端文件上传无需走内部服务器，直接走文件服务器（MinIO、OSS）
     * @param bucket 桶名称
     * @return PostPolicy 策略
     */
    Map<String, String> getPostPolicy(String bucket);

    /**
     * 根据fileKey下载
     * @param fileKey fileKey
     * @param response 响应
     * @throws Exception 异常
     */
    void download(String fileKey, HttpServletResponse response) throws Exception;

    /**
     * 根据fileKey删除文件
     *
     * @param fileKey fileKey
     */
    void deleteByFileKey(String fileKey);

}
