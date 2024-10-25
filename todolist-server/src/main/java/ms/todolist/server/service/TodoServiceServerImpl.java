package ms.todolist.server.service;

import java.util.List;
import java.util.Optional;
import ms.todolist.domain.service.TodoService;
import ms.todolist.domain.todo.Todo;
import ms.todolist.server.data.DAOFactory;
import ms.todolist.server.data.TodoDAO;

/**
 *
 * @author MS
 */
public class TodoServiceServerImpl implements TodoService {

    private final TodoDAO todoDAO;

    public TodoServiceServerImpl() {
        DAOFactory daoFactory = new DAOFactory();

        todoDAO = daoFactory.getTodoDAO();
    }

    @Override
    public List<Todo> getData() {
        return todoDAO.get();
    }

    @Override
    public void save(String deskripsi) {
        todoDAO.insert(new Todo(deskripsi));
    }

    @Override
    public void delete(int id) {
        todoDAO.delete(id);
    }

    @Override
    public void complete(int id) {
        todoDAO.update(id);
    }

    @Override
    public Optional<Todo> get(int id) {
        return getData().stream()
                .filter(todo -> todo.getId() == id)
                .findAny();
    }

}
