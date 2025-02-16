package demo.todo.group;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoQueuePublisher {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send(String message) {
        this.template.convertAndSend(queue.getName() , message);
    }
}
