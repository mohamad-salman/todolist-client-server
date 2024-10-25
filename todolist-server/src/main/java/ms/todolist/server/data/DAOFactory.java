package ms.todolist.server.data;


/**
 *
 * @author MS
 */
public class DAOFactory {

    private TodoDAO todoDAO;

    public DAOFactory() {
        todoDAO = null;
    }
    
    
    public TodoDAO getTodoDAO() {
        if(todoDAO == null) {
            todoDAO = new TodoDAOFile();
        }
        return todoDAO;
    }
    
}
