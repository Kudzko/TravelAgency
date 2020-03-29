package by.andersen.kudko.dao.jdbctemplate.daointerface;

import by.andersen.kudko.dao.jdbctemplate.exception.DAOException;

import java.util.List;

public interface CrudDAO<E> {

    E find(long id) throws DAOException;

    E create(E model) throws DAOException;

    int update(E model) throws DAOException;

    int delete(E model) throws DAOException;

    List<E> findAll() throws DAOException;
}
