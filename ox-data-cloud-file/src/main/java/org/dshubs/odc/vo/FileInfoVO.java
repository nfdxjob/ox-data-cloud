package org.dshubs.odc.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Data
@Accessors(chain = true)
public class FileInfoVO {

    private String fileKey;
    private String fileName;
}
