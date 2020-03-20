package com.jimi.display.util.hik;

import com.alibaba.fastjson.JSONObject;


import java.util.List;


/**
 * 该类用于通过任务编号查询任务当前执行状态，支持批量查询
 * 该类所有方法的参数，没有特别说明的情况下，都不能为空，否则会抛出{@link NullPointerException}<br>
 * 线程安全级别：安全
 * Date: Created in 2019/7/12 09:15
 * Author: ct
 */
public class QueryTaskStatusSender {

    /**
     * 查询任务状态
     * @param url 访问RCS-2000的完整路径
     * @param taskCodes 任务编号数组，如["123","234"]
     * @return  服务器应答结果：Json字符串
     * @throws Exception 如果连接失败抛出
     */
    public static String request(String url,List<String> taskCodes) throws Exception{
        if (url == null || taskCodes == null || taskCodes.size() == 0){
            throw new NullPointerException("参数不能为null");
        }
        QueryTaskStatusPackage query = new QueryTaskStatusPackage(taskCodes);
        return HttpPost.send(url, JSONObject.toJSONString(query));
    }
}
