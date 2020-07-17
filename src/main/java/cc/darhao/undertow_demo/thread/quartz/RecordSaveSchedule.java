package cc.darhao.undertow_demo.thread.quartz;

import cc.darhao.undertow_demo.constant.SystemProperties;
import cc.darhao.undertow_demo.util.PropUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RecordSaveSchedule{

    private static Scheduler sched;
    private static String cron = PropUtil.getString(SystemProperties.FILE_NAME, SystemProperties.QUARTZ_CRON);

    public static void start(){
        try {
            //创建StatisticTimerTask的定时任务
            ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleWithFixedDelay(()->{

            },1,1, TimeUnit.MINUTES);
            timer.shutdown();
            timer.awaitTermination(1,TimeUnit.DAYS);

            JobDetail jobDetail = JobBuilder.newJob(RecordSaveTask.class).withIdentity("job-", "console-statistic").build();
            //目标 创建任务计划
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger-", "undertow-chat")
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            SchedulerFactory sfact = new StdSchedulerFactory();
            sched = sfact.getScheduler();
            //将任务及其触发器放入调度器
            sched.scheduleJob(jobDetail, trigger);
            //调度器开始调度任务
            sched.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //停止
    public static void stop() {
        try {
            sched.shutdown();
        } catch (Exception e){
           e.printStackTrace();
        }
    }

}
