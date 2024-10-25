package ms.todolist.domain.todo;

import java.util.Objects;

/**
 *
 * @author MS
 */
public class Todo {
    private int id;
    private final String deskripsi;
    private boolean isSudahDikerjakan;

    public Todo(String deskripsi) {        
        this.deskripsi = deskripsi;
    }
    
    public Todo(int id, String deskripsi) {
        this.id = id;
        this.deskripsi = deskripsi;
        isSudahDikerjakan = false;
    }

    public Todo(int id, String deskripsi, boolean isSudahDikerjakan) {
        this(id, deskripsi);
        this.isSudahDikerjakan = isSudahDikerjakan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isSudahDikerjakan() {
        return isSudahDikerjakan;
    }

    public void setIsSudahDikerjakan(boolean isSudahDikerjakan) {
        this.isSudahDikerjakan = isSudahDikerjakan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.deskripsi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Todo other = (Todo) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.deskripsi, other.deskripsi);
    }

    @Override
    public String toString() {
        return deskripsi;
    }
    
}
