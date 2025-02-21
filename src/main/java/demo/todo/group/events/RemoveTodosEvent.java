package demo.todo.group.events;

import java.util.List;
import java.util.UUID;

public class RemoveTodosEvent {

    private List<UUID> todoIDs;

    public RemoveTodosEvent(List<UUID> todoIDs){
        this.todoIDs = todoIDs;
    }

    public List<UUID> getTodoIDs() {
        return todoIDs;
    }
}
