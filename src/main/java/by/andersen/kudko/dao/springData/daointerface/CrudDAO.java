package by.andersen.kudko.dao.springData.daointerface;

import java.util.List;

public interface CrudDAO<T> {

    T find(long id);

    void create(T model);

    void update(T model);

    void delete(T model);

    List<T> findAll();
}
