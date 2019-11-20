package cc.darhao.undertow_demo.redis;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

import java.util.List;


public class ChatRedisDao {
    private static Cache cache =  Redis.use();

    public synchronized static void save(String name,String message){
        cache.rpush("undertow_chat_record",name+":"+ message );
    }


    public synchronized static List<String> get(){
        List<String> messages = cache.lrange("undertow_chat_record",0,-1);
        return messages;
    }

    public synchronized static void del(){
        cache.del("undertow_chat_record");
    }


}
