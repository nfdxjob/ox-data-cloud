package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private String category;
    private String categoryName;
    private List<ProcessDefinitionVo> processDefinitionVoList;
}
