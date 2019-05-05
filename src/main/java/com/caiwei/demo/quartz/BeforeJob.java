package com.caiwei.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ClassName: BeforeJob
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/3 02:43
 */
public class BeforeJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("前端请求的调度程序");
    }
}
