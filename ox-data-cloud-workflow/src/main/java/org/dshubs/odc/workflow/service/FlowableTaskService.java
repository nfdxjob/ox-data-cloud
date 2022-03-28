package org.dshubs.odc.workflow.service;

import org.dshubs.odc.workflow.common.CommentTypeEnum;
import org.dshubs.odc.workflow.entity.*;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;

import java.util.List;

/**
 * Flowable 流程任务Service
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public interface FlowableTaskService {

    /**
     * 查询任务详情
     *
     * @param taskId 任务ID
     * @return TaskResponse
     */
    TaskResponse getTask(String taskId);

    /**
     * 查询子任务列表
     *
     * @param taskId 任务ID
     * @return List<TaskResponse>
     */
    List<TaskResponse> getSubTasks(String taskId);

    /**
     * 修改任务
     *
     * @param taskUpdateRequest request params
     * @return TaskResponse
     */
    TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest);

    /**
     * 转办任务
     *
     * @param taskRequest request params
     */
    void assignTask(TaskRequest taskRequest);

    /**
     * 新增任务参与人
     *
     * @param taskId        任务ID
     * @param involveUserId 校验用户Id
     */
    void involveUser(String taskId, String involveUserId);

    /**
     * 移除任务参与人
     *
     * @param taskId        任务ID
     * @param involveUserId 参与用户ID
     */
    void removeInvolvedUser(String taskId, String involveUserId);

    /**
     * 认领任务
     *
     * @param taskRequest params
     */
    void claimTask(TaskRequest taskRequest);

    /**
     * 取消认领
     *
     * @param taskRequest request params
     */
    void unclaimTask(TaskRequest taskRequest);

    /**
     * 新增任务关联人
     *
     * @param task     任务
     * @param userId   用户ID
     * @param linkType 连线类型
     */
    void addIdentiyLinkForUser(Task task, String userId, String linkType);

    /**
     * 委派任务
     *
     * @param taskRequest task request params
     */
    void delegateTask(TaskRequest taskRequest);

    /**
     * 完成任务
     *
     * @param taskRequest task request params
     */
    void completeTask(TaskRequest taskRequest);

    /**
     * 删除任务
     *
     * @param taskId task id
     */
    void deleteTask(String taskId);

    /**
     * 终止流程
     *
     * @param taskRequest task params
     */
    void stopProcessInstance(TaskRequest taskRequest);

    /**
     * 查询可退回节点
     *
     * @param taskId task id
     * @return collection flowNode response
     */
    List<FlowNodeResponse> getBackNodes(String taskId);

    /**
     * 退回任务
     *
     * @param taskRequest task request
     */
    void backTask(TaskRequest taskRequest);

    /**
     * 查询单一任务详情
     *
     * @param taskId task id
     * @return task entity
     */
    Task getTaskNotNull(String taskId);

    /**
     * 查询单一历史任务详情
     *
     * @param taskId task id
     * @return history task instance
     */
    HistoricTaskInstance getHistoricTaskInstanceNotNull(String taskId);

    /**
     * 新增过程意见
     *
     * @param taskId            task id
     * @param processInstanceId process instance id
     * @param userId            用户ID
     * @param type              类型
     * @param message           messag
     */
    void addComment(String taskId, String processInstanceId, String userId, CommentTypeEnum type, String message);

    /**
     * 查询过程意见
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param type              类型
     * @param userId            用户ID
     * @return List comments
     */
    List<Comment> getComments(String taskId, String processInstanceId, String type, String userId);

    /**
     * 新增任务关联信息
     *
     * @param taskIdentityRequest task identify request body
     */
    void saveTaskIdentityLink(IdentityRequest taskIdentityRequest);

    /**
     * 删除任务关联信息
     *
     * @param taskId       任务ID
     * @param identityId   关联ID
     * @param identityType 关联类型
     */
    void deleteTaskIdentityLink(String taskId, String identityId, String identityType);

    /**
     * 任务已阅
     *
     * @param taskRequest task request body
     */
    void readTask(TaskRequest taskRequest);

    /**
     * 我的待办
     *
     * @return List task
     */
    List<Task> myTodoList();
}
