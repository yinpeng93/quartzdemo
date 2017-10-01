package com.yp;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzServer {
    public static void main(String[] args) {
        QuartzServer server = new QuartzServer();
        try {
            server.startScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void startScheduler() throws SchedulerException{
        //使用工厂创建调度器实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //以Job实现类创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("fkJob").build();
        //创建Trigger对象，该对象代表一个简单的调度器。
        //指定该任务被重复调度了50次，每次间隔60秒
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey("fkTrigger","fkTriggerGroup"))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever()).startNow()
                .build();
        //调度器将作业与trigger关联起来
        scheduler.scheduleJob(jobDetail,trigger);
        //开始调度
        scheduler.start();
    }
}
