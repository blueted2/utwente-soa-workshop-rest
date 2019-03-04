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
}
