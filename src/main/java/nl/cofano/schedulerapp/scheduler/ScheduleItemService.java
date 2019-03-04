package nl.cofano.schedulerapp.scheduler;

import nl.cofano.schedulerapp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * The service for storing and managing all the ScheduleItems. This service can be used for saving, updating or retrieving a scheduleitem.
 * The current implementation stores the list in memory.
 */
@Service
public class ScheduleItemService {

    private static final Map<Integer, ScheduleItem> items = Collections.synchronizedMap(new HashMap<>());
    private static int idCounter = 1;

    public ScheduleItem createItem(ScheduleItem item){
        item.setId(idCounter++);
        items.put(item.getId(), item);
        return item;
    }

    public ScheduleItem updateItem(ScheduleItem item){
        items.put(item.getId(), item);
        return item;
    }

    public ScheduleItem getItem(int id){
        if(!items.containsKey(id)) throw new NotFoundException("Could not find schedule item with id " + id);
        return items.get(id);
    }

    public void deleteItem(int id){
        if(!items.containsKey(id)) throw new NotFoundException("Could not find schedule item with id " + id);
        items.remove(id);
    }

    public Collection<ScheduleItem> getItems(){
        return items.values();
    }
}
