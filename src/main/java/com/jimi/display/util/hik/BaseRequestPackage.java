package com.jimi.display.util.hik;

/**
 * 基础请求参数
 */
public class BaseRequestPackage {

    private String reqCode;

    private String reqTime;

    private String clientCode;

    private String tokenCode;

    BaseRequestPackage(String reqCode){
        this.reqCode = reqCode;
    }

    BaseRequestPackage(String reqCode, String reqTime, String clientCode, String tokenCode) {
        this.reqCode = reqCode;
        this.reqTime = reqTime;
        this.clientCode = clientCode;
        this.tokenCode = tokenCode;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }
}
