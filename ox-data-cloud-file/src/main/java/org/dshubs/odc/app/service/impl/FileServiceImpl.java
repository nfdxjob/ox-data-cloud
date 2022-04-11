package org.dshubs.odc.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileAbstractService;
import org.dshubs.odc.app.service.FileResourceService;
import org.dshubs.odc.app.service.FileService;
import org.dshubs.odc.app.service.FileServiceFactory;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileServiceFactory fileServiceFactory;
    private final FileResourceService fileResourceService;

    public FileServiceImpl(FileServiceFactory fileServiceFactory, FileResourceService fileResourceService) {
        this.fileServiceFactory = fileServiceFactory;
        this.fileResourceService = fileResourceService;
    }

    @Override
    public FileInfoVO uploadFile(MultipartFile file, String bucket, String directory, String fileName) throws Exception {
        FileAbstractService fileService = fileServiceFactory.build(null);
        return fileService.upload(file, bucket, directory, fileName);
    }

    @Override
    public FileInfoVO uploadFileWithMd5(MultipartFile file, String bucket, String directory, String fileName) throws Exception {
        FileResource fileResource = fileResourceService.queryByMd5(DigestUtils.md5DigestAsHex(file.getInputStream()));
        if (fileResource != null) {
            return new FileInfoVO().setFileKey(fileResource.getFileKey()).setFileName(fileResource.getFileName());
        }
        return uploadFile(file, bucket, directory, fileName);
    }

    @Override
    public Map<String, String> getPostPolicy(String bucket) {
        return fileServiceFactory.build(null).postPolicy(bucket);
    }

    @Override
    public void download(String fileKey, HttpServletResponse response) throws Exception {
        FileResource fileResource = fileResourceService.queryByFileKey(fileKey);
        if (fileResource == null) {
            throw new CommonException(new Results.ErrorResult("500", "无效fileKey"));
        }
        FileAbstractService fileService = fileServiceFactory.build(fileResource.getStorageCode());
        fileService.download(fileResource.getBucketName(), fileResource.getFileKey(), fileResource.getFileName(), response);
    }

    @Override
    public void deleteByFileKey(String fileKey) {
        FileResource fileResource = fileResourceService.queryByFileKey(fileKey);
        if (fileResource == null) {
            throw new CommonException(new Results.ErrorResult("500", "无效fileKey"));
        }
        FileAbstractService fileService = fileServiceFactory.build(fileResource.getStorageCode());
        try {
            fileService.delete(fileResource.getBucketName(), fileResource.getFileKey());
        } catch (Exception e) {
            log.error("删除文件失败！");
            log.error(e.getMessage(), e);
        }
        fileResourceService.deleteById(fileResource.getFileResourceId());
    }

}
