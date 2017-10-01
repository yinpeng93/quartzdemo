package com.yp;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 测试任务线程(任务类)
 */
public class TestJob implements Job {

    //判断作业是否执行的旗标
    private boolean isRunning = false;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (!isRunning) {
            System.out.println(new Date() + "  作业任务被调度。");
            for (int i = 0; i < 100; i++) {
                System.out.println("作业完成" + (i + 1) + "%");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(new Date() + "作业调度结束。");
        } else {
            System.out.println(new Date() + "任务退出");
        }
    }
}
