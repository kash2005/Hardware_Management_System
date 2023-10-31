package lk.ijse.project.dao;

import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    boolean save(T entity) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    T searchId(String id) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;
}
