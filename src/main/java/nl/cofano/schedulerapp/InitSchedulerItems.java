package nl.cofano.schedulerapp;

import nl.cofano.schedulerapp.scheduler.ScheduleItem;
import nl.cofano.schedulerapp.scheduler.ScheduleItemService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Component
public class InitSchedulerItems {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(InitSchedulerItems.class);
    @Autowired private ScheduleItemService service;
 
    @PostConstruct
    public void init() {
        log.info("Start creating dummy items");

        // Add schedule item for every minute
        service.createItem(new ScheduleItem(
            0,
            "* * * * *",
            Instant.now(),
            "Minute Item",
            1
        ));

        // Add schedule item for every 5 minutes
        service.createItem(new ScheduleItem(
            0,
            "*/5 * * * *",
            Instant.now(),
            "Five minute Item",
            2
        ));

        // Add schedule item for every hour
        service.createItem(new ScheduleItem(
            0,
            "1 * * * *",
            Instant.now(),
            "Hourly Item",
            3
        ));
    }
}
