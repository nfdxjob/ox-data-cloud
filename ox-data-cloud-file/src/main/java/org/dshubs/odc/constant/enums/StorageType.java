package org.dshubs.odc.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 储存类型
 *
 * @author Mr.zhou 2022/4/6
 **/
@RequiredArgsConstructor
@Getter
public enum StorageType {

    /**
     * 阿里云
     */
    ALIYUN("Aliyun"),
    /**
     * minio
     */
    MINIO("MinIO"),
    /**
     * 华为云
     */
    HUAWEI("Huawei"),
    /**
     * 腾讯云
     */
    TENCENT("Tencent"),
    /**
     * 七牛云
     */
    QINIU("Qiniu");

    private final String type;

}
