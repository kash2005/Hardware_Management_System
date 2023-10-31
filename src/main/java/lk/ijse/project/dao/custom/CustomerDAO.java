package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    Integer[] customerValueMonths() throws SQLException, ClassNotFoundException;

    int customerCount() throws SQLException, ClassNotFoundException;
}
