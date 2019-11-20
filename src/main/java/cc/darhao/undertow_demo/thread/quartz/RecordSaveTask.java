package cc.darhao.undertow_demo.thread.quartz;


import cc.darhao.undertow_demo.constant.SystemProperties;
import cc.darhao.undertow_demo.redis.ChatRedisDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecordSaveTask implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        List<String> records = ChatRedisDao.get();
        if (records.size() != 0) {
            //写文件
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
            File dir = new File(SystemProperties.CHAT_RECORD_SAVE_PATH );
            if (!dir.exists()){
                dir.mkdirs();
            }
            File dest = new File(SystemProperties.CHAT_RECORD_SAVE_PATH + simpleDateFormat.format(new Date()) + ".txt");
            try (
                    FileWriter fw =new FileWriter(dest,true);
                    BufferedWriter bw = new BufferedWriter(fw)
            ){
                for (String str : records) {
                    bw.write(str + "\n");
                }
                ChatRedisDao.del();
            } catch(IOException e){
                System.out.println("写入文件出错" + e.getMessage());
            }
        }

    }

}
