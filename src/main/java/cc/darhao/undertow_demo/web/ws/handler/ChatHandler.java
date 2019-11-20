package cc.darhao.undertow_demo.web.ws.handler;

import cc.darhao.undertow_demo.redis.ChatRedisDao;
import cc.darhao.undertow_demo.util.ResultFactory;
import cc.darhao.undertow_demo.util.ResultFactory.Result;
import cc.darhao.undertow_demo.web.ws.container.SessionBox;

import javax.websocket.Session;

public class ChatHandler {

    public Result chat(Session session, String message) {
       String name = SessionBox.getNameBySession(session);
        if(name == null) {
            return ResultFactory.failed(211,"请先登录");
        }
        //聊天记录用Redis保存
        ChatRedisDao.save(name,message);
        //广播其他用户
        SessionBox.send(name,name+":"+message);
        System.out.println(name+":"+message);
        return ResultFactory.succeed("发送成功");
    }
}
