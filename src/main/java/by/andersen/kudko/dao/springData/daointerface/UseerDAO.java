package by.andersen.kudko.dao.springData.daointerface;

import by.andersen.kudko.domain.User;

import java.util.List;

public interface UseerDAO extends CrudDAO<User> {
    List<User> findUserByName(String name);
}
