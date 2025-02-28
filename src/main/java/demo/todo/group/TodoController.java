package demo.todo.group;

import demo.todo.group.entities.TodoItem;
import demo.todo.group.queue.TodoQueuePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * For local testing using postman
 */
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoQueuePublisher queuePublisher;

    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public List<TodoItem> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/new")
    public TodoItem createTodos(@RequestBody TodoItem todo){
        queuePublisher.sendCreateTodoEvent(todo);
        return todo;
    }

    @PostMapping("/remove")
    public void removeTodo(@RequestBody List<UUID> todoIDs){
        queuePublisher.sendRemoveTodosEvent(todoIDs);
    }

}