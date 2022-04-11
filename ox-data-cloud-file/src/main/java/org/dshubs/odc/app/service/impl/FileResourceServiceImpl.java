package org.dshubs.odc.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.FileResourceService;
import org.dshubs.odc.infra.mapper.FileResourceMapper;
import org.dshubs.odc.domain.entity.FileResource;
import org.springframework.stereotype.Service;


/**
 * 文件资源逻辑控制层
 *
 * @author zhou 2022-04-06
 */
@Service
public class FileResourceServiceImpl extends BaseServiceImpl<FileResourceMapper,FileResource> implements FileResourceService {

    @Override
    public FileResource queryByFileKey(String fileKey) {
        return this.baseMapper.selectOne(new QueryWrapper<FileResource>().eq("file_key", fileKey));
    }

    @Override
    public FileResource queryByMd5(String md5) {
        return this.baseMapper.selectOne(new QueryWrapper<FileResource>().eq("file_md5", md5));
    }
}

