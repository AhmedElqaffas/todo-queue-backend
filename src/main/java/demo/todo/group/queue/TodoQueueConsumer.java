package demo.todo.group.queue;

import demo.todo.group.TodoService;
import demo.todo.group.events.CreateTodoEvent;
import demo.todo.group.events.RemoveTodosEvent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


@RabbitListener(queues = QueueConfiguration.queueName)
public class TodoQueueConsumer {

    @Autowired
    private TodoService todoService;

    @RabbitHandler
    public void receive(CreateTodoEvent event) {
        if(event.getTodo() == null){
            return;
        }
        todoService.storeTodoInDatabase(event.getTodo());
    }

    @RabbitHandler
    public void receive(RemoveTodosEvent event) {
        if(event.getTodoIDs() == null){
            return;
        }
        todoService.removeTodos(event.getTodoIDs());
    }
}
