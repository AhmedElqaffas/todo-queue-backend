package demo.todo.group.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import demo.todo.group.TodoService;
import demo.todo.group.entities.TodoItem;
import demo.todo.group.queue.TodoQueuePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TodoFunctions {

    @Autowired
    private TodoQueuePublisher queuePublisher;

    @Autowired
    private TodoService todoService;

    @FunctionName("GetAllTodos")
    public HttpResponseMessage getTodos(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Void> request,
            ExecutionContext context) {

        List<TodoItem> todos = todoService.getTodos();

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(todos)
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("CreateTodo")
    public HttpResponseMessage createTodo(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<TodoItem> request,
            ExecutionContext context) {

        queuePublisher.sendCreateTodoEvent(request.getBody());

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(request.getBody())
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("RemoveTodos")
    public HttpResponseMessage removeTodos(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<List<UUID>> request,
            ExecutionContext context) {

        queuePublisher.sendRemoveTodosEvent(request.getBody());
        return request
                .createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .build();
    }

}