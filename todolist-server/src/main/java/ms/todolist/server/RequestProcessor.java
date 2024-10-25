package ms.todolist.server;

import java.util.List;
import java.util.Optional;
import ms.todolist.server.service.TodoServiceServerImpl;
import ms.todolist.domain.net.Request;
import ms.todolist.domain.service.TodoService;
import ms.todolist.domain.todo.Todo;
import org.json.JSONArray;

/**
 *
 * @author MS
 */
public class RequestProcessor {

    private final TodoService todoService;

    public RequestProcessor() {
        todoService = new TodoServiceServerImpl();
    }

    String process(String requestMessage) {
        String response = "";

        if (!requestMessage.isEmpty()) {
            Request request = Request.create(requestMessage);
            String action = request.getAction();
            String param = request.getParam();

            response  = switch (action) {
                case "get" -> processGet(param);
                case "save" -> processSave(param);
                case "delete" -> processDelete(param);
                case "complete" -> processComplete(param);              
                default -> "action unknown";
            };
        }

        return response;
    }

    private String processGet(String param) {
        if (param.equals("all")) {
            return getAllTodo();
        }
        return "param " + param + " unknown";
    }

    private String getAllTodo() {
        List<Todo> l = todoService.getData();        
        
        return !l.isEmpty() ? new JSONArray(l).toString() : "";

    }

    private String processSave(String param) {
        todoService.save(param);
        return "\"" + param + "\" saved.";
    }

    private String processDelete(String param) {
        int id = Integer.valueOf(param);
        Optional<Todo> todo = todoService.get(id);

        if (todo.isPresent()) {
            todoService.delete(id);
            return "\"" + todo.get().getDeskripsi() + "\" deleted.";
        }
        return "";
    }

    private String processComplete(String param) {
        int id = Integer.valueOf(param);
        Optional<Todo> todo = todoService.get(id);
        
        if(todo.isPresent()) {
            todoService.complete(id);
            return "\"" + todo.get().getDeskripsi() + "\" completed.";
        }
        
        return "";
    }
}