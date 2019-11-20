package cc.darhao.undertow_demo.config;

import cc.darhao.undertow_demo.constant.SystemProperties;
import cc.darhao.undertow_demo.util.PropUtil;
import cc.darhao.undertow_demo.util.VisualSerializer;
import com.jfinal.plugin.redis.RedisPlugin;


public class DataSourceConfig {
    private static RedisPlugin rp;


    public void shutdown() {
        rp.stop();
    }

    public void start() {
        String redisIp = PropUtil.getString(SystemProperties.FILE_NAME, SystemProperties.REDIS_IP);
        String redisPassword = PropUtil.getString(SystemProperties.FILE_NAME, SystemProperties.REDIS_PASSWORD);
        rp = new RedisPlugin("chat", redisIp, redisPassword);
        rp.setSerializer(new VisualSerializer());//设置可视化序列化器

        rp.start();
    }


}
