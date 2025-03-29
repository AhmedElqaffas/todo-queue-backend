package demo.todo.group.queue;

import demo.todo.group.TodoService;
import demo.todo.group.entities.User;
import demo.todo.group.events.CreateTodoEvent;
import demo.todo.group.events.RemoveTodosEvent;
import demo.todo.group.repos.UserRepo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@RabbitListener(queues = QueueConfiguration.queueName)
public class TodoQueueConsumer {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepo userRepo;

    @RabbitHandler
    public void receive(CreateTodoEvent event) {
        if(event.getTodo() == null || event.getUserEmail() == null){
            return;
        }

        Optional<User> user = userRepo.findById(event.getUserEmail());
        if(user.isEmpty()){
            return;
        }

        event.getTodo().setUser(user.get());
        todoService.storeTodoInDatabase(event.getTodo());
    }

    @RabbitHandler
    public void receive(RemoveTodosEvent event) {
        if(event.getTodoIDs() == null || event.getUserEmail() == null){
            return;
        }
        try{
            todoService.removeTodos(event.getTodoIDs(), event.getUserEmail());
        }catch (IllegalArgumentException ex){
            System.out.println("Failed to handle event: " + ex);
        }

    }
}
