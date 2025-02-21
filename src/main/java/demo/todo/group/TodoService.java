package demo.todo.group;

import demo.todo.group.entities.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    public void storeTodoInDatabase(TodoItem todo){
        todoRepo.save(todo);
    }

    public List<TodoItem> getTodos(){
        return todoRepo.findAll();
    }

    public void removeTodos(List<UUID> todosIDs){
        todoRepo.deleteAllById(todosIDs);
    }
}
