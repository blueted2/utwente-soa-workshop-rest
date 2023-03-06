package nl.cofano.schedulerapp.scheduler;

import nl.cofano.schedulerapp.exceptions.TodoCreateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * The service used for creating new items in the todo-app
 */
@Service
public class TodoService {

    public static class TodoRepresentation {
        public Integer assignee;
        public String description;
        public Integer id;
        public boolean markedAsFinished;

        public TodoRepresentation() {
        }

        public TodoRepresentation(Integer assignee, String description) {
            this.assignee = assignee;
            this.description = description;
            this.id = 0;
            this.markedAsFinished = false;
        }
    }

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TodoService.class);

    @Autowired private RestTemplateBuilder restTemplateBuilder;
    public void createTodo(String description, Integer assignee) throws TodoCreateException{
        // Add an webservice call for creating an item to the todo-app
        String url = "http://localhost:8081/todo";
        
        RestTemplate restTemplate = restTemplateBuilder.build();
        TodoRepresentation todoRepresentation = new TodoRepresentation(assignee, description);

        log.info("Adding todo with values: description={}, assignee={}", description, assignee);
        todoRepresentation = restTemplate.postForObject(url, todoRepresentation, TodoRepresentation.class);
    }
}
