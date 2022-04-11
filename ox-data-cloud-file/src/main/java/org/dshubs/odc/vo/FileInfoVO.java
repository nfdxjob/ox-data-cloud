package org.dshubs.odc.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Data
@Accessors(chain = true)
public class FileInfoVO {

    /**
     * 文件id
     */
    private Long fileResourceId;
    /**
     * 文件fileKey
     */
    private String fileKey;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件版本
     */
    private String fileVersion;
}
