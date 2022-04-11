package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.mybatis.app.service.IBaseService;


/**
 * 文件资源逻辑控制层
 *
 * @author zhou 2022-04-06
 */
public interface FileResourceService extends IBaseService<FileResource>  {

    /**
     * 根据fileKey获取文件信息
     *
     * @param fileKey fileKey
     * @return 文件信息
     */
    FileResource queryByFileKey(String fileKey);

    /**
     * 根据md5值查询文件信息
     *
     * @param md5 md5值
     * @return 文件信息
     */
    FileResource queryByMd5(String md5);
}

