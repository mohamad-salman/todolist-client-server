package ms.todolist.domain.service;

import java.util.List;
import java.util.Optional;
import ms.todolist.domain.todo.Todo;

/**
 *
 * @author MS
 */
public interface TodoService {
    public List<Todo> getData();
    public Optional<Todo> get(int id);
    public void save(String todo);
    public void delete(int id);
    public void complete(int id);
}