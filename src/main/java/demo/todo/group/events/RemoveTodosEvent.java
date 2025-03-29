package demo.todo.group.events;

import java.util.List;
import java.util.UUID;

public class RemoveTodosEvent {

    private List<UUID> todoIDs;
    private String userEmail;

    public RemoveTodosEvent(List<UUID> todoIDs, String userEmail){
        this.todoIDs = todoIDs;
        this.userEmail = userEmail;
    }

    public List<UUID> getTodoIDs() {
        return todoIDs;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
