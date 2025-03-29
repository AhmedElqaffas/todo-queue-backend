package demo.todo.group;

import demo.todo.group.entities.TodoItem;
import demo.todo.group.entities.User;
import demo.todo.group.repos.TodoRepo;
import demo.todo.group.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    public void storeTodoInDatabase(TodoItem todo){
        todoRepo.save(todo);
    }

    public List<TodoItem> getTodos(String userEmail){
        // if no user exists in the database, create one
        User user = userRepo.findById(userEmail)
                .orElseGet(() -> userRepo.save(new User(userEmail)));
        return todoRepo.findAllByUser(user);
    }

    public void removeTodos(List<UUID> todosIDs, String userEmail){
        Optional<User> user = userRepo.findById(userEmail);
        if(user.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        // sanity check: the todos do belong to this user
        if(!todoRepo.findAllById(todosIDs).stream().allMatch(item -> item.getUser().equals(user.get()))){
            throw new IllegalArgumentException("One or more todo does not belong to this user.");
        }
        todoRepo.deleteAllById(todosIDs);
    }
}
