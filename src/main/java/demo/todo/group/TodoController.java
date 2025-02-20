package demo.todo.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TodoItem createTodo(@RequestBody TodoItem todo){
        queuePublisher.send(todo.getText());
        return todo;
    }

}