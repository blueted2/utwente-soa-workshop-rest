package nl.cofano.schedulerapp.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * The Entity for storing all the schedulers
 * The getters & setters are generated by lombok (https://projectlombok.org/)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleItem {
    private Integer id;

    private String cronExpression;
    private Instant lastExecuted;

    private String description;
    private Integer assignee;

}