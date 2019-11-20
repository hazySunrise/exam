package cc.darhao.undertow_demo.web.ws.container;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 存放会话的容器
 * <br>
 * <b>2019年4月9日</b>
 * @author 几米物联自动化部-洪达浩
 */
public class SessionBox {
	
	private static final Map<String, Session> sessionMap = new HashMap<>();

	
	public static synchronized Session getSessionByName(String name) {
		return sessionMap.get(name);
	}
	
	
	public static synchronized String getNameBySession(Session session) {
		for (Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
			if(sessionEntry.getValue().equals(session)) {
				return sessionEntry.getKey();
			}
		}
		return null;
	}
	
	
	public static synchronized void addSession(String name, Session session) {
		sessionMap.put(name, session);
	}
	
	
	public static synchronized void removeSession(Session session) {
		sessionMap.remove(getNameBySession(session));
	}


	public static synchronized void send( String nickName,String message) {
		for (Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
			if (!sessionEntry.getKey().equals(nickName) && sessionEntry.getValue().isOpen()) {
				try {
					sessionEntry.getValue().getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
