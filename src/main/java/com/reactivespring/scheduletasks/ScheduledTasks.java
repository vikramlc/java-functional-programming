package com.reactivespring.scheduletasks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private int i = 0;

    @Scheduled(fixedRate = 1000)
    public void scheduledCount() {

        System.out.println("Count: " + i++);
    }


}
