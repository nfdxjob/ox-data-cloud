package org.dshubs.odc.workflow.common.model;

import lombok.Data;
import org.flowable.bpmn.model.MultiInstanceLoopCharacteristics;

import java.util.ArrayList;
import java.util.List;

/**
 * 湖南牛数用户任务
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@Data
public class HnnsUserTask {
    private String id;
    private String name;
    private String documentation;
    private String assignee;
    private String owner;
    private String formKey;
    private String dueDate;
    private String category;
    private List<String> candidateUsers = new ArrayList<>();
    private List<String> candidateGroups = new ArrayList<>();
    private String skipExpression;
    private boolean asynchronous;
    private MultiInstanceLoopCharacteristics loopCharacteristics;
    private String buttons;
}
