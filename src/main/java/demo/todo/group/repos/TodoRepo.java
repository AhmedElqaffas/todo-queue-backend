package demo.todo.group.repos;


import demo.todo.group.entities.TodoItem;
import demo.todo.group.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, UUID> {
    List<TodoItem> findAllByUser(User user);
}
