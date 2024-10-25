package ms.todolist.client.gui.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import ms.todolist.domain.net.Request;
import ms.todolist.domain.service.JSONUtil;
import ms.todolist.domain.service.TodoService;
import ms.todolist.domain.todo.Todo;

/**
 *
 * @author MS
 */
public class TodoServiceClientImpl implements TodoService {

    private final String serverAddress;
    private final int serverPort;

    public TodoServiceClientImpl() {
        Properties prop = new Properties();

        try (FileReader file = new FileReader("resources/config.properties")) {
            prop.load(file);
        } catch (IOException ex) {
            Logger.getLogger(TodoServiceClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        serverAddress = prop.getProperty("server.address");
        serverPort = Integer.valueOf(prop.getProperty("server.port"));
    }

    @Override
    public List<Todo> getData() {
        String serverResponse = sendRequest(Request.create("get", "all"));
        return !serverResponse.isEmpty()
                ? JSONUtil.jsonToTodoList(serverResponse)
                : new ArrayList<>();
    }

    @Override
    public void save(String todo) {
        sendRequestAndDisplayServerResponse(Request.create("save", todo));
    }

    @Override
    public void delete(int id) {
        sendRequestAndDisplayServerResponse(Request.create("delete", String.valueOf(id)));
    }

    @Override
    public void complete(int id) {
        sendRequestAndDisplayServerResponse(Request.create("complete", String.valueOf(id)));
    }

    @Override
    public Optional<Todo> get(int id) {
        return getData().stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
    }

    private void sendRequestAndDisplayServerResponse(Request request) {
        String serverResponse = sendRequest(request);
        System.out.println(serverResponse);
    }

    private String sendRequest(Request request) {
        String response = "";
        try (Socket socket = new Socket(serverAddress, serverPort); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(formatReq(request));
            response = in.readLine();

        } catch (IOException ex) {
            Logger.getLogger(TodoServiceClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private String formatReq(Request req) {
        StringBuilder sb = new StringBuilder();
        sb.append("action=").append(req.getAction());
        sb.append(",");
        sb.append("param=").append(req.getParam());

        return sb.toString();
    }

}
