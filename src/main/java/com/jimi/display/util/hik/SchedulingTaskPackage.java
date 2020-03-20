package com.jimi.display.util.hik;

import java.util.List;

/**
 * 生成agv调度任务单请求参数
 */
public class SchedulingTaskPackage extends BaseRequestPackage {

    private String taskTyp;

    private String userCallCode;

    private  List<String> userCallCodePath;

    private String podCode;

    private String podDir;

    private String podType;

    private String materialLot;

    private String priority;

    private String robotCode;

    private String taskCode;

    private String data;

    SchedulingTaskPackage(String taskTyp, String userCallCode, List<String> userCallCodePath ) {
        super(CodeGenerate.generate());
        this.taskTyp = taskTyp;
        this.userCallCode = userCallCode;
        this.userCallCodePath = userCallCodePath;
    }

   SchedulingTaskPackage(String taskType, String userCallCode, List<String> userCallCodePath, String podCode, String podDir, String podType, String materialLot, String priority, String robotCode, String taskCode, String data) {
        super(CodeGenerate.generate());
        this.taskTyp = taskType;
        this.userCallCode = userCallCode;
        this.userCallCodePath = userCallCodePath;
        this.podCode = podCode;
        this.podDir = podDir;
        this.podType = podType;
        this.materialLot = materialLot;
        this.priority = priority;
        this.robotCode = robotCode;
        this.taskCode = taskCode;
        this.data = data;
    }

    public String getTaskTyp() {
        return taskTyp;
    }

    public void setTaskTyp(String taskTyp) {
        this.taskTyp = taskTyp;
    }

    public String getUserCallCode() {
        return userCallCode;
    }

    public void setUserCallCode(String userCallCode) {
        this.userCallCode = userCallCode;
    }

    public List<String> getUserCallCodePath() {
        return userCallCodePath;
    }

    public void setUserCallCodePath(List<String> userCallCodePath) {
        this.userCallCodePath = userCallCodePath;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodDir() {
        return podDir;
    }

    public void setPodDir(String podDir) {
        this.podDir = podDir;
    }

    public String getPodType() {
        return podType;
    }

    public void setPodType(String podType) {
        this.podType = podType;
    }

    public String getMaterialLot() {
        return materialLot;
    }

    public void setMaterialLot(String materialLot) {
        this.materialLot = materialLot;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
