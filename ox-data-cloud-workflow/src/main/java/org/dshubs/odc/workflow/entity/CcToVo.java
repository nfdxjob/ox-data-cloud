package org.dshubs.odc.workflow.entity;

import lombok.Data;

/**
 * @author guodong
 */
@Data
public class CcToVo {
    private String userId;
    private String userName;

    @Override
    public String toString(){
        return String.format("%s[%s]",this.userName,this.userId);
    }
}
