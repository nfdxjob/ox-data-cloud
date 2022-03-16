package org.dshubs.odc.workflow.entity.query;

import lombok.Data;

/**
 * @author guodong
 */
@Data
public class ModelQueryVo extends BaseQueryVo{
    private String modelId;
    private String modelCategory;
    private String modelName;
    private String modelKey;
    private Integer modelVersion;
    private Boolean latestVersion = false;
    private Boolean deployed;
    private String deploymentId;
}
