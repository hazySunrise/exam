package cc.darhao.undertow_demo.web.ws.handler;

import cc.darhao.undertow_demo.util.ResultFactory;
import cc.darhao.undertow_demo.util.ResultFactory.Result;
import cc.darhao.undertow_demo.web.ws.container.SessionBox;

import javax.websocket.Session;

public class LoginHandler {

    public Result login(Session session, String name) {
        //如果SessionBox中存在该Session，则提示重复登录
        if(SessionBox.getNameBySession(session) != null) {
            return ResultFactory.failed(210,"请不要重复登录");
        }
        SessionBox.addSession(name, session);
        //广播其他用户
        SessionBox.send(name,"\""+name+"\"加入群聊");
        System.out.println("\""+name+"\"加入群聊");
        return ResultFactory.succeed("登录成功");
    }

}
