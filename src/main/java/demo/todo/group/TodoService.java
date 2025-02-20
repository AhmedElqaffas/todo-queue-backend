package demo.todo.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    public void storeTodoInDatabase(TodoItem todo){
        todoRepo.save(todo);
    }
}
