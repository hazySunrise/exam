package com.jimi.display.controller;


import cc.darhao.dautils.api.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jimi.display.util.hik.SchedulingTaskSender;
import com.jimi.display.util.IniReader;
import com.jimi.display.util.hik.QueryTaskStatusSender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.*;


public class TaskController implements Initializable {

    private static String schedulingTask_url ;
    private static String queryTaskStatus_url;
    private Logger logger = LogManager.getRootLogger();

    @FXML
    private TextArea responseTa;

    @FXML
    private TextField startTf;

    @FXML
    private TextField endTf;

    @FXML
    private Label tipLb;

    // 起始位置
    private String start;
    // 结束位置
    private String end;
    private Stage stage;
    private  static String statusMsg;
    private Timer timer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * 确定按钮
     */
    public void onSendClick(){
        info("");
        start = startTf.getText();
        end = endTf.getText();
        if (  start == null || end == null || start.equals("") || end.equals("")){
            error("提示：参数不能为空");
        }else {
            taskSender(start,end);
        }
    }


    /**
     * 重置按钮
     */
    public void onResetClick(){
        stopTimer();
        startTf.setText("");
        endTf.setText("");
        responseTa.setText("");
        info("");
    }


    /**
     * 发送任务指令
     */
    public void taskSender(String start,String end) {
        statusMsg = null;
        //关闭定时器
        stopTimer();
        Map<String, String> urlMap = IniReader.getItem("config.ini", "url");
        //获取访问RCS-2000生成任务调度单的路径
        schedulingTask_url = urlMap.get("schedulingTask_url");
        //向服务器发送生成任务单指令
        String taskRep = null;
        try {
           taskRep = SchedulingTaskSender.request(schedulingTask_url,"test",start,end);
        }catch (Exception e){
            error("提示：连接异常");
        }
        //获取返回结果并解析
        if (taskRep != null){
            //显示服务器返回信息
            responseTa.setText("[" + DateUtil.yyyyMMddHHmmss(new Date()) + "]:  " + JSON.parseObject(taskRep).getString("message"));
            //获取任务编号
            String taskData = JSON.parseObject(taskRep).getString("data");
            //定时查询任务状态
            startTimer(taskData);
        }
    }


    /**
     * 查询任务状态
     */
    public void queryStatus(String taskData){
        Map<String, String> urlMap = IniReader.getItem("config.ini", "url");
        //获取访问RCS-2000查询任务状态的路径
        queryTaskStatus_url = urlMap.get("queryTaskStatus_url");
        List<String> taskCodes =new ArrayList<>();
        taskCodes.add(taskData);
        String statusRep = null;
        try {
             statusRep = QueryTaskStatusSender.request(queryTaskStatus_url,taskCodes);
        }catch (Exception e){
            error("提示：连接异常");
        }
        if (statusRep != null){
            String statusData = JSON.parseObject(statusRep).getString("data");
            //获取任务状态
            JSONArray jsonArray = JSONArray.parseArray(statusData);
            JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(0).toString());
            String message = jsonObject.getString("taskStatus");
            System.out.println(message);
           if (statusMsg == null || !statusMsg.equals(message)){
               statusMsg = message;
               responseTa.appendText("   [" + DateUtil.yyyyMMddHHmmss(new Date()) + "]:  " + message);
           }
        }
    }


    /**
     * 启动定时器定时查询任务状态
     * @param taskData
     */
    private void startTimer(String taskData){
        timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                queryStatus(taskData);
            }
        }, 2000,3000);
    }


    /**
     * 关闭定时器
     *
     */
    private void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /**
     * 提示错误信息
     */
    public void error(String message) {
        responseTa.setText("");
        tipLb.setTextFill(Color.RED);
        tipLb.setText(message);
        logger.error(message);
    }


    /**
     * 提示基本信息
     */
    public void info(String message) {
        tipLb.setTextFill(Color.BLACK);
        tipLb.setText(message);
        logger.info(message);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
