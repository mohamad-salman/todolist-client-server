package ms.todolist.server.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import ms.todolist.domain.todo.Todo;

;

/**
 *
 * @author MS
 */
public class TodoDAOFile implements TodoDAO {

    private final Path fileTodo;

    public TodoDAOFile() {
        Path resourcesPath = getResourcesPath();
        fileTodo = Paths.get(resourcesPath + "/todo.csv");

        if (!Files.exists(fileTodo)) {
            buatFileTodoCSV();
        }
    }

    private Path getResourcesPath() {
        String resourcesDir = "resources";
        Path resourcesPath = Paths.get(resourcesDir);
        if (!Files.exists(resourcesPath)) {
            resourcesPath = Paths.get("../" + resourcesDir);
            if (!Files.exists(resourcesPath)) {
                resourcesPath = Paths.get("./" + resourcesDir);
            }
        }
        return resourcesPath;
    }

    private void buatFileTodoCSV() {
        try {
            Files.createFile(fileTodo);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public boolean insert(Todo todo) {
        todo.setId(getNextId());

        try {
            Files.writeString(fileTodo, toCSV(todo), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return true;
    }

    private int getNextId() {
        int lastId = get().stream()
                .map(todo -> todo.getId())
                .max(Integer::compareTo)
                .orElse(0);

        return lastId + 1;
    }

    private String toCSV(Todo todo) {
        String tuple = String.join(",",
                String.valueOf(todo.getId()),
                todo.getDeskripsi(),
                String.valueOf(todo.isSudahDikerjakan())
        );

        return new StringBuilder(tuple)
                .append(System.lineSeparator())
                .toString();
    }

    @Override
    public List<Todo> get() {
        List<Todo> list = new LinkedList<>();
        try {
            Files.readAllLines(fileTodo)
                    .forEach(csv -> list.add(toTodo(csv)));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return list;
    }

    private Todo toTodo(String csv) {
        String[] csvArr = csv.split(",");
        int id = Integer.valueOf(csvArr[0]);
        String deskripsi = csvArr[1];
        boolean isSudahDikerjakan = Boolean.valueOf(csvArr[2]);

        return new Todo(id, deskripsi, isSudahDikerjakan);
    }

    @Override
    public boolean delete(int id) {
        List<Todo> list = get();
        list.removeIf(todo -> todo.getId() == id);

        updateFileData(list);

        return true;
    }

    private void updateFileData(List<Todo> list) {
        StringBuilder csvBuilder = new StringBuilder();
        list.forEach(t -> csvBuilder.append(toCSV(t)));

        try {
            Files.writeString(
                    fileTodo,
                    csvBuilder.toString(),
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void update(int id) {
        List<Todo> list = get();
        Optional<Todo> todoUpdated = list.stream()
                .filter(todo -> todo.getId() == id && todo.isSudahDikerjakan() == false)
                .findFirst();

        if (todoUpdated.isPresent()) {
            todoUpdated.get().setIsSudahDikerjakan(true);

            updateFileData(list);
        }
    }

}
