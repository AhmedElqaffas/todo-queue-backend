package demo.todo.group.events;

import demo.todo.group.entities.TodoItem;

public class CreateTodoEvent {
    private TodoItem todo;
    private String userEmail;

    public CreateTodoEvent(TodoItem todo, String userEmail){
        this.todo = todo;
        this.userEmail = userEmail;
    }

    public TodoItem getTodo() {
        return todo;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
