package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.FileEditLog;
import org.dshubs.odc.mybatis.app.service.IBaseService;


/**
 * 文件更改版本表逻辑控制层
 *
 * @author Mr.zhou 2022-04-11
 */
public interface FileEditLogService extends IBaseService<FileEditLog>  {

    /**
     * 查询指定版本的文件信息
     * @param fileResourceId fileId
     * @param fileVersion 文件版本
     * @return 文件信息
     */
    FileEditLog queryByVersion(Long fileResourceId, String fileVersion);
}

