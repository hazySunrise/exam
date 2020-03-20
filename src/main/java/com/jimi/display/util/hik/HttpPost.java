package com.jimi.display.util.hik;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 发送请求到服务器并获取服务器返回值
 */
class HttpPost {

    private static Logger LOGGER = LogManager.getLogger(HttpPost.class);

    /**
     * @param requestParam 请求参数
     * @param url 访问RCS-2000的完整路径
     * @return 服务器返回结果
     */
     static String send(String url, String requestParam) throws Exception{
        //发送并获取服务器返回值
        String resultMsg = post(url ,requestParam);
        resultMsg = new String(resultMsg.getBytes(), "UTF-8");
        return resultMsg;
    }


    private static String post(String url,String requestParamStr) throws Exception {
        try {
            PostMethod method = new PostMethod(url);
            method.addRequestHeader("Accept", "application/json");
            method.addRequestHeader("Content-Type", "application/json");
            RequestEntity req = new StringRequestEntity(requestParamStr ,"application/json" ,"UTF-8");
            method.setRequestEntity(req);
            int statusCode = new HttpClient().executeMethod(method);
            LOGGER.debug("statusCode：" + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("method faild," + method.getStatusLine());
            }
            String response = new String(method.getResponseBody());
            LOGGER.debug("post response:" + response);
            return response;
        } catch (Exception e) {
            LOGGER.error("post failed", e);
            throw new Exception("post failed", e);
        }
    }
}
