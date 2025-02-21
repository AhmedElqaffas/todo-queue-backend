package demo.todo.group.queue;

import demo.todo.group.entities.TodoItem;
import demo.todo.group.events.CreateTodoEvent;
import demo.todo.group.events.RemoveTodosEvent;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TodoQueuePublisher {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void sendCreateTodoEvent(TodoItem todo) {
        this.template.convertAndSend(queue.getName() , new CreateTodoEvent(todo));
    }

    public void sendRemoveTodosEvent(List<UUID> todoIDs) {
        this.template.convertAndSend(queue.getName() , new RemoveTodosEvent(todoIDs));
    }
}
