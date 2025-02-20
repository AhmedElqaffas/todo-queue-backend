package demo.todo.group;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


@RabbitListener(queues = QueueConfiguration.queueName)
public class TodoQueueConsumer {

    @Autowired
    private TodoService todoService;

    @RabbitHandler
    public void receive(String message) {
        todoService.storeTodoInDatabase(new TodoItem(message));
    }
}
