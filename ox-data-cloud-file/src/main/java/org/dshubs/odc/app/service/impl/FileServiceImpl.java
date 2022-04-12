package org.dshubs.odc.app.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.*;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileEditLog;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.dto.UpdateDTO;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileServiceFactory fileServiceFactory;
    private final FileResourceService fileResourceService;
    private final FileEditLogService fileEditLogService;

    public FileServiceImpl(FileServiceFactory fileServiceFactory, FileResourceService fileResourceService, FileEditLogService fileEditLogService) {
        this.fileServiceFactory = fileServiceFactory;
        this.fileResourceService = fileResourceService;
        this.fileEditLogService = fileEditLogService;
    }

    private FileResource existsFileKey(String fileKey) {
        FileResource fileResource = fileResourceService.queryByFileKey(fileKey);
        if (fileResource == null) {
            throw new CommonException(new Results.ErrorResult("500", "无效fileKey"));
        }
        return fileResource;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileInfoVO uploadFile(MultipartFile file, String bucket, String directory, String fileName) throws Exception {
        FileAbstractService fileAbstractService = fileServiceFactory.build(null);
        String realBucket = fileAbstractService.getRealBucket(bucket);
        String newFileName = StringUtils.isNotBlank(fileName) ? fileName : file.getOriginalFilename();
        String fileNamePre = fileAbstractService.getPreFileName();
        String uploadFileName = fileNamePre + newFileName;
        String fileKey = StringUtils.isBlank(directory) ? String.format("%s/%s", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), uploadFileName) : String.format("%s/%s", directory, uploadFileName);

        // 文件上传
        String fileVersion = fileAbstractService.upload(file, realBucket, fileKey);

        String url = String.format("%s/%s", fileAbstractService.getRealPreUrl(bucket), fileKey);
        FileResource fileResource = new FileResource()
                .setFileUrl(url)
                .setFileKey(fileKey)
                .setFileDirectory(directory)
                .setFileType(file.getContentType())
                .setFileName(newFileName)
                .setFileSize(file.getSize())
                .setBucketName(bucket)
                .setFileMd5(DigestUtils.md5DigestAsHex(file.getInputStream()))
                .setStorageCode(fileAbstractService.fileStorageConfig.getStorageType());

        // 文件表插入数据
        FileResource insertFileResource = fileResourceService.insert(fileResource);
        FileEditLog fileEditLog = new FileEditLog();
        BeanUtils.copyProperties(insertFileResource, fileEditLog);

        // 文件版本插入数据
        fileEditLog.setFileVersion(fileVersion);
        fileEditLogService.insert(fileEditLog);

        return new FileInfoVO().setFileResourceId(insertFileResource.getFileResourceId()).setFileKey(fileKey).setFileName(fileResource.getFileName()).setFileVersion(fileVersion);
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
    public FileInfoVO updateByFileKey(UpdateDTO updateDTO) {
        FileResource fileResource = this.existsFileKey(updateDTO.getFileKey());
        return fileServiceFactory.build(fileResource.getStorageCode()).update(fileResource, updateDTO.getFile(), fileResource.getBucketName(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    @Override
    public void deleteByFileKey(String fileKey) {
        FileResource fileResource = existsFileKey(fileKey);
        try {
            fileServiceFactory.build(fileResource.getStorageCode()).delete(fileResource.getBucketName(), fileResource.getFileKey());
        } catch (Exception e) {
            log.error("删除文件失败！");
            log.error(e.getMessage(), e);
        }
        fileResourceService.deleteById(fileResource.getFileResourceId());
    }

    @Override
    public void download(String fileKey, HttpServletResponse response) {
        FileResource fileResource = this.existsFileKey(fileKey);
        fileServiceFactory.build(fileResource.getStorageCode()).download(fileResource.getBucketName(), fileResource.getFileKey(), fileResource.getFileName(), response);
    }

    @Override
    public void download(Long fileResourceId, String fileVersion, HttpServletResponse response) {
        FileEditLog fileEditLog = fileEditLogService.queryByVersion(fileResourceId, fileVersion);
        Assert.notNull(fileEditLog, "不存在该文件版本");
        fileServiceFactory.build(fileEditLog.getStorageCode()).download(fileEditLog.getBucketName(), fileEditLog.getFileKey(), fileEditLog.getFileName(), response);
    }
}
