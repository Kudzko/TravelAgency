package by.andersen.kudko.daojpa.daointerface;

import by.andersen.kudko.jpaentity.User;

import java.util.List;

public interface IUserDAO {
    List<User> findByName(String name);

}
