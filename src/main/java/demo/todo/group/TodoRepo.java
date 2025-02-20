package demo.todo.group;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, UUID> {
}
