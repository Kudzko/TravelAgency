package by.andersen.kudko.dao.jpa.daointerface;


import by.andersen.kudko.model.jpaentity.User;

import java.util.List;

public interface IUserDAO {
    List<User> findByName(String name);

    List<User> findBySurname(String surname);

}
