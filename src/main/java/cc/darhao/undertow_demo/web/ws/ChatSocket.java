package cc.darhao.undertow_demo.web.ws;

import cc.darhao.pasta.Pasta;
import cc.darhao.undertow_demo.web.ws.container.SessionBox;
import com.alibaba.fastjson.JSONException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 *  注意：<br>
 *  由于 JFinalFilter 会接管所有不带 "." 字符的 URL 请求<br>
 *  所以 @ServerEndpoint 注解中的 URL 参数值建议以 ".ws" 结尾，<br>
 *  否则请求会响应 404 找不到资源<br>
 * <br>
 * <b>2019年10月26日</b>
 * @author <a href="https://github.com/darhao">鲁智深</a>
 */
@ServerEndpoint("/chatServer.ws")
public class ChatSocket {


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("用户接入");
    }


    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            Pasta.receiveMessage(session, message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        SessionBox.removeSession(session);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Ws发生错误，信息为:"+error.getMessage());
    }


}