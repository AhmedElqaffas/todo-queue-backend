package demo.todo.group;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {

    @GetMapping("/all")
    public List<TodoItem> getTodos(){
        return List.of(new TodoItem("Hello"));
    }

    @PostMapping("/new")
    public TodoItem createTodo(@RequestBody TodoItem todo){
        return todo;
    }

}