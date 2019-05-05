package com.caiwei.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: QuartzUtil
 * @Description: quartz工具类
 * @auther: caiwei
 * @date: 2019/5/3 02:41
 */
@Component
@Slf4j
public class QuartzUtil {

    private static String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";

    @Autowired
    Scheduler scheduler;

    //addSimpleJob简略版
    public boolean addSimpleJob(String jobName,Integer interval, TimeUnit timeUnit, Class<? extends Job> jobClass) {
        return addSimpleJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, interval, timeUnit,jobClass);
    }

    //addCronJob简略版
    public boolean addCronJob(String jobName, String cronExpression, Class<? extends Job> jobClass) {
        return addCronJob(jobName,JOB_GROUP_NAME,jobName, TRIGGER_GROUP_NAME, cronExpression, jobClass);
    }

    //modifySimpleJobTime简略版
    public boolean modifySimpleJobTime(String triggerName, Integer interval, TimeUnit timeUnit) {
        return modifySimpleJobTime(triggerName, TRIGGER_GROUP_NAME, interval, timeUnit);
    }

    //modifyCronJobTime简略版
    public boolean modifyCronJobTime(String triggerName, String cronExpression) {
        return modifyCronJobTime(triggerName, TRIGGER_GROUP_NAME, cronExpression);
    }

    //removeJob简略版
    public boolean removeJob(String jobName) {
        return removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
    }

    /**
     * @Title addSimpleJob
     * @Description: 添加简单定时任务
     * @params: [jobName, jobGroup, triggerName, triggerGroupInteger, interval, timeUnit, JobClass]
     * @return: boolean
     * @throws:
     * @author: caiwei
     * @date: 2019/5/5 21:08
     */
    public boolean addSimpleJob(String jobName, String jobGroup, String triggerName, String triggerGroup, Integer interval, TimeUnit timeUnit, Class<? extends Job> JobClass) {

        try {
            JobDetail jobDetail = JobBuilder
                    .newJob(JobClass)
                    .withIdentity(jobName, jobGroup)
                    .build();
            SimpleTrigger simpleTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerName, triggerGroup)
                    .withSchedule(getSimpleScheduleBuilder(interval, timeUnit))
                    .startNow()
                    .build();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Title addCronJob
     * @Description: 添加cron定时任务
     * @params: [jobName, jobGroup, triggerName, triggerGroup, cronExpression, JobClass]
     * @return: boolean
     * @throws:
     * @author: caiwei
     * @date: 2019/5/5 21:11
     */
    public boolean addCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cronExpression, Class<? extends Job> JobClass) {

        try {
            JobDetail jobDetail = JobBuilder
                    .newJob(JobClass)
                    .withIdentity(jobName, jobGroup)
                    .build();
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerName,triggerGroup)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Title modifySimpleJobTime
     * @Description: 修改simple任务的时间的触发时间
     * @params: [triggerName, triggerGroup, interval, timeUnit]
     * @return: boolean
     * @throws:
     * @author: caiwei
     * @date: 2019/5/5 21:39
     */
    public boolean modifySimpleJobTime(String triggerName,String triggerGroup, Integer interval, TimeUnit timeUnit){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
            SimpleTrigger oldTrigger = (SimpleTrigger) scheduler.getTrigger(triggerKey);
            if(oldTrigger == null){
                log.error("未找到相关任务");
                return false;
            }
            //unit:milliseconds
            Long oldInterval = oldTrigger.getRepeatInterval();
            if( !oldInterval.equals(getMilliseconds(interval,timeUnit)) ){
                SimpleTrigger simpleTrigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(triggerName, triggerGroup)
                        .withSchedule(getSimpleScheduleBuilder(interval, timeUnit))
                        .startNow()
                        .build();
                scheduler.rescheduleJob(triggerKey, simpleTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("修改简单定时任务间隔时间失败");
            return false;
        }
        return true;
    }

    /**
     * @Title modifyCronJob
     * @Description: 修改cron任务的时间的触发时间
     * @params: [triggerName, triggerGroup, cronExpression]
     * @return: boolean
     * @throws:
     * @author: caiwei
     * @date: 2019/5/5 21:42
     */
    public boolean modifyCronJobTime(String triggerName,String triggerGroup,String cronExpression){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
            CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(oldTrigger == null){
                log.error("未找到相关任务");
                return false;
            }
            String oldCronExpression = oldTrigger.getCronExpression();
            if( !oldCronExpression.equalsIgnoreCase(cronExpression) ){
                CronTrigger cronTrigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(triggerName,triggerGroup)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                        .build();
                scheduler.rescheduleJob(triggerKey, cronTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("修改cron定时任务时间失败");
        }
        return true;
    }

    /**
     * @Title removeJob
     * @Description: 删除指定定时任务
     * @params: [jobName, jobGroup, triggerName, triggerGroup]
     * @return: boolean
     * @throws:
     * @author: caiwei
     * @date: 2019/5/5 21:44
     */
    public boolean removeJob(String jobName,String jobGroup,String triggerName,String triggerGroup){
        try {
            scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroup));
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("删除定时任务失败");
            return false;
        }
        return true;
    }


    private SimpleScheduleBuilder getSimpleScheduleBuilder(Integer interval, TimeUnit timeUnit) {

        switch (timeUnit) {
            case SECONDS:
                return SimpleScheduleBuilder.repeatSecondlyForever(interval);
            case MINUTES:
                return SimpleScheduleBuilder.repeatMinutelyForever(interval);
            case HOURS:
                return SimpleScheduleBuilder.repeatHourlyForever(interval);
            default:
                log.error("设置的时间间隔超出范围");
                return null;
        }
    }

    private Long getMilliseconds(Integer interval, TimeUnit timeUnit) {

        switch (timeUnit) {
            case SECONDS:
                return (long) (1000 * interval);
            case MINUTES:
                return (long) (60*1000 * interval);
            case HOURS:
                return (long) (60 * 60 * 1000 * interval);
            default:
                log.error("间隔时间转换错误");
                return null;
        }
    }
}