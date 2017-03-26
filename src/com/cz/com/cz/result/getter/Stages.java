package com.cz.com.cz.result.getter;

/**
 * Created by Lijie on 2017/2/13.
 * Modified by YE on 2017/3/20.
 */
public class Stages {

    private String stageId;
    private String attemptId;
    private String numCompleteTasks;
    private String name;
    private String taskUrl;
    private String duration;
    private String SubmitTime;
    private String CompleteTime;
    private String Status;

    public String getStageId() {
        return stageId;
    }
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public String getNumCompleteTasks() {
        return numCompleteTasks;
    }

    public void setNumCompleteTasks(String numCompleteTasks) {
        this.numCompleteTasks = numCompleteTasks;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSubmitTime() {
        return SubmitTime;
    }

    public void setSubmitTime(String submitTime) {
        SubmitTime = submitTime;
    }

    public String getCompleteTime() {
        return CompleteTime;
    }

    public void setCompleteTime(String completeTime) {
        CompleteTime = completeTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
