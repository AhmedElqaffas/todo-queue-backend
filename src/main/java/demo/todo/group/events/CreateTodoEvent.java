package demo.todo.group.events;

import demo.todo.group.entities.TodoItem;

public class CreateTodoEvent {
    private TodoItem todo;

    public CreateTodoEvent(TodoItem todo){
        this.todo = todo;
    }

    public TodoItem getTodo() {
        return todo;
    }
}
