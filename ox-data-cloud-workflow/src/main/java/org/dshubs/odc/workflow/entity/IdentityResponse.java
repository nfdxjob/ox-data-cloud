package org.dshubs.odc.workflow.entity;

import lombok.Data;

@Data
public class IdentityResponse {
    private String identityId;
    private String identityType;
    private String identityName;
}
