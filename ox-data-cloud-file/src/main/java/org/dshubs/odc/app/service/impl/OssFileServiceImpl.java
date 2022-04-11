package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.app.service.FileAbstractService;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Service
public class OssFileServiceImpl extends FileAbstractService {

    @Override
    public FileInfoVO upload(MultipartFile file, String bucket, String directory, String fileName) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> postPolicy(String bucket) {
        return null;
    }

    @Override
    public void download(String bucket, String fileKey, String fileName, HttpServletResponse response) {

    }

    @Override
    public FileInfoVO update(FileResource fileResource, MultipartFile file, String bucketName, String directory) {
        return null;
    }

    @Override
    public void delete(String bucketName, String fileKey) throws Exception {

    }
}
