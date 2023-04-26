package com.learn.microservice.service_2.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestScheduler {
    public static void main(String[] args) throws SchedulerException {
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity("testJob", "group1").build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger", "triggergroup1").startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
        defaultScheduler.scheduleJob(job, trigger);
        defaultScheduler.start();
    }
}
