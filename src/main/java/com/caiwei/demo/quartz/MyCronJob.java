package com.caiwei.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @ClassName: MyCronJob
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/3 02:33
 */
public class MyCronJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务执行了" + new Date());
        // indexController.testMail();
    }
}
