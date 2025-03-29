package demo.todo.group.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    private String email;

    public User(){}
    public User(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User otherUser)) return false;
        return this.email.equals(otherUser.email);
    }
}
