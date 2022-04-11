package org.dshubs.odc.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Data
public class UpdateDTO {

    private String fileKey;

    private MultipartFile file;
}
