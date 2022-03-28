package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskUpdateRequest {
    private String id;
    private String name;
    private String assignee;
    private String owner;
    private Date dueDate;
    private String category;
    private String description;
    private int priority;
}
