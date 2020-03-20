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

    // ��ʼλ��
    private String start;
    // ����λ��
    private String end;
    private Stage stage;
    private  static String statusMsg;
    private Timer timer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * ȷ����ť
     */
    public void onSendClick(){
        info("");
        start = startTf.getText();
        end = endTf.getText();
        if (  start == null || end == null || start.equals("") || end.equals("")){
            error("��ʾ����������Ϊ��");
        }else {
            taskSender(start,end);
        }
    }


    /**
     * ���ð�ť
     */
    public void onResetClick(){
        stopTimer();
        startTf.setText("");
        endTf.setText("");
        responseTa.setText("");
        info("");
    }


    /**
     * ��������ָ��
     */
    public void taskSender(String start,String end) {
        statusMsg = null;
        //�رն�ʱ��
        stopTimer();
        Map<String, String> urlMap = IniReader.getItem("config.ini", "url");
        //��ȡ����RCS-2000����������ȵ���·��
        schedulingTask_url = urlMap.get("schedulingTask_url");
        //�������������������ָ��
        String taskRep = null;
        try {
           taskRep = SchedulingTaskSender.request(schedulingTask_url,"test",start,end);
        }catch (Exception e){
            error("��ʾ�������쳣");
        }
        //��ȡ���ؽ��������
        if (taskRep != null){
            //��ʾ������������Ϣ
            responseTa.setText("[" + DateUtil.yyyyMMddHHmmss(new Date()) + "]:  " + JSON.parseObject(taskRep).getString("message"));
            //��ȡ������
            String taskData = JSON.parseObject(taskRep).getString("data");
            //��ʱ��ѯ����״̬
            startTimer(taskData);
        }
    }


    /**
     * ��ѯ����״̬
     */
    public void queryStatus(String taskData){
        Map<String, String> urlMap = IniReader.getItem("config.ini", "url");
        //��ȡ����RCS-2000��ѯ����״̬��·��
        queryTaskStatus_url = urlMap.get("queryTaskStatus_url");
        List<String> taskCodes =new ArrayList<>();
        taskCodes.add(taskData);
        String statusRep = null;
        try {
             statusRep = QueryTaskStatusSender.request(queryTaskStatus_url,taskCodes);
        }catch (Exception e){
            error("��ʾ�������쳣");
        }
        if (statusRep != null){
            String statusData = JSON.parseObject(statusRep).getString("data");
            //��ȡ����״̬
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
     * ������ʱ����ʱ��ѯ����״̬
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
     * �رն�ʱ��
     *
     */
    private void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /**
     * ��ʾ������Ϣ
     */
    public void error(String message) {
        responseTa.setText("");
        tipLb.setTextFill(Color.RED);
        tipLb.setText(message);
        logger.error(message);
    }


    /**
     * ��ʾ������Ϣ
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
