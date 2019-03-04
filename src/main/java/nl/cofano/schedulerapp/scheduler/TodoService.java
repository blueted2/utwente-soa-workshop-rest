package nl.cofano.schedulerapp.scheduler;

import lombok.extern.slf4j.Slf4j;
import nl.cofano.schedulerapp.exceptions.TodoCreateException;
import org.springframework.stereotype.Service;


/**
 * The service used for creating new items in the todo-app
 */
@Service
@Slf4j
public class TodoService {
    public void createTodo(String description, Integer assignee) throws TodoCreateException{
        // Add an webservice call for creating an item to the todo-app
        log.info("This should be replaced by todo creation code, with values: description={}, assignee={}", description, assignee);
    }
}
