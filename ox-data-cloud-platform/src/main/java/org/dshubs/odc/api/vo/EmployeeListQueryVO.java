package org.dshubs.odc.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author create by wangxian 2022/3/2
 */
@Data
public class EmployeeListQueryVO {
    private List<String> orgCodes;
}
