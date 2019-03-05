package nl.cofano.schedulerapp.scheduler;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import nl.cofano.schedulerapp.exceptions.TodoCreateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 *  The ItemScheduler walks periodically through all scheduled items, and calls the todoService if the cron expresion is triggered since the last run.
 *  Due the fact the cronparser uses his own data library, there are some conversations necessary.
 */
@Component
public class ItemScheduler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ItemScheduler.class);
    @Autowired private ScheduleItemService storage;
    @Autowired private TodoService todoService;

    @Scheduled(fixedDelay = 60 * 1000)
    public void timer() {
        log.info("Item scheduler startup");

        // Get current timezone, and current time
        org.threeten.bp.ZoneId zone = org.threeten.bp.ZoneId.systemDefault();
        org.threeten.bp.ZonedDateTime now = org.threeten.bp.ZonedDateTime.now(zone);

        for(ScheduleItem item : storage.getItems()) {
            // Parse the cron expression
            CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
            Cron cron = parser.parse(item.getCronExpression());

            // Determine the last time the cronjob should have been executed
            ExecutionTime executionTime = ExecutionTime.forCron(cron);
            org.threeten.bp.ZonedDateTime lastCronTime = executionTime.lastExecution(now).orNull();

            // Determine the last time the schedule item is executed
            org.threeten.bp.ZonedDateTime lastExecutedTime = org.threeten.bp.ZonedDateTime.ofInstant(
                org.threeten.bp.Instant.ofEpochMilli(item.getLastExecuted().toEpochMilli()),
                zone
            );

            // Decide if an new item should be created
            if (lastCronTime != null && (lastCronTime.isEqual(lastExecutedTime) || lastCronTime.isAfter(lastExecutedTime)) && lastCronTime.isBefore(now)) {
                try {
                    // Create item with the todo service
                    todoService.createTodo(
                        item.getDescription(),
                        item.getAssignee()
                    );

                    // Update last executed to now
                    item.setLastExecuted(Instant.now());
                } catch (TodoCreateException e) {
                    log.error("Could not create todo item", e);
                }
            }
        }

        log.info("Item scheduler finished");
    }
}
