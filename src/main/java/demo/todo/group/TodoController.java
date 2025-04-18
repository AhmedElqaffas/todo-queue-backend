package demo.todo.group;

import demo.todo.group.entities.TodoItem;
import demo.todo.group.queue.TodoQueuePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoQueuePublisher queuePublisher;

    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<List<TodoItem>> getTodos(@RequestHeader("Todo-User-Email") String userEmail
                                                    ){
        return ResponseEntity.ok(todoService.getTodos(userEmail));
    }

    @PostMapping("/new")
    public TodoItem createTodos(@RequestBody TodoItem todo,
                                @RequestHeader("Todo-User-Email") String userEmail){
        queuePublisher.sendCreateTodoEvent(todo, userEmail);
        return todo;
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeTodo(@RequestBody List<UUID> todoIDs,
            @RequestHeader("Todo-User-Email") String userEmail){
        queuePublisher.sendRemoveTodosEvent(todoIDs, userEmail);
        return ResponseEntity.ok("Todo scheduled for deletion");
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleMissingRequestHeader(MissingRequestHeaderException ex, WebRequest request) {
        String headerName = ex.getHeaderName();
        String errorMessage = "Missing required header: " + headerName;
        return ResponseEntity.badRequest().body(errorMessage);
    }
}