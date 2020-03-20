package com.jimi.display.util.hik;

import java.util.List;

/**
 * 查询任务状态请求参数
 */
public class QueryTaskStatusPackage extends BaseRequestPackage {

    private List<String> taskCodes;

    QueryTaskStatusPackage(List<String> taskCodes) {
        super(CodeGenerate.generate());
        this.taskCodes = taskCodes;
    }

    public List<String> getTaskCodes() {
        return taskCodes;
    }

    public void setTaskCodes(List<String> taskCodes) {
        this.taskCodes = taskCodes;
    }


}
