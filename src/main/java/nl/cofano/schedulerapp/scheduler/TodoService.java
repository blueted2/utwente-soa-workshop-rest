package nl.cofano.schedulerapp.scheduler;

import nl.cofano.schedulerapp.exceptions.TodoCreateException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;


/**
 * The service used for creating new items in the todo-app
 */
@Service
public class TodoService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TodoService.class);

    public void createTodo(String description, Integer assignee) throws TodoCreateException{
        // Add an webservice call for creating an item to the todo-app
        log.info("This should be replaced by todo creation code, with values: description={}, assignee={}", description, assignee);
    }
}
