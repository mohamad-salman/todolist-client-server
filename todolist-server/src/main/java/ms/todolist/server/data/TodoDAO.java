
package ms.todolist.server.data;

import java.util.List;
import ms.todolist.domain.todo.Todo;

/**
 *
 * @author MS
 */
public interface TodoDAO {
    boolean insert(Todo todo);
    List<Todo> get();
    boolean delete(int id);
    void update(int id);
}