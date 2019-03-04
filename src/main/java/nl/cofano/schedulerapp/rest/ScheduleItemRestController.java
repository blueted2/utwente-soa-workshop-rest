package nl.cofano.schedulerapp.rest;

import nl.cofano.schedulerapp.scheduler.ScheduleItem;
import nl.cofano.schedulerapp.scheduler.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class ScheduleItemRestController {

    @Autowired private ScheduleItemService service;

    @RequestMapping(
        path="/item/{id}",
        method = RequestMethod.GET
    )
    public ScheduleItem getItem(@PathVariable Integer id){
        return service.getItem(id);
    }

    @RequestMapping(
        path="/item",
        method = RequestMethod.GET
    )
    public Collection<ScheduleItem> getAll(){
        return service.getItems();
    }

    @RequestMapping(
        method = RequestMethod.PUT,
        path="/item/{id}"
    )
    public ScheduleItem updateItem(@PathVariable Integer id, @RequestBody ScheduleItem scheduleItem){
        scheduleItem.setId(id);
        return service.updateItem(scheduleItem);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        path = "/item"
    )
    public ScheduleItem addItem(@RequestBody ScheduleItem scheduleItem){
        return service.createItem(scheduleItem);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        path="/item/{id}"
    )
    public void deleteItem(@PathVariable Integer id){
        service.deleteItem(id);
    }
}
