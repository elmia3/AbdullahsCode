package data;

import java.util.List;

/**
 * @author abdul
 */
public interface DAO<T> {
    List<T> getAll();

    void addOrUpdate(T object);

    void remove(T object);

    boolean save();

    boolean load();
}
