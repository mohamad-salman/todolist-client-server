package ms.todolist.domain.service;

import java.util.LinkedList;
import java.util.List;
import ms.todolist.domain.todo.Todo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MS
 */
public class JSONUtil {

    public static List<Todo> jsonToTodoList(String jsonString) {
        JSONArray ja = new JSONArray(jsonString);
        List<Todo> l = new LinkedList<>();

        ja.forEach(jsonObj -> l.add(jsonObjectToTodo(jsonObj)));

        return l;
    }

    private static Todo jsonObjectToTodo(Object obj) {
        JSONObject jsonObj = (JSONObject) obj;
        return new Todo(
                jsonObj.getInt("id"),
                jsonObj.getString("deskripsi"),
                jsonObj.getBoolean("sudahDikerjakan")
        );
    }
}
