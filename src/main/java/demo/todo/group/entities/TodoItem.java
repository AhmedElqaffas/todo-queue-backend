package demo.todo.group.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class TodoItem {
    @Id
    private UUID id = UUID.randomUUID();
    private String text;
    private Date dateCreated = new Date();

    public TodoItem(){}

    public TodoItem(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}