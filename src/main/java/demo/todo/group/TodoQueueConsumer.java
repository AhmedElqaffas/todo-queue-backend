package demo.todo.group;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@RabbitListener(queues = QueueConfiguration.queueName)
public class TodoQueueConsumer {

    @RabbitHandler
    public void receive(String message) {
        System.out.println(message);
    }
}
