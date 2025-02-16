package demo.todo.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoQueuePublisher queuePublisher;

    @GetMapping("/all")
    public List<TodoItem> getTodos(){
        return List.of(new TodoItem("Hello"));
    }

    @PostMapping("/new")
    public TodoItem createTodo(@RequestBody TodoItem todo){
        queuePublisher.send(todo.getText());
        return todo;
    }

}