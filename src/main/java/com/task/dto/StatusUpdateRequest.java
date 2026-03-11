package com.task.dto;

import com.taskapi.model.TaskStatus;

public class StatusUpdateRequest {

    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}