package org.dshubs.odc.workflow.entity;

import lombok.Data;

/**
 * @author guodong
 * @date 2020年3月24日
 */
@Data
public class IdentityResponse {
    private String identityId;
    private String identityType;
    private String identityName;
}
