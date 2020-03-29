package by.andersen.kudko.dao.jdbctemplate.daointerface;


import by.andersen.kudko.model.entity.User;

import java.util.List;

public interface IUserDAO extends CrudDAO<User> {
    List<User> findUserByName(String name);
}
