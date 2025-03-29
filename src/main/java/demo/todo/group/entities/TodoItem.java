package demo.todo.group.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "todo_items")
public class TodoItem {
    @Id
    private UUID id = UUID.randomUUID();
    private String text;
    private LocalDateTime dateCreated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "creator", // 'user' is reserved keyword in postgres
            foreignKey = @ForeignKey(name = "todoitem_user",
                    foreignKeyDefinition = "FOREIGN KEY (creator) REFERENCES public.users(email) ON DELETE CASCADE")
    )
    private User user;

    public TodoItem(){}

    public TodoItem(String text){
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}